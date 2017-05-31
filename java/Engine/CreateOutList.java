package Engine;

import Enums.Cities;
import Frames.FirstFrame;
import Implements.HeadHunterUA;
import Implements.RabotaUA;
import Implements.WorkUA;
import Interfaces.IMakeUrl;
import Interfaces.IParser;
import ParseHtml.ParserHeadHunterUA;
import ParseHtml.ParserRabotaUA;
import ParseHtml.ParserWorkUA;
import Scanner.ScanWebSite;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by dmitry on 29.05.17.
 */
public class CreateOutList {
    private String keyWords = FirstFrame.keyWords;
    private Cities city = FirstFrame.city;
    private boolean[] sites = FirstFrame.sites;
    private IMakeUrl[] urls = {new WorkUA(keyWords, city), new RabotaUA(keyWords, city), new HeadHunterUA(keyWords, city)};
    private IParser[] parsers = {new ParserWorkUA(), new ParserRabotaUA(), new ParserHeadHunterUA()};
    private VerifyOutList vol = new VerifyOutList();

    public LinkedHashMap<String, String> create(){
        LinkedHashMap<String, String> list = new LinkedHashMap<>();
        ArrayList<IMakeUrl> listUrls = new ArrayList<>();
        ArrayList<IParser> listParsers = new ArrayList<>();
        for (int i = 0; i < sites.length; i++) {
            if (sites[i]) {
                listUrls.add(urls[i]);
                listParsers.add(parsers[i]);
            }
        }
        ArrayList<String> text = new ArrayList<>();
        for (IMakeUrl m : listUrls) {
            ScanWebSite sc = new ScanWebSite();
            try {
                text.add(sc.getHtmlCode(m));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int t = 0;
        for (String s : text) {
            list.putAll(listParsers.get(t).parse(s));
            t++;
        }

        return vol.verify(list, keyWords);
    }
}
