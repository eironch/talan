import javax.swing.*;
import java.awt.*;

public class ComponentFactory {
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
}
