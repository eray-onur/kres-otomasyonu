package meltem.services.data_access.concrete;

import meltem.models.Student;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;

import java.sql.SQLException;
import java.util.List;

public class StudentDataService extends PersistentDataService<Student> {
    @Override
    public Student fetchById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Student> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Add(Student entity) throws SQLException {

    }

    @Override
    public void UpdateById(Student entity, int id) throws SQLException {

    }

    @Override
    public void Delete(int id) {

    }
}
