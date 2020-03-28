package meltem.models;

public class Branch {
    public int branchId;
    public int branchCourseId;
    public int branchTeacher;
    public String nBranchCourse;
    public String nBranchTeacherFullName;

    public Branch(String course, String teacher) {
        this.nBranchCourse = course;
        this.nBranchTeacherFullName = teacher;
    }
}
