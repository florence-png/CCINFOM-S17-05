public class hospitals {
    private int hospitalId;
    private String name;
    private String address;
    private String city;
    private String contactNumber;
}

public hospitals(int hospitalId, String name, String address, String city, String contactNumber) {
    this.hospitalId = hospitalId;
    this.name = name;
    this.address = address;
    this.city = city;
    this.contactNumber = contactNumber;
}

public int getHospitalId() {
    return hospitalId;
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

public String getContactNumber() {
    return contactNumber;
}