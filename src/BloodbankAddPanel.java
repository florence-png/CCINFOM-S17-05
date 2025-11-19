import javax.swing.*;
import java.awt.*;

public class BloodbankAddPanel extends AbstractFormPanel {
    private JTextField txtBranchName;
    private JTextArea txtStreet;
    private JTextField txtCity;
    private JTextField txtContactNumber;

    public BloodbankAddPanel() {
        super("Branch");

        btnSave.setActionCommand("HOSPITAL_SAVE_NEW");
        btnReturn.setActionCommand("HOSPITAL_RETURN_TO_MENU");
    }

    @Override
    protected JPanel createForm() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // components
        txtBranchName = new JTextField(20);
        txtStreet = new JTextArea(3, 20);
        txtCity = new JTextField(20);
        txtContactNumber = new JTextField(20);

        int y = 0;
        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("Branch Name:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtBranchName, gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridy = y; formPanel.add(new JLabel("Street:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(new JScrollPane(txtStreet), gbc);

        gbc.gridx = 0; gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = y; formPanel.add(new JLabel("City:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridy = y++; formPanel.add(txtCity, gbc);

        gbc.gridx = 0; gbc.gridy = y; gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel("Contact Number:"), gbc);
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        formPanel.add(txtContactNumber, gbc);

        return formPanel;
    }

    @Override
    public void clearForm() {
        txtBranchName.setText("");
        txtStreet.setText("");
        txtCity.setText("");
        txtContactNumber.setText("");
    }

    // Getters for the Controller
    public JTextField getTxtBranchName() {
        return txtBranchName;
    }
    public JTextArea getTxtStreet() {
        return txtStreet;
    }
    public JTextField getTxtCity() {
        return txtCity;
    }
    public JTextField getTxtContactNumber() {
        return txtContactNumber;
    }
}
