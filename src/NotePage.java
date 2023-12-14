import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NotePage extends JFrame {
    final static int YELLOW = 0xFFD34F;
    final static int LIGHT_YELLOW = 0xffe082;
    final static int BROWN = 0x584d2c;
    final static int BLACK = 0x000000;
    final static int WHITE = 0xFFFFFF;
    final static int WIDTH = 540;
    final static int HEIGHT = 960;

    ComponentFactory factory = new ComponentFactory();

    // button
    JButton arrowLeftButton = new JButton();
    JButton arrowRightButton = new JButton();
    JButton sidebarButton = new JButton();
    JButton dayButton = new JButton();
    JButton taskAddButton = new JButton();

    // label
    JLabel monthText = new JLabel();
    JLabel progressBar = new JLabel();
    JLabel weekdayText = new JLabel();
    JLabel yearText = new JLabel();
    JLabel taskText = new JLabel();

    // field
    JTextArea textArea = new JTextArea();

    // panel
    JPanel header = new JPanel();
    JPanel page = new JPanel();

    // container
    Container topHeaderContainer = new Container();
    Container bottomHeaderContainer = new Container();
    Container weekdayContainer = new Container();
    Container progressContainer = new Container();
    Container yearContainer = new Container();
    Container datePanel = new Container();
    Container sidebarContainer = new Container();
    Container monthContainer = new Container();
    Container dayContainer = new Container();
    Container taskContainer = new Container();
    Container taskHeaderContainer = new Container();
    Container taskTextContainer = new Container();
    Container taskAddContainer = new Container();

    NotePage() {
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
        ImageIcon circleIcon =
                resizeIcon(new ImageIcon("assets/circleIcon.png"), 30, 30);
        ImageIcon plusIcon =
                resizeIcon(new ImageIcon("assets/plusIcon.png"), 15, 15);
        ImageIcon addIcon =
                resizeIcon(new ImageIcon("assets/addIcon.png"), 35, 35);

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

//        textArea.setText("Input Text");
//        textArea.setFont(toMontserratLight(15));
//        textArea.setBackground(toColor(LIGHT_YELLOW));
//        textArea.setLineWrap(true);
//        textArea.setWrapStyleWord(true);
////        textArea.setPreferredSize(new Dimension(WIDTH - (WIDTH/4),200));
//        textArea.setRows(10);
//        textArea.setColumns(35);

        this.setTitle("Talan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(540, 960);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(toColor(LIGHT_YELLOW));
        this.setIconImage(icon.getImage());

        LocalDateTime date = LocalDateTime.now();

        header.setBackground(toColor(YELLOW));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        header.setPreferredSize(new Dimension(100, 130));
        addMargin(header,20,0,0,0);

        page.setBackground(toColor(LIGHT_YELLOW));
        page.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        page.setPreferredSize(new Dimension(100, 100));
        addMargin(page, 20,0,0,0);

        //- top header
        factory.createContainer(topHeaderContainer, new FlowLayout(), WIDTH, 65);

        // container
        factory.createContainer(sidebarContainer,
                new FlowLayout(FlowLayout.LEADING, 22, 0), (int) (WIDTH/4.5),100);
        factory.createContainer(monthContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), (int) (WIDTH/2.1),100);
        factory.createContainer(dayContainer,
                new FlowLayout(FlowLayout.TRAILING, 10, 0), (int) (WIDTH/4.5),100);

        // content
        sidebarButton.setIcon(sidebarIcon);
        sidebarButton.setPreferredSize(toDimension(50, 50));
        sidebarButton.setBackground(toColor(YELLOW));
        sidebarButton.setFocusable(false);
        sidebarButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        monthText.setText(date.format(DateTimeFormatter.ofPattern("MMMM")));
        monthText.setForeground(toColor(BROWN));
        monthText.setFont(toMontserrat(35));

        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        arrowLeftButton.setIcon(arrowLeftIcon);
        arrowLeftButton.setPreferredSize(toDimension(30,50));
        arrowLeftButton.setBackground(toColor(YELLOW));
        arrowLeftButton.setFocusable(false);
        arrowLeftButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        dayButton.setText(date.format(DateTimeFormatter.ofPattern("dd")));
        dayButton.setForeground(toColor(BROWN));
        dayButton.setFont(toMontserrat(25));
        dayButton.setHorizontalTextPosition(JLabel.CENTER);
        dayButton.setVerticalTextPosition(JLabel.CENTER);
        dayButton.setIcon(roundSquareIcon);
        dayButton.setBackground(toColor(YELLOW));
        dayButton.setFocusable(false);
        dayButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        arrowRightButton.setIcon(arrowRightIcon);
        arrowRightButton.setPreferredSize(toDimension(30,50));
        arrowRightButton.setBackground(toColor(YELLOW));
        arrowRightButton.setFocusable(false);
        arrowRightButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        //- bottom header
        factory.createContainer(bottomHeaderContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), WIDTH, 40);
        bottomHeaderContainer.setBackground(toColor(BLACK));

        // container
        weekdayContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 19, 0));
        weekdayContainer.setPreferredSize(toDimension((int) (WIDTH/4.5),100));
        progressContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        progressContainer.setPreferredSize(toDimension((int) (WIDTH/2.1),100));
        yearContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 14,0));
        yearContainer.setPreferredSize(toDimension((int) (WIDTH/4.5),100));

        // content
        weekdayText.setText(date.format(DateTimeFormatter.ofPattern("E")));
        weekdayText.setForeground(toColor(BROWN));
        weekdayText.setFont(toMontserrat(30));

        yearText.setText(date.format(DateTimeFormatter.ofPattern("yyyy")));
        yearText.setForeground(toColor(BROWN));
        yearText.setFont(toMontserrat(30));

        //- task
        factory.createContainer(taskContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), WIDTH, 300);

        // header
        factory.createContainer(taskHeaderContainer, new GridLayout(), WIDTH, 50);

        // content
        factory.createContainer(taskTextContainer,
                new FlowLayout(FlowLayout.LEADING, 40,0), WIDTH, HEIGHT);
        factory.createContainer(taskAddContainer,
                new FlowLayout(FlowLayout.TRAILING, 40,0), WIDTH, HEIGHT);

        taskText.setText("Tasks");
        taskText.setForeground(toColor(BROWN));
        taskText.setFont(toMontserrat(35));

        taskAddButton.setIcon(addIcon);
        taskAddButton.setBackground(toColor(LIGHT_YELLOW));
        taskAddButton.setPreferredSize(new Dimension(50,50));
        taskAddButton.setFocusable(false);
        taskAddButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        // debug
        sidebarButton.addActionListener(e -> addNote());

        // top header
        datePanel.add(arrowLeftButton);
        datePanel.add(dayButton);
        datePanel.add(arrowRightButton);

        sidebarContainer.add(sidebarButton);
        monthContainer.add(monthText);
        dayContainer.add(datePanel);

        topHeaderContainer.add(sidebarContainer);
        topHeaderContainer.add(monthContainer);
        topHeaderContainer.add(dayContainer);

        // bottom header
        weekdayContainer.add(weekdayText);
        yearContainer.add(yearText);

        bottomHeaderContainer.add(weekdayContainer);
        bottomHeaderContainer.add(progressContainer);
        bottomHeaderContainer.add(yearContainer);

        // task
        taskTextContainer.add(taskText);
        taskAddContainer.add(taskAddButton);

        taskHeaderContainer.add(taskTextContainer);
        taskHeaderContainer.add(taskAddContainer);

        taskContainer.add(taskHeaderContainer);

        // header
        header.add(topHeaderContainer);
        header.add(bottomHeaderContainer);

        // page
        page.add(taskContainer);

        this.add(header, BorderLayout.NORTH);
        this.add(page, BorderLayout.CENTER);
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

    public void addNote(){
        JLabel label = new JLabel();
        label.setText("yes");
        page.add(label);

        SwingUtilities.invokeLater(() -> {
            this.revalidate();
            this.repaint();
        });
    }
}