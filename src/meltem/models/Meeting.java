package meltem.models;

public class Meeting {
    public int meetingId;
    public String studentFullName;
    public String meetingTitle;
    public String meetingDescription;
    public String meetingDate;
    public Meeting(int id, String title, String description, String date) {
        this.meetingId = id;
        this.meetingTitle = title;
        this.meetingDescription = description;
        this.meetingDate = date;
    }
}
