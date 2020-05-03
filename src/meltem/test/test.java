package meltem.test;

import meltem.models.Student;
import meltem.services.data_access.concrete.ClassroomAttendanceRepository;
import meltem.services.data_access.concrete.ClassroomCourseRepository;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.data_access.concrete.TeacherRepository;
import meltem.services.logging.Logger;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        StudentRepository sr = new StudentRepository();
        TeacherRepository tr = new TeacherRepository();
        ClassroomAttendanceRepository car = new ClassroomAttendanceRepository();
        StudentRepository.Instance.fetchById(1);
    }
}

