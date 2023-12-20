import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ComponentToolbox {
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
