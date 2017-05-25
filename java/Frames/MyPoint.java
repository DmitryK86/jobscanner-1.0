package Frames;

import javax.swing.*;
import java.awt.*;

/**
 * Created by dmitry on 17.05.17.
 */
public class MyPoint {
    public static Point getPoint(JFrame jFrame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = jFrame.getSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(jFrame.getGraphicsConfiguration());
        int taskBarSize = 25; //scnMax.bottom;
        Point p = new Point();
        p.setLocation(screenSize.getWidth() - size.getWidth(), screenSize.getHeight() - size.getHeight() - taskBarSize);
        return p;
    }
}
