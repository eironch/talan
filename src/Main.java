import javax.swing.*;
import java.sql.SQLException;

public class Main {
    final static int YELLOW = 0xFFD34F;
    final static int LIGHT_YELLOW = 0xffe082;
    final static int BROWN = 0x584d2c;
    final static int LIGHT_BROWN = 0x847443;
    final static int BLACK = 0x000000;
    final static int WHITE = 0xFFFFFF;
    final static int WIDTH = 540;
    final static int HEIGHT = 960;
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            new NotePage().setVisible(true);
        });
    }
}
