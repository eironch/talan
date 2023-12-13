import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotePage extends JFrame {
    JPanel panel = new JPanel();
    JLabel label = new JLabel();
    JLabel monthText = new JLabel();
    JButton arrowLeft = new JButton();
    JButton arrowRight = new JButton();
    JButton sidebar = new JButton();
    JPanel header = new JPanel();
    JLabel progressBar = new JLabel();
    JLabel weekdayText = new JLabel();
    JLabel yearText = new JLabel();
    Container page = new Container();
    Container topHeader = new Container();
    Container bottomHeader = new Container();
    Container weekdayContainer = new Container();
    Container progressContainer = new Container();
    Container yearContainer = new Container();
    Container datePanel = new Container();
    Container sidebarContainer = new Container();
    Container monthContainer = new Container();
    Container dayContainer = new Container();
    JButton day = new JButton();
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
                resizeIcon(new ImageIcon("assets/sidebarIcon.png"), 45, 45);
        ImageIcon roundSquareIcon =
                resizeIcon(new ImageIcon("assets/roundSquareIcon.png"), 50, 50);

        this.setIconImage(icon.getImage());
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(toColor(LIGHT_YELLOW));

        topHeader.setLayout(new FlowLayout(FlowLayout.CENTER));
        topHeader.setPreferredSize(new Dimension(WIDTH, 70));
//        topHeader.setBackground(toColor(YELLOW));
        bottomHeader.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomHeader.setPreferredSize(new Dimension(WIDTH, 40));
        bottomHeader.setBackground(toColor(BLACK));

        header.setBackground(toColor(YELLOW));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        header.setPreferredSize(new Dimension(100, 130));
        addMargin(header,10,0,0,0);

        page.setBackground(toColor(LIGHT_YELLOW));
        page.setLayout(new FlowLayout(FlowLayout.CENTER, 0,20));
        page.setPreferredSize(new Dimension(100, 100));

        sidebarContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 22, 0));
        sidebarContainer.setPreferredSize(toDimension((int) (WIDTH/4.2),100));
        monthContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        monthContainer.setPreferredSize(toDimension((int) (WIDTH/2.1),100));
        dayContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 10,0));
        dayContainer.setPreferredSize(toDimension((int) (WIDTH/4.2),100));

        weekdayContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 26, 0));
        weekdayContainer.setPreferredSize(toDimension((int) (WIDTH/4.2),100));
        progressContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        progressContainer.setPreferredSize(toDimension((int) (WIDTH/2.1),100));
        yearContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 18,0));
        yearContainer.setPreferredSize(toDimension((int) (WIDTH/4.2),100));

        sidebar.setIcon(sidebarIcon);
        sidebar.setPreferredSize(toDimension(50, 50));
        sidebar.setBackground(toColor(YELLOW));
        sidebar.setFocusable(false);
        sidebar.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        LocalDateTime date = LocalDateTime.now();

        monthText.setText(date.format(DateTimeFormatter.ofPattern("MMMM")));
        monthText.setForeground(toColor(BROWN));
        monthText.setFont(toMontserrat(40));
        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        arrowLeft.setIcon(arrowLeftIcon);
        arrowLeft.setPreferredSize(toDimension(30,50));
        arrowLeft.setBackground(toColor(YELLOW));
        arrowLeft.setFocusable(false);
        arrowLeft.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        day.setText(date.format(DateTimeFormatter.ofPattern("dd")));
        day.setForeground(toColor(BROWN));
        day.setFont(toMontserrat(25));
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

        weekdayText.setText(date.format(DateTimeFormatter.ofPattern("E")));
        weekdayText.setForeground(toColor(BROWN));
        weekdayText.setFont(toMontserrat(30));
        weekdayText.setHorizontalTextPosition(JLabel.CENTER);
        weekdayText.setVerticalTextPosition(JLabel.CENTER);

        yearText.setText(date.format(DateTimeFormatter.ofPattern("yyyy")));
        yearText.setForeground(toColor(BROWN));
        yearText.setFont(toMontserrat(30));
        yearText.setHorizontalTextPosition(JLabel.CENTER);
        yearText.setVerticalTextPosition(JLabel.CENTER);

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

        weekdayContainer.add(weekdayText);
        yearContainer.add(yearText);

        topHeader.add(sidebarContainer);
        topHeader.add(monthContainer);
        topHeader.add(dayContainer);

        bottomHeader.add(weekdayContainer);
        bottomHeader.add(progressContainer);
        bottomHeader.add(yearContainer);

        header.add(topHeader);
        header.add(bottomHeader);

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