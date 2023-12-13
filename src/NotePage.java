import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotePage extends JFrame {
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JPanel datePanel = new JPanel();
    Container sidebarContainer = new Container();
    Container monthContainer = new Container();
    Container dayContainer = new Container();
    JLabel monthText = new JLabel();
    JButton arrowLeft = new JButton();
    JButton arrowRight = new JButton();
    JButton sidebar = new JButton();
    JPanel header = new JPanel();
    Container page = new Container();
    JButton day = new JButton();
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
                resizeIcon(new ImageIcon("assets/rightArrowIcon.png"), 40, 40);
        ImageIcon arrowLeftIcon =
                resizeIcon(new ImageIcon("assets/leftArrowIcon.png"), 40, 40);
        ImageIcon sidebarIcon =
                resizeIcon(new ImageIcon("assets/sidebarIcon.png"), 40, 40);
        ImageIcon roundSquareIcon =
                resizeIcon(new ImageIcon("assets/roundSquareIcon.png"), 50, 50);

        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(toColor(LIGHT_YELLOW));

        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
        header.setBackground(toColor(YELLOW));
        header.setPreferredSize(new Dimension(100, 100));

        page.setBackground(toColor(LIGHT_YELLOW));
        page.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
        page.setPreferredSize(new Dimension(100, 100));

        sidebarContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 22, 5));
        sidebarContainer.setBackground(toColor(BLACK));
        sidebarContainer.setPreferredSize(toDimension((int) (WIDTH/4.2),100));
        monthContainer.setLayout(new FlowLayout());
        monthContainer.setPreferredSize(toDimension((int) (WIDTH/2.1),100));
        dayContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 10,5));
//        dayContainer.setBackground(toColor(BLACK));
        dayContainer.setPreferredSize(toDimension((int) (WIDTH/4.2),100));

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter monthFormat = DateTimeFormatter.ofPattern("MMMM");

        monthText.setText(date.format(monthFormat));
        monthText.setForeground(toColor(BROWN));
        monthText.setFont(toMontserrat(40));

        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        arrowLeft.setIcon(arrowLeftIcon);
        arrowLeft.setPreferredSize(toDimension(30,50));
        arrowLeft.setBackground(toColor(YELLOW));
        arrowLeft.setFocusable(false);
        arrowLeft.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd");

        day.setText(date.format(dateFormat));
        day.setForeground(toColor(BROWN));
        day.setFont(toMontserrat(25));
        day.setPreferredSize(toDimension(50,50));
        day.setHorizontalTextPosition(JLabel.CENTER);
        day.setVerticalTextPosition(JLabel.CENTER);
        day.setIcon(roundSquareIcon);
        day.setBackground(toColor(YELLOW));
        day.setFocusable(false);
        day.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        arrowRight.setIcon(arrowRightIcon);
        arrowRight.setPreferredSize(toDimension(30,50));
        arrowRight.setBackground(toColor(YELLOW));
        arrowRight.setFocusable(false);
        arrowRight.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        sidebar.setIcon(sidebarIcon);
        sidebar.setPreferredSize(toDimension(50, 50));
        sidebar.setBackground(toColor(YELLOW));
        sidebar.setFocusable(false);
        sidebar.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

//        button.setBounds(200, 100, 100, 50);
//        button.setText("Submit");
//        button.setFocusable(false);
//        button.setForeground(toColor(BROWN));
//        button.setFont(toMontserratThin(10));
//        button.setBackground(toColor(WHITE));
//        button.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
//        button.addActionListener(e -> buttonAction());

//        label.setText("Talan");
//        label.setHorizontalAlignment(JLabel.CENTER);
//        label.setVerticalAlignment(JLabel.TOP);
//        label.setForeground(toColor(BROWN));
//        label.setFont(FRAGOR);

        textArea.setText("Input Text");
        textArea.setFont(toMontserratLight(15));
        textArea.setBackground(toColor(LIGHT_YELLOW));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
//        textArea.setPreferredSize(new Dimension(WIDTH - (WIDTH/4),200));
        textArea.setRows(10);
        textArea.setColumns(35);

        datePanel.add(arrowLeft);
        datePanel.add(day);
        datePanel.add(arrowRight);

        sidebarContainer.add(sidebar);
        monthContainer.add(monthText);
        dayContainer.add(datePanel);

        header.add(sidebarContainer);
        header.add(monthContainer);
        header.add(dayContainer);

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

    public Font toMontserratLight(int size){
        return new Font("Montserrat Light", Font.PLAIN, size);
    }

    public Color toColor(int hex){
        return new Color(hex);
    }

    public Dimension toDimension(int width, int height){
        return new Dimension(width, height);
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