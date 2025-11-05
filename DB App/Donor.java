import java.sql.Date;

public class Donor {
    private int donor_id = 1;
    private String last_name;
    private String first_name;
    private String contact_number;
    private int age;
    private boolean sex;
    private Date birthdate;
    private String blood_type;
    private String remarks;

    public Donor(String last_name, String first_name, String contact_number, int age,
                 boolean sex, Date birthdate, String blood_type, String remarks) {
        this.last_name = last_name;
        this.first_name = first_name;
        this.contact_number = contact_number;
        this.age = age;
        this.sex = sex;
        this.birthdate = birthdate;
        this.blood_type = blood_type;
        this.remarks = remarks;
        this.donor_id++;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public Date getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getBlood_type() {
        return blood_type;
    }
    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }
    public String getContact_number() {
        return contact_number;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public int getDonor_id() {
        return donor_id;
    }
    public void setDonor_id(int donor_id) {
        this.donor_id = donor_id;
    }
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
