package KarrosTechPJ.Karros.Web;

public class Request {	
	
	private String requeststatus;
	private String datesubmitted;
	private String requester;
	private String studentid;
	private String firstname;
	private String lastname;
	private String dob;
	private String notes;
	private String user;
	
	public Request(String requeststatus, String datesubmitted, String requester, String studentid, 
					String firstname, String lastname, String dob, String notes, String user) {
        this.requeststatus = requeststatus;
        this.datesubmitted = datesubmitted;
        this.requester = requester;
        this.studentid = studentid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.notes = notes;
        this.user = user;
    }
	
	public String getRequeststatus() {
		return requeststatus;
	}
	public void setRequeststatus(String requeststatus) {
		this.requeststatus = requeststatus;
	}
	public String getDatesubmitted() {
		return datesubmitted;
	}
	public void setDatesubmitted(String datesubmitted) {
		this.datesubmitted = datesubmitted;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}
	public String getStudentid() {
		return studentid;
	}
	public void setStudentid(String studentid) {
		this.studentid = studentid;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
