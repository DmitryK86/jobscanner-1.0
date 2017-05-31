package Engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dmitry on 30.05.17.
 */
public class VerifyOutList {
    public LinkedHashMap<String, String> verify(LinkedHashMap<String, String> list, String keyWords) {
        String[] words = keyWords.split(" ");
        StringBuilder sb = new StringBuilder("[\\w\\s\\W\\S]*");
        for (int i = 0; i < words.length; i++) {
            char c =  words[i].charAt(0);
            String s0 = words[i].replace(c,' ').trim();
            String s1 = String.valueOf(c).toUpperCase();
            String s2 = String.valueOf(c).toLowerCase();
            String res = "["+s1+"|"+s2+"]"+s0;
            sb.append("(" + res + ")")
                    .append("[\\w\\s\\W\\S]*");
        }
        String regExp = sb.toString();
        Pattern p = Pattern.compile(regExp);
        ArrayList<String> l = new ArrayList<>();
        Iterator<String> val = list.values().iterator();
        Iterator<String> keys = list.keySet().iterator();
        while (val.hasNext()&&keys.hasNext()) {
            String verify = val.next();
            String key = keys.next();
            Matcher m = p.matcher(verify);
            if (!m.matches()) {
                l.add(key);
            }
        }
        for (String str : l) {
            list.remove(str);
        }

        return list;
    }
}
