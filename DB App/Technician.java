import java.sql.Date;

public class Technician {
    private int technicianId = 1;
    private String lastName;
    private String firstName;
    private String technicianEmail;
    private String contactNumber;
    private Date dateEmployed;

    public Technician(String lastName, String firstName, String technicianEmail, String contactNumber, Date dateEmployed) {
        this.technicianId++;
        this.lastName = lastName;
        this.firstName = firstName;
        this.technicianEmail = technicianEmail;
        this.contactNumber = contactNumber;
        this.dateEmployed = dateEmployed;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public Date getDateEmployed() {
        return dateEmployed;
    }
    public void setDateEmployed(Date dateEmployed) {
        this.dateEmployed = dateEmployed;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getTechnicianEmail() {
        return technicianEmail;
    }
    public void setTechnicianEmail(String technicianEmail) {
        this.technicianEmail = technicianEmail;
    }
    public int getTechnicianId() {
        return technicianId;
    }
    public void setTechnicianId(int technicianId) {
        this.technicianId = technicianId;
    }
}
