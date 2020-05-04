package meltem.test;

import meltem.models.Classroom;
import meltem.models.Student;
import meltem.models.Teacher;
import meltem.services.data_access.concrete.*;
import meltem.services.logging.Logger;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        StudentRepository sr = new StudentRepository();
        TeacherRepository tr = new TeacherRepository();
        ClassroomAttendanceRepository car = new ClassroomAttendanceRepository();
        ClassroomRepository cr = new ClassroomRepository();

        MeetingRepository mr = new MeetingRepository();
        BranchRepository br = new BranchRepository();
        TeacherRepository.Instance.UpdateById(new Teacher(1, "sssssssss", "xxxxxxxxxxxxxx", "222", "2222", 3), 1);
    }
}

