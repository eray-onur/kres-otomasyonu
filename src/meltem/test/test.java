package meltem.test;

import meltem.models.Classroom;
import meltem.models.Student;
import meltem.services.data_access.concrete.*;
import meltem.services.logging.Logger;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        StudentRepository sr = new StudentRepository();
        TeacherRepository tr = new TeacherRepository();
        ClassroomAttendanceRepository car = new ClassroomAttendanceRepository();
        ClassroomRepository cr = new ClassroomRepository();
        ClassroomRepository.Instance.Add(new Classroom(10, "sadf",
                "dsafdfg", "asdfsfdg", 25), ClassroomRepository.Instance.returnLast().getClassroomId());
        MeetingRepository mr = new MeetingRepository();
        Logger.LogDebug(String.valueOf(MeetingRepository.Instance.fetchAll().get(0).getMeetingSummary()));
    }
}

