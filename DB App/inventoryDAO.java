import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryDAO {
    private Connection conn;

    public InventoryDAO(Connection conn){
        this.conn = conn;
    }

    public boolean addInventory(Inventory inventory){ // Add new inventory record
        String sql = "INSERT INTO inventory (blood_type, component_type, received_date, expiry_date, status) VALUES (?, ?, ?, ?, 'Active')";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, inventory.getBlood_type());
            pstmt.setString(2, inventory.getComponent_type());
            pstmt.setDate(3, inventory.getRecievedDate());
            pstmt.setDate(4, inventory.getExpiry_date());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Inventory> getAllActiveInventory(){ // Retrieve all active inventory records
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE status='Active'";
        try(Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                Inventory inv = mapResultSetToInventory(rs);
                inventoryList.add(inv);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return inventoryList;
    }

    public List<Inventory> searchInventory(String searchTerm){ // Search inventory by blood type or component type
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM inventory WHERE status='Active' AND (blood_type LIKE ? OR component_type LIKE ?)";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            String pattern = "%" + searchTerm + "%";  // matches anywhere in the string
            pstmt.setString(1, pattern);
            pstmt.setString(2, pattern);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                Inventory inv = mapResultSetToInventory(rs);
                inventoryList.add(inv);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return inventoryList;
    }

    public boolean updateInventory(Inventory inventory){ // Update existing inventory record
        String sql = "UPDATE inventory SET blood_type=?, component_type=?, received_date=?, expiry_date=? WHERE inventory_id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, inventory.getBlood_type());
            pstmt.setString(2, inventory.getComponent_type());
            pstmt.setDate(3, inventory.getRecievedDate());
            pstmt.setDate(4, inventory.getExpiry_date());
            pstmt.setInt(5, inventory.getInventory_id());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean archiveInventory(int inventoryId){ // Archive inventory record
        String sql = "UPDATE inventory SET status='Archived' WHERE inventory_id=?";
        try(PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, inventoryId);
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private Inventory mapResultSetToInventory(ResultSet rs) throws SQLException{ // Helper function
        Inventory inv = new Inventory(
            rs.getString("blood_type"),
            rs.getString("component_type"),
            rs.getDate("expiry_date"),
            rs.getDate("received_date")
        );
        inv.setInventory_id(rs.getInt("inventory_id"));
        return inv;
    }
}