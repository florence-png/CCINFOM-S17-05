import javax.swing.*;
import java.awt.*;

public abstract class AbstractFormPanel extends JPanel {
    protected JButton btnSave;
    protected JButton btnReturn;

    public AbstractFormPanel(String entityName) {
        super(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Header
        add(createHeader("Add " + entityName + " Record"), BorderLayout.NORTH);

        // Center form (provided by the subclass)
        add(createForm(), BorderLayout.CENTER); // <-- This is the abstract part

        // South buttons
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSave = new JButton("Save Record");
        btnReturn = new JButton("Return");
        southPanel.add(btnSave);
        southPanel.add(btnReturn);
        add(southPanel, BorderLayout.SOUTH);
    }

    // This is the "template method" that subclasses must fill in
    protected abstract JPanel createForm();

    // Abstract method to clear the form
    public abstract void clearForm();

    // Getters
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnReturn() { return btnReturn; }

    // Header helper
    private JLabel createHeader(String title) {
        JLabel lbl = new JLabel(title, SwingConstants.CENTER);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 28));
        return lbl;
    }
}