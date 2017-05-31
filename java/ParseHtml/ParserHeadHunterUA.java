package ParseHtml;

import Interfaces.IParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 29.05.17.
 */
public class ParserHeadHunterUA implements IParser {
    @Override
    public LinkedHashMap<String, String> parse(String html) {
        LinkedHashMap<String, String> list = new LinkedHashMap<>();


        Pattern p1 = Pattern.compile("hh.ua/vacancy/[\\d]+");
        Pattern p2 = Pattern.compile("vacancy-name\">\\s*([\\w\\s,\\.А-яа-яІіЇїє0-9-#\\(\\)/+]+)</div>");
        Matcher m1 = p1.matcher(html);
        Matcher m2 = p2.matcher(html);


        while (m1.find() && m2.find()) {
            String s = html.substring(m2.start() + 14, m2.end() - 6);
            String regex = "\\s{3,}";
            String replace = "";
            String res = replaceAll(regex, replace, s);
            list.put("https://" + m1.group(), res);
        }
        return list;
    }

    public String replaceAll(String regex, String replacement, String text) {
        return Pattern.compile(regex).matcher(text).replaceAll(replacement);
    }
}
