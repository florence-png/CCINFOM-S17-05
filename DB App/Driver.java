import javax.swing.*;

public class Driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MenuGUI view = new MenuGUI();
            MenuController controller = new MenuController(view);
        });
    }
}
