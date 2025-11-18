import java.util.Date;
public class Donor {

    // --- Fields ---
    private int donorId;
    private String lastName;
    private String firstName;
    private String donorEmail;
    private String contactNumber;
    private int age;
    private char sex; // M or F
    private Date birthdate;
    private String bloodType;
    private String remarks;
    private String status;

    // --- Constructors ---
    public Donor(String lastName, String firstName, String donorEmail,
                 String contactNumber, int age, char sex, Date birthdate,
                 String bloodType, String remarks, String status) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.donorEmail = donorEmail;
        this.contactNumber = contactNumber;
        this.age = age;
        this.sex = sex;
        this.birthdate = birthdate;
        this.bloodType = bloodType;
        this.remarks = remarks;
        this.status = status;
    }

    public Donor() { }

    // --- Getters and Setters ---
    public int getDonorId() {
        return donorId;
    }
    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getDonorEmail() {
        return donorEmail;
    }
    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getBloodType() {
        return bloodType;
    }
    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}