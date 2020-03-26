package meltem.models;

public class Classroom {
    private int classroomId;
    private String classroomName;
    private int capacity;

    public Classroom(int id, String name, int capacity) {
        this.classroomId = id;
        this.classroomName = name;
        this.capacity = capacity;
    }
    public Classroom() {

    }

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
