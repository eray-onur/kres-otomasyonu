package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;
import meltem.view_models.ClassroomViewModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassroomRepository extends PersistentDataService<Classroom> {


    public static ClassroomRepository Instance;
    public ClassroomRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }
    @Override
    public Classroom fetchById(int id) {
        Classroom[] classroomList = new Classroom[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM classrooms WHERE classroom_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Classroom classroom = new Classroom (
                        rs.getInt("classroom_id"),
                        rs.getString("classroom_name"),
                        rs.getInt("classroom_teacher_id"),
                        rs.getInt("classroom_capacity")
                );
                classroomList[0] = classroom;
                Logger.Log(LogType.Debug, String.valueOf(classroom.getClassroomId()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
             ex.printStackTrace();
        }
        return classroomList[0];
    }

    @Override
    public List<Classroom> fetchAll() {
        List<Classroom> classroomList = new ArrayList<Classroom>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM classrooms";
           String query2 = "SELECT classrooms.classroom_id, classrooms.classroom_name, teachers.teacher_name, teachers.teacher_lastname, classrooms.classroom_capacity " +
                   "FROM (" +
                   "classroom_courses " +
                   "INNER JOIN classrooms on classroom_courses.classroom_id = classrooms.classroom_id " +
                   "INNER JOIN teachers on classroom_courses.classroom_teacher_id = teachers.teacher_id" +
                   ");";
            int classroomId = -1;
            String classroomName = "";
            String teacherName = "";
            String teacherLastName = "";
            int classroomCapacity = -1;
            Classroom classroom = null;
            ResultSet rs = statement.executeQuery(query2);
            while(rs.next()) {
                classroomId = rs.getInt("classroom_id");
                classroomName = rs.getString("classroom_name");
                teacherName = rs.getString("teacher_name");
                teacherLastName = rs.getString("teacher_lastname");
                classroomCapacity = rs.getInt("classroom_capacity");
                classroomList.add(new Classroom(classroomId, classroomName, teacherName, teacherLastName, classroomCapacity));
            }
            Logger.LogDebug(classroomList.get(0).getClassroomName());
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return classroomList;
    }

    @Override
    public void Add(Classroom entity) {
        try {
            this.connect();
            String sql = "INSERT INTO classrooms VALUES(?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, entity.getClassroomTeacherId());
            pst.setString(2, entity.getClassroomName());
            pst.setInt(3, entity.getClassroomCapacity());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Classroom classroom, int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE classrooms SET classroom_teacher_id = ?, classroom_name = ?, classroom_capacity = ? WHERE classroom_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, classroom.getClassroomTeacherId());
            pst.setString(2, classroom.getClassroomName());
            pst.setInt(3, classroom.getClassroomCapacity());
            pst.setInt(4, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void Delete(int id) {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "DELETE FROM classrooms WHERE classroom_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, id);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }
}
