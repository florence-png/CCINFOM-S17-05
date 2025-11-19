import java.awt.*;
import java.util.List;
import javax.swing.*;

public class AssignTechGUI extends JPanel {

    private static final int COMBO_WIDTH = 760;// standard combo box width

	private final JComboBox<Technician> techCombo;// technician selection
	private final JComboBox<String> apptCombo;// appointment selection
	private final JButton confirmBtn;// confirm assignment button
	private final JButton returnBtn;// return button

	public AssignTechGUI() {// constructor
		super(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

		// Title
		JLabel title = new JLabel("Assign a Technician");
		title.setFont(new Font("SansSerif", Font.BOLD, 28));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);

		// Center form
		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));

		// Technician row
		JPanel row1 = new JPanel(new BorderLayout(8, 8));
		JLabel lTech = new JLabel("Choose Technician:");
		lTech.setPreferredSize(new Dimension(170, 30));
		techCombo = new JComboBox<>();
		techCombo.setPreferredSize(new Dimension(COMBO_WIDTH, 30));
		techCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		techCombo.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel lab) {
					if (value instanceof Technician t) {
						lab.setText(t.getFirstName() + " " + t.getLastName());
						lab.setToolTipText(lab.getText());
					}
					lab.setHorizontalAlignment(SwingConstants.LEFT);
				}
				return c;
			}
		});
		row1.add(lTech, BorderLayout.WEST);
		row1.add(techCombo, BorderLayout.CENTER);
		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row1);
		center.add(Box.createVerticalStrut(16));

		// Appointment row
		JPanel row2 = new JPanel(new BorderLayout(8, 8));
		JLabel lAppt = new JLabel("Choose Appointment:");
		lAppt.setPreferredSize(new Dimension(170, 30));
		apptCombo = new JComboBox<>();
		apptCombo.setPreferredSize(new Dimension(COMBO_WIDTH, 30));
		apptCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		apptCombo.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (c instanceof JLabel lab) {
					if (value != null) lab.setToolTipText(value.toString());
					lab.setHorizontalAlignment(SwingConstants.LEFT);
				}
				return c;
			}
		});
		row2.add(lAppt, BorderLayout.WEST);
		row2.add(apptCombo, BorderLayout.CENTER);
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row2);

		add(center, BorderLayout.CENTER);

		// Bottom buttons
		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.setBorder(BorderFactory.createEmptyBorder(10, 120, 20, 120));

		confirmBtn = new JButton("Confirm Assignment");
		confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmBtn.setPreferredSize(new Dimension(300, 40));
		confirmBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		confirmBtn.setActionCommand("BTN_CONFIRM_ASSIGN");

		bottom.add(confirmBtn);
		bottom.add(Box.createVerticalStrut(12));

		returnBtn = new JButton("Return");
		returnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		returnBtn.setPreferredSize(new Dimension(300, 40));
		returnBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		returnBtn.setActionCommand("BTN_RETURN_TO_TRANSACTIONS");

		bottom.add(returnBtn);

		add(bottom, BorderLayout.SOUTH);
	}

	public void populateTechnicians(List<Technician> techs) {// populate technician combo box
		DefaultComboBoxModel<Technician> model = new DefaultComboBoxModel<>(); // model for techs
		for (Technician t : techs) model.addElement(t);
		techCombo.setModel(model);
		String longestName = "";
		for (Technician t : techs) {
			String name = t.getFirstName() + " " + t.getLastName();
			if (name.length() > longestName.length()) longestName = name;
		}
		if (!longestName.isEmpty()) {
			FontMetrics fm = techCombo.getFontMetrics(techCombo.getFont());
			int px = fm.stringWidth(longestName) + 48;
			Dimension d = new Dimension(Math.max(px, COMBO_WIDTH), 30);
			techCombo.setPreferredSize(d);
			techCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		}
	}

	public void populateAppointments(List<String> appts) {// populate appointment combo box
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // model for appt strings
		for (String s : appts) model.addElement(s);
		apptCombo.setModel(model);
		String longest = "";
		for (String s : appts) if (s != null && s.length() > longest.length()) longest = s;
		if (!longest.isEmpty()) {
			FontMetrics fm = apptCombo.getFontMetrics(apptCombo.getFont());
			int px = fm.stringWidth(longest) + 48;
			Dimension d = new Dimension(Math.max(px, COMBO_WIDTH), 30);
			apptCombo.setPreferredSize(d);
			apptCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		}
	}

	public JButton getConfirmButton() { return confirmBtn; }
	public JButton getReturnButton() { return returnBtn; }

	public Integer getSelectedTechnicianId() {// get selected technician ID
		Technician t = (Technician) techCombo.getSelectedItem();
		return (t == null) ? null : t.getTechnicianId();
	}

	public Integer getSelectedAppointmentId() {// get selected appointment ID
		String s = (String) apptCombo.getSelectedItem();
		if (s == null) return null;	// no selection
		// Expect format: "<id> | ..." (id at start)
		String[] parts = s.split("\\s*\\|\\s*");
		try {
			return Integer.valueOf(parts[0]);
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}
}
