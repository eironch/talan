import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NotePage extends JFrame {
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JPanel arrowPanel = new JPanel();
    JLabel date = new JLabel();
    JButton arrowLeft = new JButton();
    JButton arrowRight = new JButton();
    JPanel header = new JPanel();
    Container page = new Container();
    JButton button = new JButton();
    JTextArea textArea = new JTextArea();

    final static int YELLOW = 0xFFD34F;
    final static int LIGHT_YELLOW = 0xffe082;
    final static int BROWN = 0x584d2c;
    final static int BLACK = 0x000000;
    final static int WHITE = 0xFFFFFF;
    final static int WIDTH = 540;
    final static int HEIGHT = 960;

    NotePage() {

        this.setTitle("Talan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540, 960);
        this.setResizable(false);

        ImageIcon logo = new ImageIcon("assets/logo.png");
        ImageIcon icon = new ImageIcon("assets/icon.png");
        ImageIcon arrowRightIcon =
                resizeIcon(new ImageIcon("assets/rightArrow.png"), 30, 30);
        ImageIcon arrowLeftIcon =
                resizeIcon(new ImageIcon("assets/leftArrow.png"), 30, 30);


        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(toColor(LIGHT_YELLOW));

        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
        header.setBackground(toColor(YELLOW));
        header.setPreferredSize(new Dimension(100, 100));

        page.setBackground(toColor(LIGHT_YELLOW));
        page.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
        page.setPreferredSize(new Dimension(100, 100));

        date.setText("MAY 26");
        date.setForeground(toColor(BROWN));
        date.setFont(toMontserrat(40));

        arrowPanel.setLayout(new GridLayout());
        arrowPanel.setPreferredSize(new Dimension(100, 50));

        arrowLeft.setIcon(arrowLeftIcon);
        arrowLeft.setPreferredSize(new Dimension(30, 30));
        arrowLeft.setBackground(toColor(YELLOW));
        arrowLeft.setFocusable(false);
        arrowLeft.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        arrowRight.setIcon(arrowRightIcon);
        arrowRight.setPreferredSize(new Dimension(30, 30));
        arrowRight.setBackground(toColor(YELLOW));
        arrowRight.setFocusable(false);
        arrowRight.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        button.setBounds(200, 100, 100, 50);
        button.setText("Submit");
        button.setFocusable(false);
        button.setForeground(toColor(BROWN));
        button.setFont(toMontserratThin(10));
        button.setBackground(toColor(WHITE));
        button.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        button.addActionListener(e -> buttonAction());

//        label.setText("Talan");
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setVerticalAlignment(JLabel.TOP);
//        label.setForeground(toColor(BROWN));
//        label.setFont(FRAGOR);

        textArea.setText("Input Text");
        textArea.setFont(toMontserratThin(15));
        textArea.setBackground(toColor(LIGHT_YELLOW));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

//        textArea.setPreferredSize(new Dimension(WIDTH - (WIDTH/4),200));
        textArea.setRows(10);
        textArea.setColumns(35);
        this.add(textArea);
//        this.add(button);
        addMargin(date, 0, 200, 0, 0);
        arrowPanel.add(arrowLeft);
        arrowPanel.add(arrowRight);

        header.add(date);
        header.add(arrowPanel);
        page.add(textArea);
        this.add(header, BorderLayout.NORTH);
        this.add(page, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void buttonAction (){
        System.out.println("yes");
    }

    public Font toMontserrat(int size){
        return new Font("Montserrat", Font.PLAIN, size);
    }

    public Font toMontserratThin(int size){
        return new Font("Montserrat Light", Font.PLAIN, size);
    }

    public Color toColor(int hex){
        return new Color(hex);
    }

    private static void addMargin(JComponent component, int marginN, int marginE, int marginW, int marginS) {
        Border border = BorderFactory.createEmptyBorder(marginN, marginW, marginS, marginE);
        component.setBorder(border);
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage();
        Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}