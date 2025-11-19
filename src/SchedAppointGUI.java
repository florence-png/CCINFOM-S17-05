import java.awt.*;
import java.util.Date;
import java.util.List;
import javax.swing.*;

public class SchedAppointGUI extends JPanel {

	private JComboBox<Donor> donorCombo;// for donor selection
	private JComboBox<Bloodbank> branchCombo;// for branch selection
	private final JSpinner dateSpinner; // for date selection
	private final JButton confirmBtn; // button to confirm appointment
	private final JButton returnBtn; // button to return to previous menu

	public SchedAppointGUI() {
		super(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

		// Title
		JLabel title = new JLabel("Schedule an Appointment");
		title.setFont(new Font("SansSerif", Font.BOLD, 28));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);

		// Center form
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

		// Donor
		JPanel row1 = new JPanel(new BorderLayout(8, 8));
		JLabel lDonor = new JLabel("Choose Donor:");
		lDonor.setPreferredSize(new Dimension(140, 30));
		donorCombo = new JComboBox<>();
		donorCombo.setPreferredSize(new Dimension(320, 30));
		donorCombo.setRenderer(new DefaultListCellRenderer() {// custom renderer to show donor names    
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				// Simplify instanceof checks
				if (value instanceof Donor d) {
					setText(d.getFirstName() + " " + d.getLastName());
				}
				return this;
			}
		});
		row1.add(lDonor, BorderLayout.WEST);
		row1.add(donorCombo, BorderLayout.CENTER);
		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row1);
		center.add(Box.createVerticalStrut(16));

		// Branch
		JPanel row2 = new JPanel(new BorderLayout(8, 8));
		JLabel lBranch = new JLabel("Choose Branch:");
		lBranch.setPreferredSize(new Dimension(140, 30));
		branchCombo = new JComboBox<>();
		branchCombo.setPreferredSize(new Dimension(320, 30));
		branchCombo.setRenderer(new DefaultListCellRenderer() {// custom renderer to show branch names  
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				// Simplify instanceof checks
				if (value instanceof Bloodbank b) {// set branch name
					setText(b.getBranchName());
				}
				return this;
			}
		});
		row2.add(lBranch, BorderLayout.WEST);
		row2.add(branchCombo, BorderLayout.CENTER);
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row2);
		center.add(Box.createVerticalStrut(16));

		// Date
		JPanel row3 = new JPanel(new BorderLayout(8, 8));
		JLabel lDate = new JLabel("Choose Date:");
		lDate.setPreferredSize(new Dimension(140, 30));
		dateSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));// create spinner with current date
		JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");// set date format
		dateSpinner.setEditor(dateEditor);// set date format
		dateSpinner.setPreferredSize(new Dimension(320, 30));
		row3.add(lDate, BorderLayout.WEST);
		row3.add(dateSpinner, BorderLayout.CENTER);
		row3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row3);

		add(center, BorderLayout.CENTER);

		// Bottom buttons
		JPanel bottom = new JPanel();// button panel
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.setBorder(BorderFactory.createEmptyBorder(10, 120, 20, 120));

		confirmBtn = new JButton("Confirm Appointment");// confirm appointment
		confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmBtn.setPreferredSize(new Dimension(300, 40));
		confirmBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		confirmBtn.setActionCommand("BTN_CONFIRM_APPT");

		bottom.add(confirmBtn);
		bottom.add(Box.createVerticalStrut(12));

		returnBtn = new JButton("Return");
		returnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		returnBtn.setPreferredSize(new Dimension(300, 40));
		returnBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		returnBtn.setActionCommand("BTN_RETURN_TO_MENU");

		bottom.add(returnBtn);

		add(bottom, BorderLayout.SOUTH);
	}

	// populate dropdowns
	public void populateDonorCombo(List<Donor> donors) {
		DefaultComboBoxModel<Donor> model = new DefaultComboBoxModel<>();
		for (Donor d : donors) model.addElement(d);
		donorCombo.setModel(model);
	}

	public void populateBranchCombo(List<Bloodbank> branches) {
		DefaultComboBoxModel<Bloodbank> model = new DefaultComboBoxModel<>();
		for (Bloodbank b : branches) model.addElement(b);
		branchCombo.setModel(model);
	}

	// getters to be used by controller
	public JButton getConfirmButton() { return confirmBtn; }
	public JButton getReturnButton() { return returnBtn; }

	public Integer getSelectedDonorId() {// get selected donor ID
		Donor d = (Donor) donorCombo.getSelectedItem();
		return (d == null) ? null : d.getDonorId();
	}

	public Integer getSelectedBranchId() {// get selected branch ID
		Bloodbank b = (Bloodbank) branchCombo.getSelectedItem();
		return (b == null) ? null : b.getBranchId();
	}

	public java.sql.Date getSelectedDateSql() {// get selected date as SQL date
		Date d = (Date) dateSpinner.getValue();
		return new java.sql.Date(d.getTime());
	}
}
