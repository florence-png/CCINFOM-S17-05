import java.sql.Date;

public class Technician {
    private int technician_id = 1;
    private String last_name;
    private String first_name;
    private String technician_email;
    private String contact_number;
    private Date date_employed;

    public Technician(String last_name, String first_name, String technician_email, String contact_number, Date date_employed) {
        this.technician_id++;
        this.last_name = last_name;
        this.first_name = first_name;
        this.technician_email = technician_email;
        this.contact_number = contact_number;
        this.date_employed = date_employed;
    }

    public String getContact_number() {
        return contact_number;
    }
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    public Date getDate_employed() {
        return date_employed;
    }
    public void setDate_employed(Date date_employed) {
        this.date_employed = date_employed;
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
    public String getTechnician_email() {
        return technician_email;
    }
    public void setTechnician_email(String technician_email) {
        this.technician_email = technician_email;
    }
    public int getTechnician_id() {
        return technician_id;
    }
    public void setTechnician_id(int technician_id) {
        this.technician_id = technician_id;
    }
}

