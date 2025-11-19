import java.awt.*;
import java.util.List;
import javax.swing.*;

public class ProcHospReqGUI extends JPanel {

	private static final int COMBO_WIDTH = 760;

	private final JComboBox<String> reqCombo;
	private final JButton confirmBtn;
	private final JButton returnBtn;

	public ProcHospReqGUI() {
		super(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 5));

		JLabel title = new JLabel("Process Hospital Request");
		title.setFont(new Font("SansSerif", Font.BOLD, 28));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title, BorderLayout.NORTH);

		JPanel center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		center.setBorder(BorderFactory.createEmptyBorder(30, 5, 30, 5));

		JPanel row = new JPanel(new BorderLayout(8, 8));
		JLabel l = new JLabel("Available Hospital Requests:");
		l.setPreferredSize(new Dimension(210, 30));
		reqCombo = new JComboBox<>();
		reqCombo.setPreferredSize(new Dimension(COMBO_WIDTH, 30));
		reqCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		reqCombo.setRenderer(new DefaultListCellRenderer() {
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
		row.add(l, BorderLayout.WEST);
		row.add(reqCombo, BorderLayout.CENTER);
		row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
		center.add(row);

        

		add(center, BorderLayout.CENTER);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));
		bottom.setBorder(BorderFactory.createEmptyBorder(10, 120, 20, 120));

		confirmBtn = new JButton("Confirm Request");
		confirmBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		confirmBtn.setPreferredSize(new Dimension(300, 40));
		confirmBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		confirmBtn.setActionCommand("BTN_CONFIRM_PROC_REQ"); // confirm processing

		bottom.add(confirmBtn);
		bottom.add(Box.createVerticalStrut(12));

		returnBtn = new JButton("Return");
		returnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		returnBtn.setPreferredSize(new Dimension(300, 40));
		returnBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 48));
		returnBtn.setActionCommand("BTN_RETURN_TO_TRANSACTIONS"); // return

		bottom.add(returnBtn);

		add(bottom, BorderLayout.SOUTH);
	}

	// populate requests list with formatted strings
	public void populateRequests(List<String> rows) {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
		for (String s : rows) model.addElement(s);
		reqCombo.setModel(model);

		String longest = "";
		for (String s : rows) if (s != null && s.length() > longest.length()) longest = s;
		if (!longest.isEmpty()) {
			FontMetrics fm = reqCombo.getFontMetrics(reqCombo.getFont());
			int px = fm.stringWidth(longest) + 48;
			int width = Math.max(px, COMBO_WIDTH);
			Dimension d = new Dimension(width, 30);
			reqCombo.setPreferredSize(d);
			reqCombo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		}
	}

	public Integer getSelectedRequestId() {
		String s = (String) reqCombo.getSelectedItem();
		if (s == null) return null; // none selected
		String[] p = s.split("\\s*\\|\\s*"); // id at start
		try {
			return Integer.valueOf(p[0]); // parse id
		} catch (NumberFormatException | ArrayIndexOutOfBoundsException e) { return null; }
	}

    

	public JButton getConfirmButton() { return confirmBtn; }
	public JButton getReturnButton() { return returnBtn; }
}
