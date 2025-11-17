import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class AbstractManagePanel<T> extends JPanel {

    // --- Common Components ---
    protected JTextField txtSearch;
    protected JButton btnSearch;
    protected JPanel listPanel; // The panel *inside* the scroll pane
    protected JButton btnRefresh;
    protected JButton btnReturn;

    public AbstractManagePanel(String entityName) {
        super(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // --- Top Panel (Header & Search) ---
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(createHeader("Manage " + entityName + " Record"), BorderLayout.NORTH);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtSearch = new JTextField(20);
        btnSearch = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);

        // --- Center List Area ---
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        // --- Bottom Button Panel ---
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnRefresh = new JButton("Refresh List");
        btnReturn = new JButton("Return");
        southPanel.add(btnRefresh);
        southPanel.add(btnReturn);
        add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * This is the main public method the controller will call.
     * It clears the list and calls the abstract row builder for each entity.
     */
    public void displayEntities(List<T> entities) {
        listPanel.removeAll(); // Clear the old list

        if (entities.isEmpty()) {
            listPanel.add(new JLabel("No records found."));
        } else {
            for (T entity : entities) {
                // Call the abstract method to create the specific row
                JPanel rowPanel = createEntityRowPanel(entity);
                listPanel.add(rowPanel);
                listPanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            }
        }

        listPanel.revalidate();
        listPanel.repaint();
    }

    // --- Abstract Method ---

    /**
     * Subclasses MUST implement this.
     * Create and return a JPanel that represents one row in the list
     * (like your Image 4 design with Name, Type, and buttons).
     *
     * @param entity The entity object (e.g., a Donor)
     * @return A JPanel representing that entity's row.
     */
    protected abstract JPanel createEntityRowPanel(T entity);

    // --- Getters for the Controller ---
    public JTextField getTxtSearch() {
        return txtSearch;
    }

    public JButton getBtnSearch() {
        return btnSearch;
    }

    public JButton getBtnRefresh() {
        return btnRefresh;
    }

    public JButton getBtnReturn() {
        return btnReturn;
    }

    // Header helper
    private JLabel createHeader(String title) {
        JLabel lbl = new JLabel(title, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 28));
        return lbl;
    }
}