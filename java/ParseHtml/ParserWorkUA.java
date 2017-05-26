package ParseHtml;

import Interfaces.IParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 16.05.17.
 */
public class ParserWorkUA implements IParser {

    public LinkedHashMap<String, String> parse (String html){
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
        ArrayList<String> arr = new ArrayList<>();

        Pattern pattern = Pattern.compile("(/jobs/)[0-9]+\\W+(title=)\\W[\\w\\s,\\.А-яа-яІіЇїє0-9-#\\(\\)/+]+");
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()){
            arr.add(html.substring(matcher.start(), matcher.end()));
        }

        for (String s : arr) {
            Pattern p1 = Pattern.compile("(/jobs/)[0-9]+");
            Pattern p2 = Pattern.compile("(title=)\\W[\\w\\s,\\.А-яа-яІіЇїє0-9-#\\(\\)/+]+");
            Matcher m1 = p1.matcher(s);
            Matcher m2 = p2.matcher(s);
            while (m1.find()&&m2.find()){
                list.put("https://www.work.ua" + s.substring(m1.start(), m1.end()), s.substring(m2.start()+7, m2.end()));
            }
        }
        return list;
    }
}
