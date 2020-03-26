package meltem.test;

import meltem.enums.LogType;
import meltem.models.User;
import meltem.services.data_access.concrete.UserDataService;
import meltem.services.logging.Logger;

import java.sql.SQLException;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) throws SQLException {
        UserDataService uds = new UserDataService();
        User user = uds.fetchById(1);
        Logger.LogDebug(user.getUserName());
    }
}

