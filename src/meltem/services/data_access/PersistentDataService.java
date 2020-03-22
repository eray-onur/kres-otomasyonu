package meltem.services.data_access;

import meltem.enums.LogType;

import java.sql.*;
import java.util.List;

public abstract class PersistentDataService<T> implements IDataService<T> {
    public static String connectionString = "jdbc:sqlserver://localhost;databaseName=meltem_kres;user=SA;Password=genotype*135;Trusted_Connection=true;";
    protected Connection connection;

    public static void main(String[] args) {
//        try {
//            Connection connection = DriverManager.getConnection(connectionString);
//            Statement statement = connection.createStatement();
//            ResultSet rs = statement.executeQuery("SELECT * FROM userDB");
//            while(rs.next()) {
//                Logger.LogDebug(String.valueOf(rs.getInt("user_id")));
//            }
//        }
//        catch(Exception ex) {
//            ex.printStackTrace();
//        }
    }

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
    }
    public void close() throws SQLException {
        this.connection.close();
    }
}
