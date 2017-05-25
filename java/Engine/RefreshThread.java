package Engine;

import ParseHtml.ParserWorkUA;
import Scanner.ScanWebSite;

/**
 * Created by dmitry on 24.05.17.
 */
public class RefreshThread extends Thread {
    @Override
    public void run() {
        System.out.println("Refresh!");
        ScanWebSite s = new ScanWebSite();
        String text = null;
        try {
            text = s.getHtmlCode(Engine.workUA);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ParserWorkUA parser = new ParserWorkUA();
        Engine.mainFrame.refresh(parser.parse(text));
    }
}
