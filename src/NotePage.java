import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;

public class NotePage extends JFrame {
    final static int YELLOW = 0xFFD34F;
    final static int LIGHT_YELLOW = 0xffe082;
    final static int BROWN = 0x584d2c;
    final static int BLACK = 0x000000;
    final static int WHITE = 0xFFFFFF;
    final static int WIDTH = 540;
    final static int HEIGHT = 960;
    int taskSectionSize;
    int minTaskSectionSize;
    LinkedList<LinkedList<Object>> tasks = new LinkedList<>();
    LinkedList<LinkedList<Object>> accomplishements = new LinkedList<>();
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
    Container taskSectionContainer = new Container();
    Container taskHeaderContainer = new Container();
    Container taskTextContainer = new Container();
    Container taskAddContainer = new Container();
    Container dividerSectionContainer = new Container();
    Container dividerContainer = new Container();
    Container taskContainer = new Container();

    // asset
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
            resizeIcon(new ImageIcon("assets/addIcon.png"), 30, 30);
    ImageIcon dividerLeftIcon =
            resizeIcon(new ImageIcon("assets/dividerLeftIcon.png"), 30, 4);
    ImageIcon dividerRightIcon =
            resizeIcon(new ImageIcon("assets/dividerRightIcon.png"), 30, 4);
    ImageIcon doneIcon =
            resizeIcon(new ImageIcon("assets/doneIcon.png"), 30, 30);

    NotePage() {
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
        addMargin(page, 15,0,0,0);

        // ----------------- top header -------------------
        factory.createContainer(topHeaderContainer, new FlowLayout(), WIDTH, 65);

        // container
        factory.createContainer(sidebarContainer,
                new FlowLayout(FlowLayout.LEADING, 22, 0), (int) (WIDTH/4.5),100);
        factory.createContainer(monthContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 2), (int) (WIDTH/2.1),100);
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

