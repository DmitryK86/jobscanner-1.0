package Engine;

import Frames.FirstFrame;
import Frames.MainFrame;

import java.util.Iterator;
import java.util.LinkedHashMap;

/**
 * Created by dmitry on 17.05.17.
 */
public class Engine {

    public static MainFrame mainFrame;
    public CreateOutList list = new CreateOutList();

    public void begin() {
        mainFrame = new MainFrame(list.create());
        mainFrame.init();

    }
}
