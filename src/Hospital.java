public class Hospital {
    private int hospitalId;
    private String hospitalName;
    private String street;
    private String city;
    private String region;
    private String contactNumber;
    private String status;

    public Hospital(int hospitalId, String hospitalName, String street, String city, String region, String contactNumber, String status) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.street = street;
        this.city = city;
        this.region = region;
        this.contactNumber = contactNumber;
        this.status = status;
    }

    public int getHospitalId() {
        return hospitalId;
    }
    public String getHospitalName() {
        return hospitalName;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getRegion() {
        return region;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getStatus() { return status; }
}
