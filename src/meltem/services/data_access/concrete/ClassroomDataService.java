package meltem.services.data_access.concrete;

import meltem.models.Classroom;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;

import java.sql.SQLException;
import java.util.List;

public class ClassroomDataService extends PersistentDataService<Classroom> {
    @Override
    public Classroom fetchById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Classroom> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Update(Classroom classroom) {

    }

    @Override
    public void Delete(int id) {

    }
}
