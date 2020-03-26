package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ClassroomDataService extends PersistentDataService<Classroom> {
    @Override
    public Classroom fetchById(int id) throws SQLException {
        Classroom[] classroomList = new Classroom[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM classrooms WHERE classroom_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setClassroomId(rs.getInt("classroom_id"));
                classroomList[0] = classroom;
                Logger.Log(LogType.Debug, String.valueOf(classroom.getClassroomId()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return classroomList[0];
    }

    @Override
    public List<Classroom> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Add(Classroom entity) throws SQLException {

    }

    @Override
    public void UpdateById(Classroom classroom, int id) {

    }

    @Override
    public void Delete(int id) {

    }
}
