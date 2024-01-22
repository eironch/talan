import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AssetFactory {
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
    ImageIcon addIcon =
            resizeIcon(new ImageIcon("assets/addIcon.png"), 30, 30);
    ImageIcon dividerLeftIcon =
            resizeIcon(new ImageIcon("assets/dividerLeftIcon.png"), 30, 4);
    ImageIcon dividerRightIcon =
            resizeIcon(new ImageIcon("assets/dividerRightIcon.png"), 30, 4);
    ImageIcon doneIcon =
            resizeIcon(new ImageIcon("assets/doneIcon.png"), 30, 30);
    ImageIcon saveIcon =
            resizeIcon(new ImageIcon("assets/saveIcon.png"), 30, 30);
    ImageIcon savedIcon =
            resizeIcon(new ImageIcon("assets/savedIcon.png"), 30, 30);
    ImageIcon worstMoodIcon =
            resizeIcon(new ImageIcon("assets/worstMoodIcon.png"), 50, 50);
    ImageIcon badMoodIcon =
            resizeIcon(new ImageIcon("assets/badMoodIcon.png"), 50, 50);
    ImageIcon fineMoodIcon =
            resizeIcon(new ImageIcon("assets/fineMoodIcon.png"), 50, 50);
    ImageIcon goodMoodIcon =
            resizeIcon(new ImageIcon("assets/goodMoodIcon.png"), 50, 50);
    ImageIcon excellentMoodIcon =
            resizeIcon(new ImageIcon("assets/excellentMoodIcon.png"), 50, 50);
    ImageIcon worstSelectedMoodIcon =
            resizeIcon(new ImageIcon("assets/worstSelectedMoodIcon.png"), 50, 50);
    ImageIcon badSelectedMoodIcon =
            resizeIcon(new ImageIcon("assets/badSelectedMoodIcon.png"), 50, 50);
    ImageIcon fineSelectedMoodIcon =
            resizeIcon(new ImageIcon("assets/fineSelectedMoodIcon.png"), 50, 50);
    ImageIcon goodSelectedMoodIcon =
            resizeIcon(new ImageIcon("assets/goodSelectedMoodIcon.png"), 50, 50);
    ImageIcon excellentSelectedMoodIcon =
            resizeIcon(new ImageIcon("assets/excellentSelectedMoodIcon.png"), 50, 50);
    ImageIcon swapTabIcon =
            resizeIcon(new ImageIcon("assets/swapTabIcon.png"), 45, 45);

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
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

    public void addMargin(JComponent component, int marginN, int marginE, int marginW, int marginS) {
        Border border = BorderFactory.createEmptyBorder(marginN, marginW, marginS, marginE);
        component.setBorder(border);
    }

    public int getTotalHeight(Container container) {
        int totalHeight = 0;

        for (Component component : container.getComponents()) {
            totalHeight += component.getPreferredSize().height;
        }

        return totalHeight;
    }
}
