package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.User;
import meltem.services.logging.Logger;
import meltem.services.data_access.PersistentDataService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDataService extends PersistentDataService<User> {

    @Override
    public User fetchById(int id) throws SQLException {
        User[] userList = new User[1];

        this.connect();
        // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM userDB WHERE user_id = %d", id);
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("user_password"));
            user.setUserType((short)rs.getInt("user_type"));
            userList[0] = user;
            Logger.Log(LogType.Debug, user.getUserName());
        }
        // Bitis
        this.close();
        return userList[0];
    }

    @Override
    public List<User> fetchAll() throws SQLException {
        ArrayList<User> userList = new ArrayList<User>();
        this.connect();
        // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
        Statement statement = connection.createStatement();
        String query = "SELECT * FROM userDB";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setPassword(rs.getString("user_password"));
            user.setUserType((short) rs.getInt("user_type"));
            Logger.Log(LogType.Debug, user.getUserName());
            userList.add(user);
        }
        // Bitis
        this.close();
        return userList;
    }

    @Override
    public void Update(User user) throws SQLException {
        this.connect();
        // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM userDB WHERE user_id = %d");
        ResultSet rs = statement.executeQuery(query);
        // Bitis
        this.close();
    }

    @Override
    public void Delete(int id) throws SQLException {

        this.connect();
        // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM userDB WHERE user_id = %d");
        ResultSet rs = statement.executeQuery(query);
        // Bitis
        this.close();
    }
}
