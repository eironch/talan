import javax.swing.*;
import java.awt.*;

public class ComponentFactory {
    AssetHandler asset = new AssetHandler();
    ComponentToolbox tool = new ComponentToolbox();
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

        dividerLeft.setIcon(asset.dividerLeftIcon);
        dividerLeft.setPreferredSize(new Dimension(asset.dividerLeftIcon.getIconWidth(),8));
        dividerLeft.setVerticalAlignment(SwingConstants.TOP);

        dividerRight.setIcon(asset.dividerRightIcon);
        dividerRight.setPreferredSize(new Dimension(asset.dividerRightIcon.getIconWidth(),8));
        dividerRight.setVerticalAlignment(SwingConstants.TOP);

        divider.setBackground(tool.toColor(Main.BROWN));
        divider.setPreferredSize(new Dimension(Main.WIDTH - (Main.WIDTH/4),8));

        dividerAssetContainer.add(dividerLeft);
        dividerAssetContainer.add(divider);
        dividerAssetContainer.add(dividerRight);

        dividerContainer.add(dividerAssetContainer);
        dividerSectionContainer.add(dividerContainer);

        return dividerSectionContainer;
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

        dividerLeft.setIcon(asset.dividerLeftIcon);
        dividerLeft.setPreferredSize(new Dimension(asset.dividerLeftIcon.getIconWidth(),8));
        dividerLeft.setVerticalAlignment(SwingConstants.TOP);

        dividerRight.setIcon(asset.dividerRightIcon);
        dividerRight.setPreferredSize(new Dimension(asset.dividerRightIcon.getIconWidth(),8));
        dividerRight.setVerticalAlignment(SwingConstants.TOP);

        divider.setBackground(tool.toColor(Main.BROWN));
        divider.setPreferredSize(new Dimension(Main.WIDTH - (Main.WIDTH/4),8));

        dividerContainer.add(dividerLeft);
        dividerContainer.add(divider);
        dividerContainer.add(dividerRight);

        dividerSectionContainer.add(dividerContainer);

        return dividerSectionContainer;
    }
}
