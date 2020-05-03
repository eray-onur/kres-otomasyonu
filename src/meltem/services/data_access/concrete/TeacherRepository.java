package meltem.services.data_access.concrete;

import meltem.models.Teacher;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TeacherRepository extends PersistentDataService<Teacher> {

    public static TeacherRepository Instance;

    public TeacherRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }

    @Override
    public Teacher fetchById(int id) {
        Teacher[] teachers = new Teacher[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM teachers WHERE teacher_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                // Teacher teacher = new Teacher(teacherId, teacherName, teacherLastName, teacherEmail, teacherType);
                // teacher.setId blah blah
                teachers[0].setTeacherId(rs.getInt("teacher_id"));
                teachers[0].setTeacherName(rs.getString("teacher_name"));
                teachers[0].setTeacherLastName(rs.getString("teacher_lastname"));
                teachers[0].setTeacherPhone(rs.getString("teacher_phone"));
                teachers[0].setTeacherEmail(rs.getString("teacher_email"));
                teachers[0].setTeacherAuth(rs.getInt("teacher_auth"));
            }
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return teachers[0];
    }

    public Teacher fetchByName(String name) {
        Teacher[] teachers = new Teacher[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM teachers WHERE teacher_name = %s", name);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                // Teacher teacher = new Teacher(teacherId, teacherName, teacherLastName, teacherEmail, teacherType);
                // teacher.setId blah blah
                teachers[0].setTeacherId(rs.getInt("teacher_id"));
                teachers[0].setTeacherName(rs.getString("teacher_name"));
                teachers[0].setTeacherLastName(rs.getString("teacher_lastname"));
                teachers[0].setTeacherPhone(rs.getString("teacher_phone"));
                teachers[0].setTeacherEmail(rs.getString("teacher_email"));
                teachers[0].setTeacherAuth(rs.getInt("teacher_auth"));
            }
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return teachers[0];
    }

    @Override
    public List<Teacher> fetchAll() throws SQLException {
        return null;
    }

    @Override
    public void Add(Teacher entity) throws SQLException {

    }

    @Override
    public void UpdateById(Teacher teacher, int id) {

    }

    @Override
    public void Delete(int id) {

    }
}
