import javax.swing.*;
import java.awt.*;

/**
 * The template for the "Entity Details" screen.
 *
 * @param <T> The entity type being displayed (e.g., Donor, Technician)
 */
public abstract class AbstractDetailPanel<T> extends JPanel {

    // --- Common Components ---
    protected JLabel lblHeader;
    protected JPanel infoPanel;
    protected JPanel historyPanel;
    protected JTextArea txtHistory;
    protected JButton btnReturn;

    public AbstractDetailPanel() {
        super(new BorderLayout(10, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // --- Header (will be populated by subclass) ---
        lblHeader = new JLabel("Entity Details");
        lblHeader.setFont(new Font("SansSerif", Font.BOLD, 28));
        add(lblHeader, BorderLayout.NORTH);

        // --- Center Panel (Info + History) ---
        JPanel centerPanel = new JPanel(new BorderLayout(10, 20));

        // Info Panel (will be populated by subclass)
        infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        centerPanel.add(infoPanel, BorderLayout.NORTH);

        // History Panel (common structure)
        historyPanel = new JPanel(new BorderLayout(5, 5));
        historyPanel.add(new JLabel("History"), BorderLayout.NORTH);
        txtHistory = new JTextArea(8, 30);
        txtHistory.setEditable(false);
        historyPanel.add(new JScrollPane(txtHistory), BorderLayout.CENTER);
        centerPanel.add(historyPanel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        // --- Bottom Button ---
        btnReturn = new JButton("Return");
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southPanel.add(btnReturn);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * This is the main public method the controller will call.
     * It triggers the abstract methods to populate the panel.
     */
    public void loadEntityData(T entity) {
        // Call the abstract methods to be filled by the subclass
        lblHeader.setText(getHeaderTitle(entity));
        populateInfoPanel(entity);
        populateHistoryPanel(entity);
    }

    // --- Abstract Methods ---
    /**
     * Subclasses MUST implement this.
     * @return The string for the main header (e.g., "Donor: Dela Cruz, Juan")
     */
    protected abstract String getHeaderTitle(T entity);

    /**
     * Subclasses MUST implement this.
     * Clear and add all specific JLabels to the `infoPanel`.
     * (e.g., "Type: O+", "Sex: Male", etc.)
     */
    protected abstract void populateInfoPanel(T entity);

    /**
     * Subclasses MUST implement this.
     * Fetch the relevant history and set the text of `txtHistory`.
     * (e.g., Donation history for a Donor, Work history for a Technician)
     */
    protected abstract void populateHistoryPanel(T entity);

    // --- Getter for the Controller ---
    public JButton getBtnReturn() { return btnReturn; }
}