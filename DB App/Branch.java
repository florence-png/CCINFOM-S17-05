public class Branch {
    private int branchId;
    private String name;
    private String location;
    private String contactNumber;

    public Branch(int branchId, String name, String location, String contactNumber) {
        this.branchId = branchId;
        this.name = name;
        this.location = location;
        this.contactNumber = contactNumber;
    }

    public int getBranchId() {
        return branchId;
    }
    public String getName() {
        return name;
    }
    public String getLocation() {
        return location;
    }
    public String getContactNumber() {
        return contactNumber;
    }
}
