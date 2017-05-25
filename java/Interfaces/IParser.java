package Interfaces;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by dmitry on 16.05.17.
 */
public interface IParser {

    public LinkedHashMap<String, String> parse (String html);

}
