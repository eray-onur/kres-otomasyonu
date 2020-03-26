package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Teacher;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TeacherDataService extends PersistentDataService<Teacher> {
    @Override
    public Teacher fetchById(int id) throws SQLException {
        Teacher[] teachers = new Teacher[1];

        this.connect();
        // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
        Statement statement = connection.createStatement();
        String query = String.format("SELECT * FROM master.users WHERE user_id = %d", id);
        ResultSet rs = statement.executeQuery(query);
        while(rs.next()) {
            Teacher teacher = new Teacher();
            // teacher.setId blah blah
        }
        // Bitis
        this.close();
        return teachers[0];
    }

    @Override
    public List<Teacher> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Add(Teacher entity) throws SQLException {

    }

    @Override
    public void UpdateById(Teacher teacher, int id) {

    }

    @Override
    public void Delete(int id) {

    }
}
