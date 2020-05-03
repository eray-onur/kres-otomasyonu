package meltem.test;

import meltem.services.data_access.concrete.ClassroomCourseRepository;
import meltem.services.data_access.concrete.StudentRepository;
import meltem.services.logging.Logger;

import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        StudentRepository sr = new StudentRepository();
        Logger.LogDebug(StudentRepository.Instance.fetchAll().get(0).getParentName());
    }
}

