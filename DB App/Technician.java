import java.sql.Date;

public class Technician {
    private int technicianId;
    private String lastName;
    private String firstName;
    private String technicianEmail;
    private String contactNumber;
    private int age;
    private char sex;
    private Date dateEmployed;
    private String status;

    public Technician(int technicianId, String lastName, String firstName, String technicianEmail, String contactNumber, int age, char sex, Date dateEmployed, String status) {
        this.technicianId = technicianId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.technicianEmail = technicianEmail;
        this.age = age;
        this.sex = sex;
        this.contactNumber = contactNumber;
        this.dateEmployed = dateEmployed;
        this.status = status;
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
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public char getSex() {
        return sex;
    }
    public void setSex(char sex) {
        this.sex = sex;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
