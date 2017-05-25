package Implements;

import Enums.Cities;
import Interfaces.IMakeUrl;

/**
 * Created by dmitry on 16.05.17.
 */
public class WorkUA implements IMakeUrl {

    private String keyWords;
    private Cities city;
    private final String URL = "https://www.work.ua/jobs-";

    public WorkUA(String keyWords, Cities city){
        this.keyWords = keyWords;
        this.city = city;
    }

    private String getCity(){
       switch (city) {
           case Киев:
               return "kyiv-";
           case Одесса:
               return "odesa-";
           case Днепр:
               return "dnipro-";
           case Харьков:
               return "kharkiv-";
           case Львов:
               return "lviv-";
           default:
               return "";
       }
    }

    public String makeUrl() {
        StringBuilder sb;
        if (keyWords.isEmpty()&&getCity()==""){
            return (URL.replace('-', '/') + "?ss=1");
        }
        else if (keyWords.isEmpty()){
            return (URL + getCity().replace('-', '/'));
        }
        else {
            String[] list = keyWords.split(" ");
            sb = new StringBuilder(URL + getCity());
            for (int i = 0; i <= list.length - 1; i++) {
                if (i == list.length - 1) {
                    sb.append(list[i] + "/");
                } else {
                    sb.append(list[i] + "+");
                }
            }
            return sb.toString();
        }

    }
}
