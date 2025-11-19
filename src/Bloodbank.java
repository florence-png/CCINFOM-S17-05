public class Bloodbank {
    private int branchId;
    private String branchName;
    private String street;
    private String city;
    private String contactNumber;
    private String status;

    public Bloodbank(int branchId, String branchName, String street, String city, String contactNumber, String status) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.street = street;
        this.city = city;
        this.contactNumber = contactNumber;
        this.status = status;
    }

    public int getBranchId() {
        return branchId;
    }
    public String getBranchName() {
        return branchName;
    }
    public String getStreet() {
        return street;
    }
    public String getCity() {
        return city;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public String getStatus() { return status; }
}