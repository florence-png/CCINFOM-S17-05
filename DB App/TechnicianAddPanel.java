import javax.swing.*;
import java.awt.*;

public class TechnicianAddPanel extends AbstractFormPanel {

    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtContactNumber;
    private JTextField txtDateEmployed; // Reflects model

    public TechnicianAddPanel() {
        super("Technician");

        btnSave.setActionCommand("TECHNICIAN_SAVE_NEW");
        btnReturn.setActionCommand("TECHNICIAN_RETURN_TO_MENU");
    }

    @Override
    protected JPanel createForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtLastName = new JTextField(20);
        txtFirstName = new JTextField(20);
        txtEmail = new JTextField(20);
        txtContactNumber = new JTextField(20);
        txtDateEmployed = new JTextField(20); // Date field

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtLastName, gbc);
        y++;

        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtFirstName, gbc);
        y++;

        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtEmail, gbc);
        y++;

        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Contact Number:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtContactNumber, gbc);
        y++;

        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Date Employed (yyyy-mm-dd):"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtDateEmployed, gbc);
        y++;

        return formPanel;
    }

    @Override
    public void clearForm() {
        txtLastName.setText("");
        txtFirstName.setText("");
        txtEmail.setText("");
        txtContactNumber.setText("");
        txtDateEmployed.setText("");
    }

    public JTextField getTxtLastName() { return txtLastName; }
    public JTextField getTxtFirstName() { return txtFirstName; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtContactNumber() { return txtContactNumber; }
    public JTextField getTxtDateEmployed() { return txtDateEmployed; }
}
