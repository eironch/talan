import javax.swing.*;
import java.awt.*;

public class AssetHandler {
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
            resizeIcon(new ImageIcon("assets/saveIcon.png"), 35, 35);
    ImageIcon savedIcon =
            resizeIcon(new ImageIcon("assets/savedIcon.png"), 35, 35);

    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        return new ImageIcon(icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}
