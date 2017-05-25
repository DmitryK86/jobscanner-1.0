package Scanner;

import Interfaces.IMakeUrl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dmitry on 16.05.17.
 */
public class ScanWebSite {

    public String getHtmlCode (IMakeUrl makeUrl) throws Exception {
        URL url = new URL(makeUrl.makeUrl());
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            char[] buf = new char[1000];
            int r;

            do{
                if ((r = br.read(buf))>0)
                    sb.append(new String(buf,0,r));
            } while (r > 0);
        } finally {
            http.disconnect();
        }
        return sb.toString();
    }

}
