import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ComponentFactory {
    AssetFactory asset = new AssetFactory();
    AssetFactory a = new AssetFactory();

    // button
    JButton arrowLeftButton = new JButton();
    JButton arrowRightButton = new JButton();
    JButton sidebarButton = new JButton();
    JButton dayButton = new JButton();
    JButton taskTabButton = new JButton();
    JButton noteSaveButton = new JButton();

    // checkbox
    JRadioButton worstMoodButton = new JRadioButton();
    JRadioButton badMoodButton = new JRadioButton();
    JRadioButton fineMoodButton = new JRadioButton();
    JRadioButton goodMoodButton = new JRadioButton();
    JRadioButton excellentMoodButton = new JRadioButton();

    // label
    JLabel monthText = new JLabel();
    //    JLabel progressBar = new JLabel();
    JLabel weekdayText = new JLabel();
    JLabel yearText = new JLabel();
    JLabel taskDoneText = new JLabel();
    JLabel noteText = new JLabel();
    JLabel moodContext = new JLabel();

    // text area
    JTextArea noteTextArea = new JTextArea();
    LocalDateTime date;

    public ComponentFactory() {
        date = LocalDateTime.now();

        // ----------------- top header -------------------
        sidebarButton.setIcon(asset.sidebarIcon);
        sidebarButton.setPreferredSize(new Dimension(50, 50));
        sidebarButton.setBackground(a.toColor(Main.YELLOW));
        sidebarButton.setFocusable(false);
        sidebarButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        monthText.setText(date.format(DateTimeFormatter.ofPattern("MMMM")));
        monthText.setForeground(a.toColor(Main.BROWN));
        monthText.setFont(a.toMontserrat(35));
        arrowLeftButton.setIcon(asset.arrowLeftIcon);
        arrowLeftButton.setPreferredSize(new Dimension(30,50));
        arrowLeftButton.setBackground(a.toColor(Main.YELLOW));
        arrowLeftButton.setFocusable(false);
        arrowLeftButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        dayButton.setText(date.format(DateTimeFormatter.ofPattern("dd")));
        dayButton.setForeground(a.toColor(Main.BROWN));
        dayButton.setFont(a.toMontserrat(25));
        dayButton.setHorizontalTextPosition(JLabel.CENTER);
        dayButton.setVerticalTextPosition(JLabel.CENTER);
        dayButton.setIcon(asset.roundSquareIcon);
        dayButton.setBackground(a.toColor(Main.YELLOW));
        dayButton.setFocusable(false);
        dayButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        arrowRightButton.setIcon(asset.arrowRightIcon);
        arrowRightButton.setPreferredSize(new Dimension(30,50));
        arrowRightButton.setBackground(a.toColor(Main.YELLOW));
        arrowRightButton.setFocusable(false);
        arrowRightButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        // ----------------- bottom header --------------------

        weekdayText.setText(date.format(DateTimeFormatter.ofPattern("E")));
        weekdayText.setForeground(a.toColor(Main.BROWN));
        weekdayText.setFont(a.toMontserrat(30));
        yearText.setText(date.format(DateTimeFormatter.ofPattern("yyyy")));
        yearText.setForeground(a.toColor(Main.BROWN));
        yearText.setFont(a.toMontserrat(30));

        // ------------------ task section ---------------------

        taskDoneText.setText("Tasks");
        taskDoneText.setForeground(a.toColor(Main.BROWN));
        taskDoneText.setFont(a.toMontserrat(35));

        taskTabButton.setIcon(asset.swapTabIcon);
        taskTabButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        taskTabButton.setPreferredSize(new Dimension(50,50));
        taskTabButton.setFocusable(false);
        taskTabButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));

        // ----------------- note section -----------------

        noteText.setText("Notes");
        noteText.setForeground(a.toColor(Main.BROWN));
        noteText.setFont(a.toMontserrat(35));

        noteSaveButton.setIcon(asset.savedIcon);
        noteSaveButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        noteSaveButton.setPreferredSize(new Dimension(50,50));
        noteSaveButton.setFocusable(false);
        noteSaveButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, Color.BLACK));
        noteTextArea.setText("Tell us about your day.");
        noteTextArea.setFont(a.toMontserratMedium(15));
        noteTextArea.setBackground(a.toColor(Main.LIGHT_YELLOW));
        noteTextArea.setForeground(a.toColor(Main.LIGHT_BROWN));
        noteTextArea.setColumns(27);
        noteTextArea.setLineWrap(true);
        noteTextArea.setWrapStyleWord(true);
        noteTextArea.setBorder(null);

        // --------------- mood section -----------------

        moodContext.setText("How do you feel today?");
        moodContext.setForeground(a.toColor(Main.BROWN));
        moodContext.setFont(a.toMontserrat(20));

        worstMoodButton.setFocusable(false);
        worstMoodButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        worstMoodButton.setIcon(asset.worstMoodIcon);
        worstMoodButton.setSelectedIcon(asset.worstSelectedMoodIcon);
        badMoodButton.setFocusable(false);
        badMoodButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        badMoodButton.setIcon(asset.badMoodIcon);
        badMoodButton.setSelectedIcon(asset.badSelectedMoodIcon);
        fineMoodButton.setFocusable(false);
        fineMoodButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        fineMoodButton.setIcon(asset.fineMoodIcon);
        fineMoodButton.setSelectedIcon(asset.fineSelectedMoodIcon);
        goodMoodButton.setFocusable(false);
        goodMoodButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        goodMoodButton.setIcon(asset.goodMoodIcon);
        goodMoodButton.setSelectedIcon(asset.goodSelectedMoodIcon);
        excellentMoodButton.setFocusable(false);
        excellentMoodButton.setBackground(a.toColor(Main.LIGHT_YELLOW));
        excellentMoodButton.setIcon(asset.excellentMoodIcon);
        excellentMoodButton.setSelectedIcon(asset.excellentSelectedMoodIcon);
    }
}
