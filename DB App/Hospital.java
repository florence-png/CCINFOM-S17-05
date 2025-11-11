public class Hospital {
    private int hospital_id;
    private String name;
    private String address;
    private String city;
    private String contact_number;

    public Hospital(int hospitalId, String name, String address, String city, String contactNumber) {
        this.hospital_id = hospitalId;
        this.name = name;
        this.address = address;
        this.city = city;
        this.contact_number = contactNumber;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getContact_number() {
        return contact_number;
    }
}