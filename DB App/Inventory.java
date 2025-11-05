import java.sql.Date;

public class Inventory {
    private int inventoryId;
    private int donorId;
    private int technicianId;
    private int branchId;
    private String bloodType;
    private String componentType;
    private Date recievedDate;
    private Date expiryDate;
    private String remarks;

    public Inventory(int inventoryId, int donorId, int technicianId, int branchId, String bloodType,
                     String componentType, Date recievedDate, Date expiryDate, String remarks) {
        this.inventoryId = inventoryId;
        this.donorId = donorId;
        this.technicianId = technicianId;
        this.branchId = branchId;
        this.bloodType = bloodType;
        this.componentType = componentType;
        this.recievedDate = recievedDate;
        this.expiryDate = expiryDate;
        this.remarks = remarks;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getDonorId() {
        return donorId;
    }

    public int getTechnicianId() {
        return technicianId;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public String getComponentType() {
        return componentType;
    }

    public Date getRecievedDate() {
        return recievedDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getremarks() {
        return remarks;
    }
}