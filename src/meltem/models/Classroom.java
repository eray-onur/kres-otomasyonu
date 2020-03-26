package meltem.models;

public class Classroom {
    private int classroomId;
    private String classroomName;
    private int capacity;

    public void setClassroomId(int classroomId) {
        this.classroomId = classroomId;
    }
    public int getClassroomId() {

        return this.classroomId;
    }
    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
    public String getClassroomName() {
        return this.classroomName;
    }
}
