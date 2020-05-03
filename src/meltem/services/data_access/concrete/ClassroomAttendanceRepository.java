package meltem.services.data_access.concrete;

import meltem.models.ClassroomAttendance;
import meltem.services.data_access.PersistentDataService;

import java.sql.SQLException;
import java.util.List;

public class ClassroomAttendanceRepository extends PersistentDataService<ClassroomAttendance> {
    @Override
    public ClassroomAttendance fetchById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<ClassroomAttendance> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Add(ClassroomAttendance entity) throws SQLException {

    }

    @Override
    public void UpdateById(ClassroomAttendance entity, int id) throws SQLException {

    }

    @Override
    public void Delete(int id) throws SQLException {

    }
}