        // ----------------- bottom header --------------------
        factory.createContainer(bottomHeaderContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), WIDTH, 40);

        // container
        weekdayContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 18, 0));
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

        // ----------------- task section ------------------Container
        taskSectionSize = 40;
        minTaskSectionSize = 280;
        factory.createContainer(taskSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), WIDTH, minTaskSectionSize);

        // header
        factory.createContainer(taskHeaderContainer, new GridLayout(0,2,0,0), WIDTH, 50);

        // content
        factory.createContainer(taskTextContainer,
                new FlowLayout(FlowLayout.LEADING, 54,3), WIDTH, HEIGHT);
        factory.createContainer(taskAddContainer,
                new FlowLayout(FlowLayout.TRAILING, 56,0), WIDTH, HEIGHT);

        taskText.setText("Tasks");
        taskText.setForeground(toColor(BROWN));
        taskText.setFont(toMontserrat(35));

        taskAddButton.setIcon(addIcon);
        taskAddButton.setBackground(toColor(LIGHT_YELLOW));
        taskAddButton.setPreferredSize(new Dimension(50,50));
        taskAddButton.setFocusable(false);
        taskAddButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskAddButton.addActionListener(e -> addNote());

        // ------------------ hierarchy ------------------
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

        // task header
        taskTextContainer.add(taskText);
        taskAddContainer.add(taskAddButton);

        taskHeaderContainer.add(taskTextContainer);
        taskHeaderContainer.add(taskAddContainer);

        // task
        taskSectionContainer.add(taskHeaderContainer);
        taskSectionContainer.add(createDivider());
        taskSectionContainer.add(taskContainer);

        addNote();

        // header
        header.add(topHeaderContainer);
        header.add(bottomHeaderContainer);

        // page
        page.add(taskSectionContainer);
        page.add(createDivider());

        this.add(header, BorderLayout.NORTH);
        this.add(page, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
    }

    public Font toMontserrat(int size){
        return new Font("Montserrat", Font.PLAIN, size);
    }

    public Font toMontserratMedium(int size){
        return new Font("Montserrat Medium", Font.PLAIN, size);
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
        Container taskContainer = new Container();
        Container taskButtonContainer = new Container();
        Container taskTextFieldContainer = new Container();
        JButton taskButton = new JButton();
        JTextField taskTextArea = new JTextField();

        factory.createContainer(taskContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), WIDTH, 40);
        factory.createContainer(taskButtonContainer,
                new FlowLayout(FlowLayout.LEADING,0,0), 46,40);
        factory.createContainer(taskTextFieldContainer,
                new GridLayout(1,1, 0,0), (int) (WIDTH - (WIDTH/3.5)), 40);

        taskTextArea.setText("New Task");
        taskTextArea.setFont(toMontserrat(20));
        taskTextArea.setBackground(toColor(LIGHT_YELLOW));
        taskTextArea.setHorizontalAlignment(JTextField.LEFT);
        taskTextArea.setForeground(toColor(BROWN));
        taskTextArea.setBorder(null);

        taskButton.setIcon(circleIcon);
        taskButton.setBackground(toColor(LIGHT_YELLOW));
        taskButton.setPreferredSize(new Dimension(40,40));
        taskButton.setFocusable(false);
        taskButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskButton.addActionListener(this::finishTask);

        taskButtonContainer.add(taskButton);
        taskTextFieldContainer.add(taskTextArea);

        taskContainer.add(taskTextFieldContainer);
        taskContainer.add(taskButtonContainer);

        tasks.add(new LinkedList<>(Arrays.asList(
                taskContainer,
                taskButtonContainer,
                taskTextFieldContainer,
                taskButton,
                taskTextArea))
        );

        taskSectionSize += 40;

        if ((taskSectionSize) >= 240){
            taskSectionContainer.setPreferredSize(new Dimension(WIDTH, taskSectionSize + 40));
        }

        taskSectionContainer.add(taskContainer);

        taskTextArea.requestFocus();

        SwingUtilities.invokeLater(() -> {
            taskSectionContainer.revalidate();
            taskSectionContainer.repaint();
        });
    }

    public void finishTask(ActionEvent e) {
        Object component = e.getSource();
        for(int i = 0; i < tasks.size(); i++){
            if (!tasks.get(i).contains(component)){
                continue;
            }

            taskSectionContainer.remove((Component) tasks.get(i).get(0));

            accomplishements.add(tasks.get(i));
            tasks.remove(i);

            taskSectionSize -= 40;

            if (tasks.size()>=5){
                taskSectionContainer.setPreferredSize(new Dimension(WIDTH, taskSectionSize + 40));
            }

            SwingUtilities.invokeLater(() -> {
                taskSectionContainer.revalidate();
                taskSectionContainer.repaint();
            });

            return;
        }
    }

    public Container createDivider(){
        Container dividerSectionContainer = new Container();
        Container dividerContainer = new Container();
        JPanel divider = new JPanel();
        JLabel dividerLeft = new JLabel();
        JLabel dividerRight = new JLabel();

        factory.createContainer(dividerSectionContainer,
                new FlowLayout(), WIDTH,20);
        factory.createContainer(dividerContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), WIDTH, 4);

        dividerLeft.setIcon(dividerLeftIcon);
        dividerLeft.setPreferredSize(new Dimension(dividerLeftIcon.getIconWidth(),8));
        dividerLeft.setVerticalAlignment(SwingConstants.TOP);

        dividerRight.setIcon(dividerRightIcon);
        dividerRight.setPreferredSize(new Dimension(dividerRightIcon.getIconWidth(),8));
        dividerRight.setVerticalAlignment(SwingConstants.TOP);

        divider.setBackground(toColor(BROWN));
        divider.setPreferredSize(new Dimension(WIDTH - (WIDTH/4),8));

        dividerContainer.add(dividerLeft);
        dividerContainer.add(divider);
        dividerContainer.add(dividerRight);

        dividerSectionContainer.add(dividerContainer);

        return dividerSectionContainer;
    }
}