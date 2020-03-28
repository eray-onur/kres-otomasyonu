package meltem.models;

public class Course {
    public int courseId;
    public String courseName;
    public int courseTeacher;
    public String teacherName;
    public int teacherAuth;
    public Course(int id, String name, String teacher, int auth) {
        this.courseId = id;
        this.courseName = name;
        this.teacherName = teacher;
        this.teacherAuth = auth;
    }
    public String getTrueAuth() {
        switch(this.teacherAuth) {
            case 1:
                return "Yonetici";
            case 2:
                return "Sinif Ogretmeni";
            case 3:
                return "Brans Ders Ogretmeni";
            default:
                return "Yetkisiz";
        }
    }
}
