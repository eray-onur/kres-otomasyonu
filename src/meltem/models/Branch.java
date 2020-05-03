package meltem.models;

public class Branch {
    private int branchId;
    private String branchCourseName;
    private int branchTeacher;
    private int branchCapacity;

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchCourseName() {
        return branchCourseName;
    }

    public void setBranchCourseName(String branchCourseName) {
        this.branchCourseName = branchCourseName;
    }

    public int getBranchTeacher() {
        return branchTeacher;
    }

    public void setBranchTeacher(int branchTeacher) {
        this.branchTeacher = branchTeacher;
    }

    public int getBranchCapacity() {
        return branchCapacity;
    }

    public void setBranchCapacity(int branchCapacity) {
        this.branchCapacity = branchCapacity;
    }

    public Branch(int id, String courseName, int teacher, int capacity) {
        this.branchId = id;
        this.branchCourseName = courseName;
        this.branchTeacher = teacher;
        this.branchCapacity = capacity;
    }
}
