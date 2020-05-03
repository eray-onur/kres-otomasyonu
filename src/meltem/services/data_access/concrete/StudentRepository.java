package meltem.services.data_access.concrete;

import meltem.enums.LogType;
import meltem.models.Classroom;
import meltem.models.Student;
import meltem.models.User;
import meltem.services.data_access.PersistentDataService;
import meltem.services.logging.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository extends PersistentDataService<Student> {

    public static StudentRepository Instance;

    public StudentRepository() {
        if(Instance == null) {
            Instance = this;
        }
    }

    @Override
    public Student fetchById(int id) throws SQLException {
        Student[] studentList = new Student[1];
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = String.format("SELECT * FROM students WHERE student_id = %d;", id);
            Logger.LogDebug(query);
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_lastname"),
                        rs.getDate("orientation_start").toString(),
                        rs.getDate("orientation_end").toString(),
                        rs.getString("parent_name"),
                        rs.getString("parent_lastname"),
                        rs.getString("parent_phone"),
                        rs.getString("parent_email"),
                        rs.getInt("payment_monthly")
                );
                StringBuilder sb = new StringBuilder();
                sb.append("Student by the id of ");
                sb.append(student.getStudentId());
                sb.append(" was found. ");
                sb.append("Orientation start and end dates are: ");
                sb.append(student.getOrientationStart());
                sb.append(", ");
                sb.append(student.getOrientationEnd());
                Logger.LogDebug(sb.toString());
                studentList[0] = student;
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        // Returning the found user, or null if not found any.
        return studentList[0];
    }

    @Override
    public List<Student> fetchAll() throws SQLException {
        List<Student> studentList = new ArrayList<Student>();
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM students";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_lastname"),
                        rs.getDate("orientation_start").toString(),
                        rs.getDate("orientation_end").toString(),
                        rs.getString("parent_name"),
                        rs.getString("parent_lastname"),
                        rs.getString("parent_phone"),
                        rs.getString("parent_email"),
                        rs.getInt("payment_monthly")
                );
                studentList.add(student);
                Logger.Log(LogType.Debug, String.valueOf(student.getParentEmail()));
            }
            // Bitis
            this.close();
        }
        catch (Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
        return studentList;
    }

    @Override
    public void Add(Student entity) throws SQLException {
        try {
            this.connect();
            String sql = "INSERT INTO students VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setInt(1, entity.getStudentId());
            pst.setString(2, entity.getStudentName());
            pst.setString(3, entity.getStudentLastName());
            pst.setDate(4, Date.valueOf(entity.getOrientationStart()));
            pst.setDate(5, Date.valueOf(entity.getOrientationEnd()));
            pst.setString(6, entity.getParentName());
            pst.setString(7, entity.getParentLastName());
            pst.setString(8, entity.getParentNumber());
            pst.setString(9, entity.getParentEmail());
            pst.setInt(10, entity.getPaymentMonthly());
            int i = pst.executeUpdate();
            Logger.LogDebug(String.valueOf(i));
            this.close();
        }
        catch(Exception ex) {
            Logger.Log(LogType.Error, ex.getMessage());
        }
    }

    @Override
    public void UpdateById(Student entity, int id) throws SQLException {
        try {
            this.connect();
            // Tum sorgu yollama operasyonlari bu iki yorum arasinda gerceklestirilecek.
            String sql = "UPDATE students SET student_name = ?, student_lastname = ?, orientation_start = ?, orientation_end = ?, parent_name = ?, parent_lastname = ?, parent_phone = ?, parent_email = ?, payment_monthly = ? WHERE student_id = ?";
            PreparedStatement pst = this.connection.prepareStatement(sql);
            pst.setString(1, entity.getStudentName());
            pst.setString(2, entity.getStudentLastName());
            pst.setDate(3, Date.valueOf(entity.getOrientationStart()));
            pst.setDate(4, Date.valueOf(entity.getOrientationEnd()));
            pst.setString(5, entity.getParentName());
            pst.setString(6, entity.getParentLastName());
            pst.setString(7, entity.getParentNumber());
            pst.setString(8, entity.getParentEmail());
            pst.setInt(9, entity.getPaymentMonthly());
            pst.setInt(10, id);
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
            String sql = "DELETE FROM students WHERE student_id = ?";
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
