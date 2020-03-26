package meltem.models;

import java.util.Calendar;
import java.util.Date;

public class Student {
    public int studentId;
    public String studentName;
    public String studentLastName;
    public String orientationStart;
    public String orientationEnd;
    public String parentName;
    public String parentLastName;
    public String parentNumber;
    public String parentEmail;

    public Student(int id,
                   String name,
                   String lastName,
                   String orientationStart,
                   String orientationEnd
    ) {
        this.studentId = id;
        this.studentName = name;
        this.studentLastName = lastName;
        this.orientationStart = orientationStart;
        this.orientationEnd = orientationEnd;
    }
    public Student(int id,
                   String name,
                   String lastName,
                   String orientationStart,
                   String orientationEnd,
                   String parentName,
                   String parentLastName,
                   String parentNumber,
                   String parentEmail
    ) {
        this.studentId = id;
        this.studentName = name;
        this.studentLastName = lastName;
        this.orientationStart = orientationStart;
        this.orientationEnd = orientationEnd;
        this.parentName = parentName;
        this.parentLastName = parentLastName;
        this.parentNumber = parentNumber;
        this.parentEmail = parentEmail;
    }

}
