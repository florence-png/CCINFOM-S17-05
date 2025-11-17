import javax.swing.*;
import java.awt.*;

public class DonorAddPanel extends AbstractFormPanel {

    // --- Donor-Specific Components ---
    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtContactNumber;
    private JComboBox<String> comboSex;
    private JTextField txtBirthdate;
    private JComboBox<String> comboBloodType;
    private JTextArea txtRemarks;

    public DonorAddPanel() {
        super("Donor"); // Tell the base class the entity name

        // Set action commands
        btnSave.setActionCommand("DONOR_SAVE_NEW");
        btnReturn.setActionCommand("DONOR_RETURN_TO_MENU");
    }

    @Override
    protected JPanel createForm() {
        JPanel formPanel = new JPanel(new GridBagLayout()); // Use a good layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Init components
        txtLastName = new JTextField(20);
        txtFirstName = new JTextField(20);
        txtEmail = new JTextField(20);
        txtContactNumber = new JTextField(20);
        comboSex = new JComboBox<>(new String[]{"Male", "Female"});
        txtBirthdate = new JTextField(20); // Hint: Use JSpinner or JDatePicker
        comboBloodType = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        txtRemarks = new JTextArea(3, 20);

        // Add components using GridBagLayout
        int y = 0;
        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtLastName, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtFirstName, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtEmail, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Contact No.:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtContactNumber, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Sex:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(comboSex, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Birthdate:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtBirthdate, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Blood Type:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(comboBloodType, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridy = y; formPanel.add(new JLabel("Remarks:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(new JScrollPane(txtRemarks), gbc);

        return formPanel;
    }

    @Override
    public void clearForm() {
        txtLastName.setText("");
        txtFirstName.setText("");
        txtEmail.setText("");
        txtContactNumber.setText("");
        comboSex.setSelectedIndex(0);
        txtBirthdate.setText("");
        comboBloodType.setSelectedIndex(0);
        txtRemarks.setText("");
    }

    // --- Getters for the Controller ---
    public JTextField getTxtLastName() { return txtLastName; }
    public JTextField getTxtFirstName() { return txtFirstName; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtContactNumber() { return txtContactNumber; }
    public JComboBox<String> getComboSex() { return comboSex; }
    public JTextField getTxtBirthdate() { return txtBirthdate; }
    public JComboBox<String> getComboBloodType() { return comboBloodType; }
    public JTextArea getTxtRemarks() { return txtRemarks; }
}