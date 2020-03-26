package meltem.services.data_access;

import meltem.enums.LogType;

import java.sql.*;
import java.util.List;

public abstract class PersistentDataService<T> implements IDataService<T> {
    public static String connectionString = "jdbc:sqlserver://localhost;Database=meltem_kres;CurrentSchema=master;user=SA;Password=genotype*135;Trusted_Connection=true;";
    protected Connection connection;

    public static void main(String[] args) {
    }

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection(connectionString);
    }
    public void close() throws SQLException {
        this.connection.close();
    }
}
