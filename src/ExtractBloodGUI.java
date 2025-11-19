import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ExtractBloodGUI extends JPanel {

	private static final int COMBO_WIDTH = 760;

	private final JComboBox<String> apptCombo;
	private final JComboBox<String> remarksCombo;
	private final JButton confirmBtn;
	private final JButton returnBtn;

	public ExtractBloodGUI() {
		super(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

		JLabel title = new JLabel("Extract Blood");
		title.setFont(new Font("SansSerif", Font.BOLD, 28));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));

		JPanel row1 = new JPanel(new BorderLayout(8, 8));
		JLabel lAppt = new JLabel("Select Appointment");
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
		row1.add(lAppt, BorderLayout.WEST);
		row1.add(apptCombo, BorderLayout.CENTER);
		row1.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row1);
		center.add(Box.createVerticalStrut(16));

		JPanel row2 = new JPanel(new BorderLayout(8, 8));
		JLabel lRemarks = new JLabel("Remarks");
		lRemarks.setPreferredSize(new Dimension(170, 30));
		remarksCombo = new JComboBox<>(new String[]{"No Issues", "Hemolyzed", "Low Volume", "Contaminated", "Other"});
		remarksCombo.setPreferredSize(new Dimension(COMBO_WIDTH, 30));
		remarksCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		row2.add(lRemarks, BorderLayout.WEST);
		row2.add(remarksCombo, BorderLayout.CENTER);
		row2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row2);

		add(center, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.setBorder(BorderFactory.createEmptyBorder(10, 120, 20, 120));

		confirmBtn = new JButton("Confirm Extraction");
		confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmBtn.setPreferredSize(new Dimension(300, 40));
		confirmBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		confirmBtn.setActionCommand("BTN_CONFIRM_EXTRACT");

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

	public void populateAppointments(List<String> appts) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // model for appt rows
		for (String s : appts) model.addElement(s);
		apptCombo.setModel(model);

		String longest = "";
		for (String s : appts) if (s != null && s.length() > longest.length()) longest = s;
		if (!longest.isEmpty()) {
			FontMetrics fm = apptCombo.getFontMetrics(apptCombo.getFont());
			int px = fm.stringWidth(longest) + 48;
			int width = Math.max(px, COMBO_WIDTH);
			Dimension d = new Dimension(width, 30);
			apptCombo.setPreferredSize(d);
			apptCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		}
	}

	public String getSelectedRemark() {
		return (String) remarksCombo.getSelectedItem();
	}

	public Integer getSelectedAppointmentId() {
		String s = (String) apptCombo.getSelectedItem();
		if (s == null) return null;
		String[] parts = s.split("\\s*\\|\\s*"); // id is first token
		try {
			return Integer.valueOf(parts[0]); // parse id
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
			return null; // parse error
		}
	}

	public JButton getConfirmButton() { return confirmBtn; }
	public JButton getReturnButton() { return returnBtn; }
}
