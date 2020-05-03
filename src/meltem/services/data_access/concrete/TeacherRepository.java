package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
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
        Teacher teacher = new Teacher(99, "", "", "", "", 99);
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM teachers WHERE teacher_id = %d", id);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                // teacher.setId blah blah
                teacher.setTeacherId(rs.getInt(1));
                teacher.setTeacherName(rs.getString(2));
                teacher.setTeacherLastName(rs.getString(3));
                teacher.setTeacherPhone(rs.getString(4));
                teacher.setTeacherEmail(rs.getString(5));
                teacher.setTeacherAuth(rs.getInt(6));
            }
            teachers[0] = teacher;
            // Bitis
            this.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return teachers[0];
    }

    public Teacher fetchByName(String name) {
        Logger.LogDebug(name + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
        Teacher[] teachers = new Teacher[1];
        Teacher teacher = new Teacher(-1, "", "", "", "", -1);
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT TOP 1 * FROM teachers WHERE teacher_name = '%s'", name);

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                teacher.setTeacherId(rs.getInt(1));
                teacher.setTeacherName(rs.getString(2));
                teacher.setTeacherLastName(rs.getString(3));
                teacher.setTeacherPhone(rs.getString(4));
                teacher.setTeacherEmail(rs.getString(5));
                teacher.setTeacherAuth(rs.getInt(6));
            }
            if(teacher.getTeacherId() != -1) {
                teachers[0] = teacher;
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
    public void Add(Teacher entity)  {
        try {
            this.connect();
            String sql = "INSERT INTO teachers(teacher_name, teacher_lastname, teacher_phone, teacher_email, teacher_auth) VALUES (?,?,?,?,?);";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getTeacherName());
            pst.setString(2, entity.getTeacherLastName());
            pst.setString(3, entity.getTeacherPhone());
            pst.setString(4, entity.getTeacherEmail());
            pst.setInt(5, 2);
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Teacher teacher, int id) {

    }

    @Override
    public void Delete(int id) {

    }
}
