import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.awt.event.MouseEvent;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NotePage extends JFrame {
    final String COLOR_LIGHT_BROWN = tool.toColor(Main.LIGHT_BROWN).toString();

    int taskSectionSize;
    int minTaskSectionSize;
    int minNoteContainerSize;

    static ComponentToolbox tool = new ComponentToolbox();
    AssetHandler asset = new AssetHandler();
    ComponentFactory factory = new ComponentFactory();
    static DatabaseManager dbManager;

    LinkedList<LinkedList<Object>> tasks = new LinkedList<>();
    LinkedList<LinkedList<Object>> accomplishments = new LinkedList<>();

    // button
    JButton arrowLeftButton = new JButton();
    JButton arrowRightButton = new JButton();
    JButton sidebarButton = new JButton();
    JButton dayButton = new JButton();
    JButton taskMenuButton = new JButton();
    JButton noteSaveButton = new JButton();

    // label
    JLabel monthText = new JLabel();
//    JLabel progressBar = new JLabel();
    JLabel weekdayText = new JLabel();
    JLabel yearText = new JLabel();
    JLabel taskText = new JLabel();
    JLabel noteText = new JLabel();

    // text area
    JTextArea noteTextArea = new JTextArea();

    // panel
    JPanel header = new JPanel();
    JPanel content = new JPanel();

    // scroll pane
    JScrollPane pageScrollPane;

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
    Container taskMenuContainer = new Container();
    Container noteSectionContainer = new Container();
    Container noteHeaderContainer = new Container();
    Container noteTextContainer = new Container();
    Container noteSaveButtonContainer = new Container();
    Container noteTextAreaContainer = new Container();

    NotePage() {
        this.setTitle("Talan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Main.WIDTH, Main.HEIGHT);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(tool.toColor(Main.LIGHT_YELLOW));
        this.setIconImage(asset.icon.getImage());

        dbManager = new DatabaseManager();

        LocalDateTime date = LocalDateTime.now();

        header.setBackground(tool.toColor(Main.YELLOW));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        header.setPreferredSize(new Dimension(Main.WIDTH, 130));
        tool.addMargin(header,20,0,0,0);

        content.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        content.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        content.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT-170));
        tool.addMargin(content, 15,0,0,0);

        // ----------------- top header -------------------
        factory.createContainer(topHeaderContainer, new FlowLayout(), Main.WIDTH, 65);

        // container
        factory.createContainer(sidebarContainer,
                new FlowLayout(FlowLayout.LEADING, 22, 0), (int) (Main.WIDTH/4.5),100);
        factory.createContainer(monthContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 2), (int) (Main.WIDTH/2.1),100);
        factory.createContainer(dayContainer,
                new FlowLayout(FlowLayout.TRAILING, 10, 0), (int) (Main.WIDTH/4.5),100);

        // content
        sidebarButton.setIcon(asset.sidebarIcon);
        sidebarButton.setPreferredSize(tool.toDimension(50, 50));
        sidebarButton.setBackground(tool.toColor(Main.YELLOW));
        sidebarButton.setFocusable(false);
        sidebarButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        monthText.setText(date.format(DateTimeFormatter.ofPattern("MMMM")));
        monthText.setForeground(tool.toColor(Main.BROWN));
        monthText.setFont(tool.toMontserrat(35));

        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

        arrowLeftButton.setIcon(asset.arrowLeftIcon);
        arrowLeftButton.setPreferredSize(tool.toDimension(30,50));
        arrowLeftButton.setBackground(tool.toColor(Main.YELLOW));
        arrowLeftButton.setFocusable(false);
        arrowLeftButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        dayButton.setText(date.format(DateTimeFormatter.ofPattern("dd")));
        dayButton.setForeground(tool.toColor(Main.BROWN));
        dayButton.setFont(tool.toMontserrat(25));
        dayButton.setHorizontalTextPosition(JLabel.CENTER);
        dayButton.setVerticalTextPosition(JLabel.CENTER);
        dayButton.setIcon(asset.roundSquareIcon);
        dayButton.setBackground(tool.toColor(Main.YELLOW));
        dayButton.setFocusable(false);
        dayButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        arrowRightButton.setIcon(asset.arrowRightIcon);
        arrowRightButton.setPreferredSize(tool.toDimension(30,50));
        arrowRightButton.setBackground(tool.toColor(Main.YELLOW));
        arrowRightButton.setFocusable(false);
        arrowRightButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        // ----------------- bottom header --------------------
        factory.createContainer(bottomHeaderContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), Main.WIDTH, 40);

        // container
        weekdayContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 19, 0));
        weekdayContainer.setPreferredSize(tool.toDimension((int) (Main.WIDTH/4.5),100));
        progressContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        progressContainer.setPreferredSize(tool.toDimension((int) (Main.WIDTH/2.1),100));
        yearContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 14,0));
        yearContainer.setPreferredSize(tool.toDimension((int) (Main.WIDTH/4.5),100));

        // content
        weekdayText.setText(date.format(DateTimeFormatter.ofPattern("E")));
        weekdayText.setForeground(tool.toColor(Main.BROWN));
        weekdayText.setFont(tool.toMontserrat(30));

        yearText.setText(date.format(DateTimeFormatter.ofPattern("yyyy")));
        yearText.setForeground(tool.toColor(Main.BROWN));
        yearText.setFont(tool.toMontserrat(30));

        // ----------------- task section ------------------
        taskSectionSize = 40;
        minTaskSectionSize = 275;
        factory.createContainer(taskSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, minTaskSectionSize);

        // header
        factory.createContainer(taskHeaderContainer, new GridLayout(0,2,0,0), Main.WIDTH, 50);

        // content
        factory.createContainer(taskTextContainer,
                new FlowLayout(FlowLayout.LEADING, 54,3), Main.WIDTH, Main.HEIGHT);
        factory.createContainer(taskMenuContainer,
                new FlowLayout(FlowLayout.TRAILING, 55,0), Main.WIDTH, Main.HEIGHT);

        taskText.setText("Tasks");
        taskText.setForeground(tool.toColor(Main.BROWN));
        taskText.setFont(tool.toMontserrat(35));

        taskMenuButton.setIcon(asset.circleIcon);
        taskMenuButton.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        taskMenuButton.setPreferredSize(new Dimension(50,50));
        taskMenuButton.setFocusable(false);
        taskMenuButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        // ----------------- note section -----------------
        minNoteContainerSize = 209;
        factory.createContainer(noteSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, minNoteContainerSize + 50 + 7);

        factory.createContainer(noteHeaderContainer,
                new GridLayout(0,2,0,0), Main.WIDTH, 50);
        factory.createContainer(noteTextContainer,
                new FlowLayout(FlowLayout.LEADING, 54,3), Main.WIDTH, Main.HEIGHT);
        factory.createContainer(noteSaveButtonContainer,
                new FlowLayout(FlowLayout.TRAILING, 55,0), Main.WIDTH, Main.HEIGHT);

        factory.createContainer(noteTextAreaContainer,
                new FlowLayout(FlowLayout.LEADING, 5,8), (int) (Main.WIDTH - (Main.WIDTH/5.2)), minNoteContainerSize);

        noteText.setText("Notes");
        noteText.setForeground(tool.toColor(Main.BROWN));
        noteText.setFont(tool.toMontserrat(35));

        noteSaveButton.setIcon(asset.savedIcon);
        noteSaveButton.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        noteSaveButton.setPreferredSize(new Dimension(50,50));
        noteSaveButton.setFocusable(false);
        noteSaveButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        noteSaveButton.addActionListener(e -> saveNotes(noteTextArea.getText()));

        noteTextArea.setText("Tell us about your day.");
        noteTextArea.setFont(tool.toMontserratMedium(15));
        noteTextArea.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        noteTextArea.setForeground(tool.toColor(Main.LIGHT_BROWN));
        noteTextArea.setColumns(27);
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setBorder(null);

        try {
            getNotes();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addCaretStart(noteTextArea);
        addFocusRequest(noteTextAreaContainer, noteTextArea);
        addDocumentListener(noteTextArea, noteSaveButton);

        // -----------------------------------------------
        // ------------------ hierarchy ------------------
        // -----------------------------------------------

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
        taskMenuContainer.add(taskMenuButton);

        taskHeaderContainer.add(taskTextContainer);
        taskHeaderContainer.add(taskMenuContainer);

        // task
        taskSectionContainer.add(taskHeaderContainer);
        taskSectionContainer.add(factory.createDivider(0, 5));

        try {
            getTasks();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // notes
        noteTextContainer.add(noteText);
        noteSaveButtonContainer.add(noteSaveButton);

        noteHeaderContainer.add(noteTextContainer);
        noteHeaderContainer.add(noteSaveButtonContainer);

        noteTextAreaContainer.add(noteTextArea);

        noteSectionContainer.add(noteHeaderContainer);
        noteSectionContainer.add(noteTextAreaContainer);

        // header
        header.add(topHeaderContainer);
        header.add(bottomHeaderContainer);

        // page
        content.add(taskSectionContainer);
        content.add(factory.createDivider());
        content.add(noteSectionContainer);
        content.add(factory.createDivider());

        pageScrollPane = new JScrollPane(content);
        pageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pageScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        pageScrollPane.getVerticalScrollBar().setBackground(tool.toColor(Main.LIGHT_YELLOW));
        pageScrollPane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

        pageScrollPane.getVerticalScrollBar().setBorder(null);
        pageScrollPane.getVerticalScrollBar().setFocusable(false);
        pageScrollPane.setBorder(null);

        savePeriodically(noteTextArea);

        this.add(header, BorderLayout.NORTH);
        this.add(pageScrollPane, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
    }

    public void getTasks() throws SQLException {
        LinkedList<LinkedList<Object>> resultList = dbManager.getTasksFromTasks(Date.valueOf(LocalDateTime.now().toLocalDate()));

        for (LinkedList<Object> objects : resultList) {
            addNewTask("finish", Main.BROWN,objects.get(0).toString());
        }

        addNewTask("add", Main.LIGHT_BROWN, "New Task");
    }

    public void addNewTask(String buttonType, Integer colorCode, String taskText){
        Container taskContainer = new Container();
        Container taskButtonContainer = new Container();
        Container taskTextFieldContainer = new Container();
        JButton taskAddButton = new JButton();
        JButton taskFinishButton = new JButton();
        JTextField taskTextField = new JTextField();
        int taskId = 0;

        taskSectionSize += 40;

        if ((taskSectionSize) > minTaskSectionSize - 35){
            taskSectionContainer.setPreferredSize(new Dimension(Main.WIDTH, taskSectionSize + 35));
            content.setPreferredSize(new Dimension(Main.WIDTH, tool.getTotalHeight(content) + 20));
        }

        repaint(content);

        factory.createContainer(taskContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), Main.WIDTH, 40);
        factory.createContainer(taskButtonContainer,
                new FlowLayout(FlowLayout.LEADING,0,0), 44,40);
        factory.createContainer(taskTextFieldContainer,
                new GridLayout(1,1, 0,0), (int) (Main.WIDTH - (Main.WIDTH/3.5)), 40);

        taskTextField.setText(taskText);
        taskTextField.setFont(tool.toMontserrat(20));
        taskTextField.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        taskTextField.setHorizontalAlignment(JTextField.LEFT);
        taskTextField.setForeground(tool.toColor(colorCode));
        taskTextField.setBorder(null);
        addCaretStart(taskTextField);

        taskAddButton.setIcon(asset.addIcon);
        taskAddButton.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        taskAddButton.setPreferredSize(new Dimension(40,40));
        taskAddButton.setFocusable(false);
        taskAddButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskAddButton.addActionListener(this::saveTask);

        taskFinishButton.setIcon(asset.circleIcon);
        taskFinishButton.setBackground(tool.toColor(Main.LIGHT_YELLOW));
        taskFinishButton.setPreferredSize(new Dimension(40,40));
        taskFinishButton.setFocusable(false);
        taskFinishButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskFinishButton.addActionListener(this::finishTask);

        if (buttonType.equals("add")){
            taskButtonContainer.add(taskAddButton);
        } else if (buttonType.equals("finish")){
            taskButtonContainer.add(taskFinishButton);
        }

        taskTextFieldContainer.add(taskTextField);

        taskContainer.add(taskTextFieldContainer);
        taskContainer.add(taskButtonContainer);

        taskSectionContainer.add(taskContainer);

        tasks.add(new LinkedList<>(Arrays.asList(
                taskContainer,
                taskButtonContainer,
                taskTextField,
                taskAddButton,
                taskFinishButton,
                taskId))
        );

        addDocumentListener(taskTextField, tasks);

        taskTextField.requestFocus();

        repaint(taskSectionContainer);
    }

    public void saveTask(ActionEvent e) {
        Object component = e.getSource();

        for (LinkedList<Object> task : tasks) {
            if (!task.contains(component)) {
                continue;
            }

            // check if task is valid
            JTextField textField = (JTextField) task.get(2);
            if (textField.getText().equals("New Task")) {
                return;

            } else if (textField.getText().isEmpty()) {
                textField.setText("New Task");
                textField.setForeground(tool.toColor(Main.LIGHT_BROWN));
                textField.setCaretPosition(0);

                repaint(taskSectionContainer);

                return;
            }

            Container container = (Container) task.get(1);
            container.remove((Component) task.get(3));
            container.add((Component) task.get(4));

            repaint(container);

            handleAddTask();

            // save to database
            try {
                if ((Integer) task.get(5) == 0) {
                    dbManager.insertToTasks(textField.getText(), Date.valueOf(LocalDateTime.now().toLocalDate()));
                    task.set(5, dbManager.getTaskIdOfLast());
                } else {
                    dbManager.updateToTasks(textField.getText(), (Integer) task.get(5));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void savePeriodically(JTextArea textArea){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        int initialDelay = 5;
        int period = 5;

        scheduler.scheduleAtFixedRate(() -> saveNotes(textArea.getText()), initialDelay, period, TimeUnit.SECONDS);
    }

    public void saveNotes(String noteText){
        if (noteText.equals("Tell us about your day.")){
            return;
        }

        if (noteSaveButton.getIcon().equals(asset.savedIcon)){
            return;
        }

        noteSaveButton.setIcon(asset.savedIcon);

        repaint(noteSaveButton);

        try {
            dbManager.insertToNotes(Date.valueOf(LocalDateTime.now().toLocalDate()), noteText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getNotes() throws SQLException {
        String noteText = dbManager.getNoteFromNotes(Date.valueOf(LocalDateTime.now().toLocalDate()));

        if (noteText == null){
            return;
        }

        noteTextArea.setText(noteText);
        noteTextArea.setForeground(tool.toColor(Main.BROWN));
    }

    public void handleAddTask(){
        JTextField textField = (JTextField) tasks.get(tasks.size() - 1).get(2);

        if (!textField.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
            addNewTask("add", Main.LIGHT_BROWN, "New Task");
        }
    }

    public void finishTask(ActionEvent e) {
        Object component = e.getSource();

        for(int i = 0; i < tasks.size(); i++){
            if (!tasks.get(i).contains(component)){
                continue;
            }

            taskSectionContainer.remove((Component) tasks.get(i).get(0));

            accomplishments.add(tasks.get(i));
            tasks.remove(i);

            taskSectionSize -= 40;

            if (tasks.size()>=5){
                taskSectionContainer.setPreferredSize(new Dimension(Main.WIDTH, taskSectionSize + 35));
                content.setPreferredSize(new Dimension(Main.WIDTH, tool.getTotalHeight(content) + 20));
            }

            repaint(content);

            return;
        }
    }

    public void addFocusRequest(Container container, JTextArea textArea) {
        container.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (textArea.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
                    textArea.setCaretPosition(0);
                }
            }

            public void mouseClicked(MouseEvent e) {
                noteTextArea.requestFocusInWindow();
            }
        });
    }

    public void addCaretStart(JTextField textField) {
        textField.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                resetCaretPosition();
            }
            public void mouseClicked(MouseEvent e) {
                resetCaretPosition();
            }
            public void mouseReleased(MouseEvent e) {
                resetCaretPosition();
            }

            private void resetCaretPosition(){
                if (textField.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
                    textField.setCaretPosition(0);
                }
            }
        });
    }

    public void addCaretStart(JTextArea textArea) {
        textArea.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                resetCaretPosition();
            }

            public void mouseReleased(MouseEvent e) {
                resetCaretPosition();
            }

            private void resetCaretPosition(){
                if (textArea.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
                    textArea.setCaretPosition(0);
                }
            }
        });
    }

    public void addDocumentListener(JTextArea textArea, JButton button) {
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(this::handleNewLine);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(this::handleNewLine);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(this::handleNewLine);
            }

            private void handleNewLine() {
                if (textArea.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
                    textArea.setText(textArea.getText().replaceFirst(
                            "Tell us about your day.", ""));
                    textArea.setForeground(tool.toColor(Main.BROWN));
                }

                if (button.getIcon().equals(asset.savedIcon)){
                    button.setIcon(asset.saveIcon);
                }

                if (textArea.getPreferredSize().getHeight() + 19 <= minNoteContainerSize){
                    noteSectionContainer.setPreferredSize(
                            new Dimension(noteTextAreaContainer.getWidth(), minNoteContainerSize + (50 + 7)));

                } else {
                    noteTextAreaContainer.setPreferredSize(
                            new Dimension(noteTextAreaContainer.getWidth(),
                                    (int) textArea.getPreferredSize().getHeight() + 19));

                    noteSectionContainer.setPreferredSize(
                            new Dimension(noteTextAreaContainer.getWidth(),
                                    (int) textArea.getPreferredSize().getHeight() + (19 + 50 + 7)));
                }

                content.setPreferredSize(new Dimension(Main.WIDTH, tool.getTotalHeight(content) + 20));

                repaint(content);
            }
        });
    }

    public void addDocumentListener(JTextField textField, LinkedList<LinkedList<Object>> tasks) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(this::handleNewLine);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(this::handleNewLine);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(this::handleNewLine);
            }

            private void handleNewLine() {
                if (textField.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
                    textField.setText(textField.getText().replaceFirst(
                            "New Task", ""));
                    textField.setForeground(tool.toColor(Main.BROWN));

                    repaint(textField);

                    return;
                }

                for (LinkedList<Object> task : tasks) {
                    if (!task.contains(textField)) {
                        continue;
                    }

                    Container container = (Container) task.get(1);

                    if (container.isAncestorOf((Component) task.get(3))) {
                        return;
                    }

                    container.remove((Component) task.get(4));
                    container.add((Component) task.get(3));

                    repaint(container);
                }

                repaint(textField);
            }
        });
    }

    static class CustomScrollBarUI extends BasicScrollBarUI {
        int verticalScrollBarWidth = 4;
        int verticalTrackBoundX = 13;
        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createArrowButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createArrowButton();
        }

        private JButton createArrowButton() {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(0, 0));
            return button;
        }

        @Override
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            trackBounds.width = verticalScrollBarWidth;
            g.setColor(tool.toColor(Main.LIGHT_YELLOW));
            g.fillRect(trackBounds.x + verticalTrackBoundX, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            thumbBounds.width = verticalScrollBarWidth;
            Graphics2D thumbEndGraphic = (Graphics2D) g.create();

            int arcWidth = 4;
            int arcHeight = 4;
            thumbEndGraphic.setColor(tool.toColor(Main.BROWN));
            thumbEndGraphic.fillRoundRect(thumbBounds.x + verticalTrackBoundX, thumbBounds.y, thumbBounds.width, thumbBounds.height, arcWidth, arcHeight);

            thumbEndGraphic.dispose();
        }
    }

    public void repaint(Component component){
        SwingUtilities.invokeLater(() -> {
            component.revalidate();
            component.repaint();
        });
    }
}