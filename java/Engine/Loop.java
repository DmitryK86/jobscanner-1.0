package Engine;


import ParseHtml.ParserWorkUA;
import Scanner.ScanWebSite;

/**
 * Created by dmitry on 19.05.17.
 */
public class Loop extends Thread {

    private int time;

    public Loop(int time) {
        this.time = time * 60000;
    }

    public void sleep(){
        try {
            this.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {

            sleep();
            System.out.println("run");

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
}
