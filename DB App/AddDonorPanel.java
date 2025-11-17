import javax.swing.*;
import java.awt.*;
import org.jdatepicker.JDatePicker;
import java.util.Calendar;

public class AddDonorPanel extends AbstractFormPanel {

    // --- Donor-Specific Components ---
    private JTextField txtLastName;
    private JTextField txtFirstName;
    private JTextField txtEmail;
    private JTextField txtContactNumber;
    private JComboBox<String> comboSex;
    private JDatePicker datePicker;
    private JComboBox<String> comboBloodType;
    private JTextArea txtRemarks;

    public AddDonorPanel() {
        super("Donor");

        // Set action commands
        btnSave.setActionCommand("DONOR_SAVE_NEW");
        btnReturn.setActionCommand("DONOR_RETURN_TO_MENU");
    }

    /**
     * This is the required method from the abstract class.
     * We just build and return the panel with our specific fields.
     */
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

        Calendar today = Calendar.getInstance();
        datePicker = new JDatePicker(today);
        datePicker.setTextEditable(true);
        datePicker.setShowYearButtons(true);

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
        gbc.gridy = y++; formPanel.add(datePicker, gbc);

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
        datePicker.getModel().setValue(null);
        comboBloodType.setSelectedIndex(0);
        txtRemarks.setText("");
    }

    // --- Getters for the Controller ---
    public JTextField getTxtLastName() { return txtLastName; }
    public JTextField getTxtFirstName() { return txtLastName; }
    public JTextField getTxtEmail() { return txtEmail; }
    public JTextField getTxtContactNumber() { return txtContactNumber; }
    public JComboBox<String> getComboSex() { return comboSex; }
    public JDatePicker getDatePicker() { return datePicker; }
    public JComboBox<String> getComboBloodType() { return comboBloodType; }
    public JTextArea getTxtRemarks() { return txtRemarks; }
}