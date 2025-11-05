import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.border.*;
import com.intellij.uiDesigner.core.*;

public class MainUI extends JFrame {

    public MainUI() {
        initComponents();
        setContentPane(TechnicianPanel);
        setTitle("Technician GUI APP");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainUI();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        // Generated using JFormDesigner Evaluation license - unknown
        TechnicianPanel = new JPanel();
        var panel1 = new JPanel();
        var hSpacer1 = new Spacer();
        var hSpacer2 = new Spacer();
        var panel2 = new JPanel();
        var label1 = new JLabel();
        var hSpacer3 = new Spacer();
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        var panel3 = new JPanel();
        var label2 = new JLabel();
        contactNumTextField = new JTextField();
        var panel4 = new JPanel();
        var label3 = new JLabel();
        addressTextArea = new JTextArea();
        var hSpacer4 = new Spacer();
        var hSpacer5 = new Spacer();
        var hSpacer6 = new Spacer();
        var hSpacer7 = new Spacer();
        var hSpacer8 = new Spacer();
        var vSpacer1 = new Spacer();
        var vSpacer2 = new Spacer();
        var panel5 = new JPanel();
        var panel6 = new JPanel();

        //======== TechnicianPanel ========
        {
            TechnicianPanel.setBorder (new CompoundBorder( new TitledBorder (new EmptyBorder( 0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", TitledBorder. CENTER, TitledBorder. BOTTOM, new Font ("Dia\u006cog" ,Font .BOLD ,
            12 ), Color. red) ,TechnicianPanel. getBorder( )) ); TechnicianPanel. addPropertyChangeListener (new PropertyChangeListener( ){ @Override public void propertyChange (PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            TechnicianPanel.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));

            //======== panel1 ========
            {
                panel1.setBorder(new TitledBorder("Technician"));
                panel1.setLayout(new GridLayoutManager(10, 1, new Insets(0, 0, 0, 0), -1, -1));
                panel1.add(hSpacer1, new GridConstraints(0, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(hSpacer2, new GridConstraints(9, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null, 1));

                //======== panel2 ========
                {
                    panel2.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));

                    //---- label1 ----
                    label1.setText("Name");
                    panel2.add(label1, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                    panel2.add(hSpacer3, new GridConstraints(0, 1, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_CAN_SHRINK,
                        null, null, null));

                    //---- firstNameTextField ----
                    firstNameTextField.setText("First Name");
                    panel2.add(firstNameTextField, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- lastNameTextField ----
                    lastNameTextField.setText("Last Name");
                    panel2.add(lastNameTextField, new GridConstraints(1, 1, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                }
                panel1.add(panel2, new GridConstraints(2, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== panel3 ========
                {
                    panel3.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));

                    //---- label2 ----
                    label2.setText("Contact Number");
                    panel3.add(label2, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- contactNumTextField ----
                    contactNumTextField.setText("ContactNum");
                    panel3.add(contactNumTextField, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));
                }
                panel1.add(panel3, new GridConstraints(5, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));

                //======== panel4 ========
                {
                    panel4.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));

                    //---- label3 ----
                    label3.setText("Address");
                    panel4.add(label3, new GridConstraints(0, 0, 1, 1,
                        GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
                        GridConstraints.SIZEPOLICY_FIXED,
                        GridConstraints.SIZEPOLICY_FIXED,
                        null, null, null));

                    //---- addressTextArea ----
                    addressTextArea.setText("Address");
                    panel4.add(addressTextArea, new GridConstraints(1, 0, 1, 1,
                        GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                        null, new Dimension(150, 50), null));
                }
                panel1.add(panel4, new GridConstraints(8, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                    null, null, null));
                panel1.add(hSpacer4, new GridConstraints(6, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(hSpacer5, new GridConstraints(3, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(hSpacer6, new GridConstraints(1, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(hSpacer7, new GridConstraints(4, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
                panel1.add(hSpacer8, new GridConstraints(7, 0, 1, 1,
                    GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
                    GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                    GridConstraints.SIZEPOLICY_CAN_SHRINK,
                    null, null, null));
            }
            TechnicianPanel.add(panel1, new GridConstraints(0, 2, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
            TechnicianPanel.add(vSpacer1, new GridConstraints(0, 3, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));
            TechnicianPanel.add(vSpacer2, new GridConstraints(0, 1, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
                GridConstraints.SIZEPOLICY_CAN_SHRINK,
                GridConstraints.SIZEPOLICY_CAN_GROW | GridConstraints.SIZEPOLICY_WANT_GROW,
                null, null, null));

            //======== panel5 ========
            {
                panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
            }
            TechnicianPanel.add(panel5, new GridConstraints(0, 0, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));

            //======== panel6 ========
            {
                panel6.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
            }
            TechnicianPanel.add(panel6, new GridConstraints(0, 4, 1, 1,
                GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
                null, null, null));
        }
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    // Generated using JFormDesigner Evaluation license - unknown
    private JPanel TechnicianPanel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField contactNumTextField;
    private JTextArea addressTextArea; {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
$$$setupUI$$$();
} /** Method generated by IntelliJ IDEA GUI Designer
 * >>> IMPORTANT!! <<<
 * DO NOT edit this method OR call it in your code!
 * @noinspection ALL
 */
private void $$$setupUI$$$()
{
TechnicianPanel=new JPanel();
TechnicianPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1,5,new Insets(0,0,0,0),-1,-1));
final JPanel panel1=new JPanel();
panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10,1,new Insets(0,0,0,0),-1,-1));
TechnicianPanel.add(panel1,new com.intellij.uiDesigner.core.GridConstraints(0,2,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,null,null,null,0,false));
panel1.setBorder(BorderFactory.createTitledBorder(null,"Technician",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION,null,null));
final com.intellij.uiDesigner.core.Spacer spacer1=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer1,new com.intellij.uiDesigner.core.GridConstraints(0,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer2=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer2,new com.intellij.uiDesigner.core.GridConstraints(9,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,1,false));
final JPanel panel2=new JPanel();
panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2,2,new Insets(0,0,0,0),-1,-1));
panel1.add(panel2,new com.intellij.uiDesigner.core.GridConstraints(2,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,null,null,null,0,false));
final JLabel label1=new JLabel();
label1.setText("Name");
panel2.add(label1,new com.intellij.uiDesigner.core.GridConstraints(0,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer3=new com.intellij.uiDesigner.core.Spacer();
panel2.add(spacer3,new com.intellij.uiDesigner.core.GridConstraints(0,1,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
firstNameTextField=new JTextField();
firstNameTextField.setText("First Name");
panel2.add(firstNameTextField,new com.intellij.uiDesigner.core.GridConstraints(1,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,null,new Dimension(150,-1),null,0,false));
lastNameTextField=new JTextField();
lastNameTextField.setText("Last Name");
panel2.add(lastNameTextField,new com.intellij.uiDesigner.core.GridConstraints(1,1,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,null,new Dimension(150,-1),null,0,false));
final JPanel panel3=new JPanel();
panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2,1,new Insets(0,0,0,0),-1,-1));
panel1.add(panel3,new com.intellij.uiDesigner.core.GridConstraints(5,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,null,null,null,0,false));
final JLabel label2=new JLabel();
label2.setText("Contact Number");
panel3.add(label2,new com.intellij.uiDesigner.core.GridConstraints(0,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,null,null,null,0,false));
contactNumTextField=new JTextField();
contactNumTextField.setText("ContactNum");
panel3.add(contactNumTextField,new com.intellij.uiDesigner.core.GridConstraints(1,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,null,new Dimension(150,-1),null,0,false));
final JPanel panel4=new JPanel();
panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2,1,new Insets(0,0,0,0),-1,-1));
panel1.add(panel4,new com.intellij.uiDesigner.core.GridConstraints(8,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,null,null,null,0,false));
final JLabel label3=new JLabel();
label3.setText("Address");
panel4.add(label3,new com.intellij.uiDesigner.core.GridConstraints(0,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,null,null,null,0,false));
addressTextArea=new JTextArea();
addressTextArea.setText("Address");
panel4.add(addressTextArea,new com.intellij.uiDesigner.core.GridConstraints(1,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,null,new Dimension(150,50),null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer4=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer4,new com.intellij.uiDesigner.core.GridConstraints(6,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer5=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer5,new com.intellij.uiDesigner.core.GridConstraints(3,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer6=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer6,new com.intellij.uiDesigner.core.GridConstraints(1,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer7=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer7,new com.intellij.uiDesigner.core.GridConstraints(4,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer8=new com.intellij.uiDesigner.core.Spacer();
panel1.add(spacer8,new com.intellij.uiDesigner.core.GridConstraints(7,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,1,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer9=new com.intellij.uiDesigner.core.Spacer();
TechnicianPanel.add(spacer9,new com.intellij.uiDesigner.core.GridConstraints(0,3,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,1,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,null,null,null,0,false));
final com.intellij.uiDesigner.core.Spacer spacer10=new com.intellij.uiDesigner.core.Spacer();
TechnicianPanel.add(spacer10,new com.intellij.uiDesigner.core.GridConstraints(0,1,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL,1,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,null,null,null,0,false));
final JPanel panel5=new JPanel();
panel5.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1,1,new Insets(0,0,0,0),-1,-1));
TechnicianPanel.add(panel5,new com.intellij.uiDesigner.core.GridConstraints(0,0,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,null,null,null,0,false));
final JPanel panel6=new JPanel();
panel6.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1,1,new Insets(0,0,0,0),-1,-1));
TechnicianPanel.add(panel6,new com.intellij.uiDesigner.core.GridConstraints(0,4,1,1,com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK|com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,null,null,null,0,false));
} /** @noinspection ALL */ public JComponent $$$getRootComponent$$$() { return TechnicianPanel; }
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
}
