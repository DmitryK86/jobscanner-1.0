package Engine;

import Frames.FirstFrame;
import Frames.MainFrame;
import Implements.WorkUA;
import ParseHtml.ParserWorkUA;
import Scanner.ScanWebSite;

/**
 * Created by dmitry on 17.05.17.
 */
public class Engine {

    public static WorkUA workUA;
    public static MainFrame mainFrame;


    public void begin() {
        boolean[] sites = FirstFrame.sites;
        if (sites[0]){
           workUA = new WorkUA(FirstFrame.keyWords, FirstFrame.city);
        }


        ScanWebSite s = new ScanWebSite();
        String text = null;
        try {
            text = s.getHtmlCode(workUA);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ParserWorkUA parser = new ParserWorkUA();
        mainFrame = new MainFrame(parser.parse(text));
        mainFrame.init();

//        Loop loop = new Loop();
//        loop.start();

    }
}
