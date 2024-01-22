import javax.swing.*;
import java.awt.*;

public class PanelFactory {
    AssetFactory a = new AssetFactory();
    JScrollBar verticalScrollBar;

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
    Container taskDoneSectionContainer = new Container();
    Container taskHeaderContainer = new Container();
    Container taskTextContainer = new Container();
    Container taskTabContainer = new Container();
    Container noteSectionContainer = new Container();
    Container noteHeaderContainer = new Container();
    Container noteTextContainer = new Container();
    Container noteSaveButtonContainer = new Container();
    Container noteTextAreaContainer = new Container();
    Container moodSectionContainer = new Container();
    Container moodContextContainer = new Container();
    Container moodButtonsContainer = new Container();
    ButtonGroup moodButtonsGroup = new ButtonGroup();

    public PanelFactory() {
        header.setBackground(a.toColor(Main.YELLOW));
        header.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        header.setPreferredSize(new Dimension(Main.WIDTH, 130));
        a.addMargin(header,20,0,0,0);

        content.setBackground(a.toColor(Main.LIGHT_YELLOW));
        content.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        content.setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT-170));
        a.addMargin(content, 15,16,0,0);

        pageScrollPane = new JScrollPane(content);
        pageScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pageScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pageScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        pageScrollPane.getVerticalScrollBar().setBackground(a.toColor(Main.LIGHT_YELLOW));
        pageScrollPane.getVerticalScrollBar().setUI(new MainView.CustomScrollBarUI());
        pageScrollPane.getVerticalScrollBar().setBorder(null);
        pageScrollPane.getVerticalScrollBar().setFocusable(false);
        pageScrollPane.setBorder(null);
        verticalScrollBar = pageScrollPane.getVerticalScrollBar();


        // ----------------- top header -------------------
        createContainer(topHeaderContainer, new FlowLayout(), Main.WIDTH, 65);

        // container
        createContainer(sidebarContainer,
                new FlowLayout(FlowLayout.LEADING, 22, 0), (int) (Main.WIDTH/4.5),100);
        createContainer(monthContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 2), (int) (Main.WIDTH/2.1),100);
        createContainer(dayContainer,
                new FlowLayout(FlowLayout.TRAILING, 10, 0), (int) (Main.WIDTH/4.5),100);

        datePanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));


        // ----------------- bottom header --------------------

        createContainer(bottomHeaderContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), Main.WIDTH, 40);

        weekdayContainer.setLayout(new FlowLayout(FlowLayout.LEADING, 19, 0));
        weekdayContainer.setPreferredSize(new Dimension((int) (Main.WIDTH/4.5),100));
        progressContainer.setLayout(new FlowLayout(FlowLayout.CENTER));
        progressContainer.setPreferredSize(new Dimension((int) (Main.WIDTH/2.1),100));
        yearContainer.setLayout(new FlowLayout(FlowLayout.TRAILING, 14,0));
        yearContainer.setPreferredSize(new Dimension((int) (Main.WIDTH/4.5),100));

        // ----------------- task section --------------------

        createContainer(taskDoneSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, 275);

        // header
        createContainer(taskHeaderContainer,
                new GridLayout(0,2,0,0), Main.WIDTH, 50);

        // content
        createContainer(taskTextContainer,
                new FlowLayout(FlowLayout.LEADING, 54,3), Main.WIDTH, Main.HEIGHT);
        createContainer(taskTabContainer,
                new FlowLayout(FlowLayout.TRAILING, 55,0), Main.WIDTH, Main.HEIGHT);

        createContainer(noteSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, 266);

        createContainer(noteHeaderContainer,
                new GridLayout(0,2,0,0), Main.WIDTH, 50);
        createContainer(noteTextContainer,
                new FlowLayout(FlowLayout.LEADING, 54,3), Main.WIDTH, Main.HEIGHT);
        createContainer(noteSaveButtonContainer,
                new FlowLayout(FlowLayout.TRAILING, 55,0), Main.WIDTH, Main.HEIGHT);

        createContainer(noteTextAreaContainer,
                new FlowLayout(FlowLayout.LEADING, 5,8),
                (int) (Main.WIDTH - (Main.WIDTH/5.2)), 209);

        // --------------- mood section -----------------

        createContainer(moodSectionContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 0), Main.WIDTH, 130);
        createContainer(moodContextContainer,
                new FlowLayout(FlowLayout.CENTER, 0, 20), Main.WIDTH, 65);
        createContainer(moodButtonsContainer,
                new FlowLayout(FlowLayout.CENTER, 20, 0), Main.WIDTH, 65);
    }

    public void createContainer(JPanel panel, FlowLayout layout, int width, int height){
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(width, height));
    }

    public void createContainer(Container container, FlowLayout layout, int width, int height){
        container.setLayout(layout);
        container.setPreferredSize(new Dimension(width, height));
    }

    public void createContainer(JPanel panel, FlowLayout layout){
        panel.setLayout(layout);
    }

    public void createContainer(Container container, FlowLayout layout){
        container.setLayout(layout);
    }

    public void createContainer(JPanel panel, GridLayout layout, int width, int height){
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(width, height));
    }

    public void createContainer(Container container, GridLayout layout, int width, int height){
        container.setLayout(layout);
        container.setPreferredSize(new Dimension(width, height));
    }
    public void createContainer(JPanel panel, BorderLayout layout, int width, int height){
        panel.setLayout(layout);
        panel.setPreferredSize(new Dimension(width, height));
    }

    public void createContainer(Container container, BorderLayout layout, int width, int height){
        container.setLayout(layout);
        container.setPreferredSize(new Dimension(width, height));
    }


    public Container createDivider(){
        Container dividerSectionContainer = new Container();
        Container dividerContainer = new Container();
        JPanel divider = new JPanel();
        JLabel dividerLeft = new JLabel();
        JLabel dividerRight = new JLabel();

        createContainer(dividerSectionContainer,
                new FlowLayout(), Main.WIDTH,15);
        createContainer(dividerContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), Main.WIDTH, 4);

        dividerLeft.setIcon(a.dividerLeftIcon);
        dividerLeft.setPreferredSize(new Dimension(a.dividerLeftIcon.getIconWidth(),8));
        dividerLeft.setVerticalAlignment(SwingConstants.TOP);

        dividerRight.setIcon(a.dividerRightIcon);
        dividerRight.setPreferredSize(new Dimension(a.dividerRightIcon.getIconWidth(),8));
        dividerRight.setVerticalAlignment(SwingConstants.TOP);

        divider.setBackground(a.toColor(Main.BROWN));
        divider.setPreferredSize(new Dimension(Main.WIDTH - (Main.WIDTH/4),8));

        dividerContainer.add(dividerLeft);
        dividerContainer.add(divider);
        dividerContainer.add(dividerRight);

        dividerSectionContainer.add(dividerContainer);

        return dividerSectionContainer;
    }

    public Container createDivider(int marginN, int marginS){
        Container dividerSectionContainer = new Container();
        Container dividerContainer = new Container();
        Container dividerAssetContainer = new Container();
        JPanel divider = new JPanel();
        JLabel dividerLeft = new JLabel();
        JLabel dividerRight = new JLabel();

        createContainer(dividerSectionContainer,
                new GridLayout(), Main.WIDTH, 15 + marginS);
        createContainer(dividerContainer,
                new FlowLayout(FlowLayout.CENTER), Main.WIDTH,15);
        createContainer(dividerAssetContainer,
                new FlowLayout(FlowLayout.CENTER, 0,0), Main.WIDTH, 4);

        dividerLeft.setIcon(a.dividerLeftIcon);
        dividerLeft.setPreferredSize(new Dimension(a.dividerLeftIcon.getIconWidth(),8));
        dividerLeft.setVerticalAlignment(SwingConstants.TOP);

        dividerRight.setIcon(a.dividerRightIcon);
        dividerRight.setPreferredSize(new Dimension(a.dividerRightIcon.getIconWidth(),8));
        dividerRight.setVerticalAlignment(SwingConstants.TOP);

        divider.setBackground(a.toColor(Main.BROWN));
        divider.setPreferredSize(new Dimension(Main.WIDTH - (Main.WIDTH/4),8));

        dividerAssetContainer.add(dividerLeft);
        dividerAssetContainer.add(divider);
        dividerAssetContainer.add(dividerRight);

        dividerContainer.add(dividerAssetContainer);
        dividerSectionContainer.add(dividerContainer);

        return dividerSectionContainer;
    }
}
