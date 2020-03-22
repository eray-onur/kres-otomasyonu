package meltem.test;

import meltem.services.data_access.concrete.UserDataService;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        UserDataService userService = new UserDataService();
        userService.fetchById(1);
    }
}
