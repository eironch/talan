import javax.swing.*;
import java.awt.*;

public class Main {
    static JFrame frame = new JFrame();
    static AssetFactory a = new AssetFactory();
    final static int YELLOW = 0xFFD34F;
    final static int LIGHT_YELLOW = 0xffe082;
    final static int BROWN = 0x584d2c;
    final static int LIGHT_BROWN = 0x847443;
    final static int BLACK = 0x000000;
    final static int WHITE = 0xFFFFFF;
    final static int WIDTH = 540;
    final static int HEIGHT = 960;
    final static String COLOR_LIGHT_BROWN = a.toColor(Main.LIGHT_BROWN).toString();

    public static void main(String[] args) {
        frame.setTitle("Talan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Main.WIDTH, Main.HEIGHT);
        frame.setBounds((1920 / 2)- (WIDTH / 2), (1080 / 2)- (HEIGHT / 2), WIDTH, HEIGHT);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(a.toColor(Main.LIGHT_YELLOW));
        frame.setIconImage(a.icon.getImage());

        SwingUtilities.invokeLater(() -> new MainView(frame));

        frame.setVisible(true);
    }
}
