import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainView extends JFrame {
    AssetFactory a = new AssetFactory();
    DatabaseManager d = new DatabaseManager();
    ComponentFactory c = new ComponentFactory();
    PanelFactory p = new PanelFactory();
    final String COLOR_LIGHT_BROWN = a.toColor(Main.LIGHT_BROWN).toString();
    int taskSectionSize = 40;
    int minTaskSectionSize = 275;
    int minNoteContainerSize = 209;
    int day;
    int year;
    int month;
    String weekday;
    LocalDateTime date;
    LinkedList<LinkedList<Object>> taskList = new LinkedList<>();
    LinkedList<LinkedList<Object>> doneList = new LinkedList<>();

    public MainView() {
        this.setTitle("Talan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Main.WIDTH, Main.HEIGHT);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(a.toColor(Main.LIGHT_YELLOW));
        this.setIconImage(a.icon.getImage());

        date = LocalDateTime.now();
        day = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("dd")));
        weekday = date.format(DateTimeFormatter.ofPattern("E"));
        month = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("M")));
        year = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("yyyy")));

        c.arrowLeftButton.addActionListener(e -> showYesterday());
        c.arrowRightButton.addActionListener(e -> showTomorrow());

        // ----------------- task section ------------------

        c.taskTabButton.addActionListener(e -> swapTaskAccomplished());

        // ----------------- note section -----------------

        c.noteSaveButton.addActionListener(e -> saveNotes(c.noteTextArea.getText()));

        addCaretStart(c.noteTextArea);
        addFocusRequest(p.noteTextAreaContainer, c.noteTextArea);
        addDocumentListener(c.noteTextArea, c.noteSaveButton);

        // --------------- mood section -----------------

        c.worstMoodButton.addActionListener(this::selectMood);
        c.badMoodButton.addActionListener(this::selectMood);
        c.fineMoodButton.addActionListener(this::selectMood);
        c.goodMoodButton.addActionListener(this::selectMood);
        c.excellentMoodButton.addActionListener(this::selectMood);

        // -----------------------------------------------
        // ----------------- hierarchy -------------------
        // -----------------------------------------------

        // top header
        p.datePanel.add(c.arrowLeftButton);
        p.datePanel.add(c.dayButton);
        p.datePanel.add(c.arrowRightButton);

        p.sidebarContainer.add(c.sidebarButton);
        p.monthContainer.add(c.monthText);
        p.dayContainer.add(p.datePanel);

        p.topHeaderContainer.add(p.sidebarContainer);
        p.topHeaderContainer.add(p.monthContainer);
        p.topHeaderContainer.add(p.dayContainer);

        // bottom header
        p.weekdayContainer.add(c.weekdayText);
        p.yearContainer.add(c.yearText);

        p.bottomHeaderContainer.add(p.weekdayContainer);
        p.bottomHeaderContainer.add(p.progressContainer);
        p.bottomHeaderContainer.add(p.yearContainer);

        // task header
        p.taskTextContainer.add(c.taskDoneText);
        p.taskTabContainer.add(c.taskTabButton);

        p.taskHeaderContainer.add(p.taskTextContainer);
        p.taskHeaderContainer.add(p.taskTabContainer);

        // task
        p.taskDoneSectionContainer.add(p.taskHeaderContainer);
        p.taskDoneSectionContainer.add(p.createDivider(0, 5));

        // note
        p.noteTextContainer.add(c.noteText);
        p.noteSaveButtonContainer.add(c.noteSaveButton);

        p.noteHeaderContainer.add(p.noteTextContainer);
        p.noteHeaderContainer.add(p.noteSaveButtonContainer);

        p.noteTextAreaContainer.add(c.noteTextArea);

        p.noteSectionContainer.add(p.noteHeaderContainer);
        p.noteSectionContainer.add(p.noteTextAreaContainer);

        // mood
        p.moodContextContainer.add(c.moodContext);

        p.moodButtonsGroup.add(c.worstMoodButton);
        p.moodButtonsGroup.add(c.badMoodButton);
        p.moodButtonsGroup.add(c.fineMoodButton);
        p.moodButtonsGroup.add(c.goodMoodButton);
        p.moodButtonsGroup.add(c.excellentMoodButton);

        p.moodButtonsContainer.add(c.worstMoodButton);
        p.moodButtonsContainer.add(c.badMoodButton);
        p.moodButtonsContainer.add(c.fineMoodButton);
        p.moodButtonsContainer.add(c.goodMoodButton);
        p.moodButtonsContainer.add(c.excellentMoodButton);

        p.moodSectionContainer.add(p.moodContextContainer);
        p.moodSectionContainer.add(p.moodButtonsContainer);

        // header
        p.header.add(p.topHeaderContainer);
        p.header.add(p.bottomHeaderContainer);

        // page
        p.content.add(p.taskDoneSectionContainer);
        p.content.add(p.createDivider());
        p.content.add(p.noteSectionContainer);
        p.content.add(p.createDivider());
        p.content.add(p.moodSectionContainer);

        savePeriodically(c.noteTextArea);
        updateContent();

        this.add(p.header, BorderLayout.NORTH);
        this.add(p.pageScrollPane, BorderLayout.CENTER);
        this.setFocusable(true);
        this.requestFocus();
    }

    public void showYesterday() {
        decreaseDay();
        updateContent();
    }

    public void decreaseDay() {
        if (day != 1) {
            day--;

            return;
        }

        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }

        day = checkMonthDays(month, year);
    }

    public void showTomorrow() {
        increaseDay();
        updateContent();
    }

    public void increaseDay() {
        if (day != checkMonthDays(month, year)) {
            day++;

            return;
        }

        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }

        day = 1;
    }

    public int checkMonthDays(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                if ((year % 4) != 0) {
                    yield 28;
                } else if ((year % 100) == 0 && (year % 400) != 0) {
                    yield 28;
                }
                yield 29;
            }
            default -> 0;
        };
    }

   public String getCorrectFormat(int num) {
        if (num < 10) {
            return "0" + num;
        }

        return String.valueOf(num);
   }

   public void updateContent() {
       saveNotes(c.noteTextArea.getText());
       date = LocalDateTime.parse(year + "-" + getCorrectFormat(month) + "-" + getCorrectFormat(day) + "T00:00:00");
       c.dayButton.setText(date.format(DateTimeFormatter.ofPattern("dd")));
       c.monthText.setText(date.format(DateTimeFormatter.ofPattern("MMMM")));
       c.yearText.setText(date.format(DateTimeFormatter.ofPattern("yyyy")));
       c.weekdayText.setText(date.format(DateTimeFormatter.ofPattern("E")));

       taskSectionSize = 40;
       minTaskSectionSize = 275;
       p.createContainer(p.taskDoneSectionContainer,
               new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, minTaskSectionSize);

       minNoteContainerSize = 209;
       p.createContainer(p.noteSectionContainer,
               new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, minNoteContainerSize + 50 + 7);

       p.content.setPreferredSize(new Dimension(Main.WIDTH, a.getTotalHeight(p.content) + 35));

       SwingWorker<Void, Void> worker = new SwingWorker<>() {
           @Override
           protected Void doInBackground() {
               try {
                   if (c.taskDoneText.getText().equals("Tasks")) {
                       getTasks();
                   } else {
                       getDone();
                   }

                   getNote();
                   getMood();
               } catch (SQLException e) {
                   throw new RuntimeException(e);
               }

               return null;
           }

           @Override
           protected void done() {
               SwingUtilities.invokeLater(() -> p.verticalScrollBar.setValue(0));
           }
       };

       worker.execute();

       this.requestFocus();
       repaint(p.pageScrollPane);
   }

    // --------------------- tasks ------------------------

    public void addNewTask(String buttonType, Integer colorCode, String taskText, String status, int taskId, boolean isFocused, boolean isEnabled){
        Container taskContainer = new Container();
        Container taskButtonContainer = new Container();
        Container taskTextFieldContainer = new Container();
        JButton taskAddButton = new JButton();
        JButton taskFinishButton = new JButton();
        JButton taskDoneButton = new JButton();
        JTextField taskTextField = new JTextField();

        taskSectionSize += 40;

        if ((taskSectionSize) > minTaskSectionSize - 35) {
            p.taskDoneSectionContainer.setPreferredSize(new Dimension(Main.WIDTH, taskSectionSize + 35));
            p.content.setPreferredSize(new Dimension(Main.WIDTH, a.getTotalHeight(p.content) + 35));
        }

        repaint(p.content);

        p.createContainer(taskContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), Main.WIDTH, 40);
        p.createContainer(taskButtonContainer,
                new FlowLayout(FlowLayout.LEADING,0,0), 44,40);
        p.createContainer(taskTextFieldContainer,
                new GridLayout(1,1, 0,0), (int) (Main.WIDTH - (Main.WIDTH/3.5)), 40);

        taskTextField.setText(taskText);
        taskTextField.setFont(a.toMontserrat(20));
        taskTextField.setBackground(a.toColor(Main.LIGHT_YELLOW));
        taskTextField.setHorizontalAlignment(JTextField.LEFT);
        taskTextField.setForeground(a.toColor(colorCode));
        taskTextField.setBorder(null);
        taskTextField.setEnabled(isEnabled);
        taskTextField.setDisabledTextColor(a.toColor(Main.LIGHT_BROWN));
        addActionListener(taskTextField);
        addCaretStart(taskTextField);

        taskAddButton.setIcon(a.addIcon);
        taskAddButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        taskAddButton.setPreferredSize(new Dimension(40,40));
        taskAddButton.setFocusable(false);
        taskAddButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskAddButton.addActionListener(this::saveTask);

        taskFinishButton.setIcon(a.circleIcon);
        taskFinishButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        taskFinishButton.setPreferredSize(new Dimension(40,40));
        taskFinishButton.setFocusable(false);
        taskFinishButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskFinishButton.addActionListener(this::finishTask);

        taskDoneButton.setIcon(a.doneIcon);
        taskDoneButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        taskDoneButton.setPreferredSize(new Dimension(40,40));
        taskDoneButton.setFocusable(false);
        taskDoneButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        taskDoneButton.addActionListener(this::revertTask);

        if (status.equals("done")) {
            taskButtonContainer.add(taskDoneButton);
        } else if (buttonType.equals("add")) {
            taskButtonContainer.add(taskAddButton);
        } else if (buttonType.equals("finish")) {
            taskButtonContainer.add(taskFinishButton);
        }

        taskTextFieldContainer.add(taskTextField);

        taskContainer.add(taskTextFieldContainer);
        taskContainer.add(taskButtonContainer);

        p.taskDoneSectionContainer.add(taskContainer);

        if (status.equals("done")) {
            doneList.add(new LinkedList<>(Arrays.asList(
                    taskContainer, // 0
                    taskButtonContainer, // 1
                    taskTextField, // 2
                    taskAddButton, // 3
                    taskFinishButton, // 4
                    taskDoneButton, // 5
                    taskId)) // 6
            );

            return;
        }

        taskList.add(new LinkedList<>(Arrays.asList(
                taskContainer, // 0
                taskButtonContainer, // 1
                taskTextField, // 2
                taskAddButton, // 3
                taskFinishButton, // 4
                taskDoneButton, // 5
                taskId)) // 6
        );

        addDocumentListener(taskTextField, taskList, a);

        if (isFocused) {
            taskTextField.requestFocus();
            taskTextField.setCaretPosition(0);
        }
    }

    public void getTasks() throws SQLException {
        LinkedList<LinkedList<Object>> resultList = d.getTasksFromTasks(Date.valueOf(date.toLocalDate()));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                if (resultList.isEmpty() && taskList.size() == 1) {
                    return null;
                }

                for (LinkedList<Object> task : taskList) {
                    p.taskDoneSectionContainer.remove((Component) task.getFirst());
                }
                for (LinkedList<Object> done : doneList) {
                    p.taskDoneSectionContainer.remove((Component) done.getFirst());
                }

                doneList.clear();
                taskList.clear();

                return null;
            }

            @Override
            protected void done() {
                // return if there is no tasks
                if (resultList.isEmpty() && taskList.size() == 1) {
                    return;
                }

                for (LinkedList<Object> taskInfo : resultList) {
                    addNewTask("finish", Main.BROWN, taskInfo.getFirst().toString(),
                            "pending", Integer.parseInt(taskInfo.getLast().toString()),
                            false, true);
                }

                addNewTask("add", Main.LIGHT_BROWN, "New Task", "pending", 0,
                        false, true);
            }
        };

        worker.execute();

        repaint(p.taskDoneSectionContainer);
    }

    public void saveTask(ActionEvent e) {
        Object component = e.getSource();

        for (int i = 0; i < taskList.size(); i++) {
            LinkedList<Object> task = taskList.get(i);

            // ignore lists that don't have the component
            if (!task.contains(component)) {
                continue;
            }

            JTextField textField = (JTextField) task.get(2);

            if (textField.getText().isEmpty() &&
                    i == taskList.size() - 1) {

                return;
            }

            // remove component if text field is empty
            if (textField.getText().isEmpty() && taskList.size() > 1) {
                try {
                    d.deleteFromTasks((Integer) task.getLast(), Date.valueOf(date.toLocalDate()));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                p.taskDoneSectionContainer.remove((Component) task.get(0));

                adjustTaskSectionSize(i, taskList);

                repaint(p.taskDoneSectionContainer);

                return;
            }

            JButton button = (JButton) task.get(4);
            Container buttonContainer = (Container) task.get(1);

            // ignore if there is no change when pressing enter
            if (buttonContainer.isAncestorOf(button)) {
                return;
            }

            // check if task is valid
            if (textField.getText().equals("New Task")) {
                return;

            } else if (textField.getText().isEmpty()) {
                textField.setText("New Task");
                textField.setForeground(a.toColor(Main.LIGHT_BROWN));
                textField.setCaretPosition(0);

                repaint(p.taskDoneSectionContainer);

                return;
            }

            Container container = (Container) task.get(1);
            container.remove((Component) task.get(3));
            container.add((Component) task.get(4));

            repaint(container);

            handleAddTask();

            // save to database
            try {
                if ((Integer) task.getLast() == 0) {
                    d.insertToTasks(textField.getText(), Date.valueOf(date.toLocalDate()));
                    task.set(6, d.getTaskIdOfLast());
                } else {
                    d.updateTaskTextToTasks(textField.getText(), (Integer) task.getLast());
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void handleAddTask(){
        JTextField textField = (JTextField) taskList.get(taskList.size() - 1).get(2);

        if (!textField.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
            addNewTask("add", Main.LIGHT_BROWN, "New Task",
                    "pending", 0, true, true);
            repaint(p.taskDoneSectionContainer);
        }
    }

    public void finishTask(ActionEvent e) {
        Object component = e.getSource();

        for (int i = 0; i < taskList.size(); i++) {
            LinkedList<Object> task = taskList.get(i);

            if (!task.contains(component)) {
                continue;
            }

            p.taskDoneSectionContainer.remove((Component) task.getFirst());

            try {
                d.updateStatusToTasks("done", (Integer) task.getLast());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            adjustTaskSectionSize(i, taskList);

            repaint(p.content);

            return;
        }
    }

    public void revertTask (ActionEvent e) {
        Object component = e.getSource();

        for (int i = 0; i < doneList.size(); i++) {
            LinkedList<Object> done = doneList.get(i);

            if (!done.contains(component)) {
                continue;
            }

            p.taskDoneSectionContainer.remove((Component) done.getFirst());

            try {
                d.updateStatusToTasks("pending", (Integer) done.getLast());
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            adjustTaskSectionSize(i, doneList);

            repaint(p.content);

            return;
        }
    }

    public void adjustTaskSectionSize (int index, LinkedList<LinkedList<Object>> list){
        list.remove(index);
        taskSectionSize -= 40;

        if (list.size()>=5){
            p.taskDoneSectionContainer.setPreferredSize(new Dimension(Main.WIDTH, taskSectionSize + 35));
            p.content.setPreferredSize(new Dimension(Main.WIDTH, a.getTotalHeight(p.content) + 20));
        }
    }

    // --------------------- notes ------------------------

    public void getNote() throws SQLException {
        String noteText = d.getNoteFromNotes(Date.valueOf(date.toLocalDate()));

        if (noteText.isEmpty()){
            if (c.noteTextArea.getForeground().equals(a.toColor(Main.BROWN))){
                p.noteTextAreaContainer.remove(c.noteTextArea);

                c.noteTextArea = new JTextArea();
                c.noteTextArea.setText("Tell us about your day.");
                c.noteTextArea.setFont(a.toMontserratMedium(15));
                c.noteTextArea.setBackground(a.toColor(Main.LIGHT_YELLOW));
                c.noteTextArea.setForeground(a.toColor(Main.LIGHT_BROWN));
                c.noteTextArea.setColumns(27);
                c.noteTextArea.setLineWrap(true);
                c.noteTextArea.setWrapStyleWord(true);
                c.noteTextArea.setBorder(null);

                addCaretStart(c.noteTextArea);
                addFocusRequest(p.noteTextAreaContainer, c.noteTextArea);
                addDocumentListener(c.noteTextArea, c.noteSaveButton);

                p.noteTextAreaContainer.add(c.noteTextArea);

                repaint(p.noteTextAreaContainer);
            }

            return;
        }

        c.noteTextArea.setText(noteText);
        c.noteTextArea.setForeground(a.toColor(Main.BROWN));
        SwingUtilities.invokeLater(() -> c.noteSaveButton.setIcon(a.savedIcon));
    }

    public void saveNotes(String noteText){
        if (noteText.equals("Tell us about your day.")){
            return;
        }

        if (c.noteSaveButton.getIcon().equals(a.savedIcon)){
            return;
        }

        c.noteSaveButton.setIcon(a.savedIcon);

        repaint(c.noteSaveButton);

        try {
            if (noteText.isEmpty()) {
                d.deleteFromNotes(Date.valueOf(date.toLocalDate()));

                return;
            }

            d.insertToNotes(Date.valueOf(date.toLocalDate()), noteText);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // --------------------- moods ------------------------

    public void selectMood(ActionEvent e) {
        try {
            if (e.getSource() == c.worstMoodButton) {
                d.insertToMoods(Date.valueOf(date.toLocalDate()), "worst");
            } else if (e.getSource() == c.badMoodButton) {
                d.insertToMoods(Date.valueOf(date.toLocalDate()), "bad");
            } else if (e.getSource() == c.fineMoodButton) {
                d.insertToMoods(Date.valueOf(date.toLocalDate()), "fine");
            } else if (e.getSource() == c.goodMoodButton) {
                d.insertToMoods(Date.valueOf(date.toLocalDate()), "good");
            } else if (e.getSource() == c.excellentMoodButton) {
                d.insertToMoods(Date.valueOf(date.toLocalDate()), "excellent");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void getMood() throws SQLException {
        String result = d.getMoodFromMoods(Date.valueOf(date.toLocalDate()));

        switch (result) {
            case "worst" -> c.worstMoodButton.setSelected(true);
            case "bad" -> c.badMoodButton.setSelected(true);
            case "fine" -> c.fineMoodButton.setSelected(true);
            case "good" -> c.goodMoodButton.setSelected(true);
            case "excellent" -> c.excellentMoodButton.setSelected(true);
            case "" -> p.moodButtonsGroup.clearSelection();
        }
    }

    // --------------------- done ------------------------

    public void swapTaskAccomplished(){
        taskSectionSize = 40;
        minTaskSectionSize = 275;
        p.createContainer(p.taskDoneSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, minTaskSectionSize);

        p.content.setPreferredSize(new Dimension(Main.WIDTH, a.getTotalHeight(p.content) + 35));

        if (c.taskDoneText.getText().equals("Tasks")) {
            c.taskDoneText.setText("Done");

            try {
                getDone();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return;
        }


        c.taskDoneText.setText("Tasks");

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    getTasks();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                return null;
            }

            @Override
            protected void done() {
                SwingUtilities.invokeLater(() -> p.verticalScrollBar.setValue(0));
            }
        };

        worker.execute();

        repaint(p.pageScrollPane);
    }

    public void getDone() throws SQLException {
        LinkedList<LinkedList<Object>> resultList = d.getDoneFromTasks(Date.valueOf(date.toLocalDate()));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                for (LinkedList<Object> task : taskList) {
                    p.taskDoneSectionContainer.remove((Component) task.getFirst());
                }

                for (LinkedList<Object> done : doneList) {
                    p.taskDoneSectionContainer.remove((Component) done.getFirst());
                }

                taskList.clear();
                doneList.clear();

                return null;
            }

            @Override
            protected void done() {
                // return if there is no tasks
                if (resultList.isEmpty()) {
                    return;
                }

                for (LinkedList<Object> doneInfo : resultList) {
                    addNewTask("finish", Main.BROWN, doneInfo.getFirst().toString(),
                            "done", Integer.parseInt(doneInfo.getLast().toString()),
                            false, false);
                }

//                addNewTask("add", Main.LIGHT_BROWN, "New Task", "done", 0, false);
            }
        };

        worker.execute();

        repaint(p.taskDoneSectionContainer);
    }

    public void savePeriodically(JTextArea textArea){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        int initialDelay = 5;
        int period = 5;

        scheduler.scheduleAtFixedRate(() -> saveNotes(textArea.getText()), initialDelay, period, TimeUnit.SECONDS);
    }

    public void addFocusRequest(Container container, JTextArea textArea) {
        container.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (textArea.getForeground().toString().equals(COLOR_LIGHT_BROWN)){
                    textArea.setCaretPosition(0);
                }
            }

            public void mouseClicked(MouseEvent e) {
                c.noteTextArea.requestFocusInWindow();
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

    public void addActionListener(JTextField textField) {
        textField.addActionListener(e -> {
            if (textField.getForeground().equals(a.toColor(Main.LIGHT_BROWN))) {
                return;
            }

            JTextField lastTextField = (JTextField) Objects.requireNonNull(taskList.peekLast()).get(2);

            if (lastTextField.getForeground().equals(a.toColor(Main.LIGHT_BROWN))) {
                lastTextField.requestFocus();
                lastTextField.setCaretPosition(0);
            }

            saveTask(e);
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
                    textArea.setForeground(a.toColor(Main.BROWN));
                }

                if (button.getIcon().equals(a.savedIcon)){
                    button.setIcon(a.saveIcon);
                }

                if (textArea.getPreferredSize().getHeight() + 19 <= minNoteContainerSize){
                    p.noteSectionContainer.setPreferredSize(
                            new Dimension(p.noteTextAreaContainer.getWidth(), minNoteContainerSize + (50 + 7)));

                } else {
                    p.noteTextAreaContainer.setPreferredSize(
                            new Dimension(p.noteTextAreaContainer.getWidth(),
                                    (int) textArea.getPreferredSize().getHeight() + 19));

                    p.noteSectionContainer.setPreferredSize(
                            new Dimension(p.noteTextAreaContainer.getWidth(),
                                    (int) textArea.getPreferredSize().getHeight() + (19 + 50 + 7)));
                }

                p.content.setPreferredSize(new Dimension(Main.WIDTH, a.getTotalHeight(p.content) + 20));

                repaint(p.content);
            }
        });
    }

    public void addDocumentListener(JTextField textField,
                                    LinkedList<LinkedList<Object>> tasks,
                                    AssetFactory a) {
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
                    textField.setForeground(a.toColor(Main.BROWN));
                    textField.setText(textField.getText().replaceFirst(
                            "New Task", ""));

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
        AssetFactory a = new AssetFactory();
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
            g.setColor(a.toColor(Main.LIGHT_YELLOW));
            g.fillRect(trackBounds.x + verticalTrackBoundX, trackBounds.y, trackBounds.width, trackBounds.height);
        }

        @Override
        protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
            thumbBounds.width = verticalScrollBarWidth;
            Graphics2D thumbEndGraphic = (Graphics2D) g.create();

            int arcWidth = 4;
            int arcHeight = 4;
            thumbEndGraphic.setColor(a.toColor(Main.BROWN));
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