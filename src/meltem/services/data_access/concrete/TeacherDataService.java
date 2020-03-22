package meltem.services.data_access.concrete;

import meltem.models.Teacher;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;

import java.sql.SQLException;
import java.util.List;

public class TeacherDataService extends PersistentDataService<Teacher> {
    @Override
    public Teacher fetchById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Teacher> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Update(Teacher teacher) {

    }

    @Override
    public void Delete(int id) {

    }
}
