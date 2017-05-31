package Implements;

import Enums.Cities;
import Interfaces.IMakeUrl;

/**
 * Created by dmitry on 29.05.17.
 */
public class HeadHunterUA implements IMakeUrl {
    private String keyWords;
    private Cities city;
    private final String URL = "https://hh.ua/search/vacancy?";

    public HeadHunterUA(String keyWords, Cities city) {
        this.keyWords = keyWords;
        this.city = city;
    }

    private String getCity(){
        switch (city) {
            case Киев:
                return "area=115";
            case Одесса:
                return "area=127";
            case Днепр:
                return "area=117";
            case Харьков:
                return "area=135";
            case Львов:
                return "area=125";
            default:
                return "area=5";
        }
    }

    @Override
    public String makeUrl() {
        StringBuilder sb;
        if (keyWords.isEmpty()&&getCity()==""){
            return (URL + "enable_snippets=true&currency_code=UAH&clusters=true&area=5&from=cluster_area");
        }
        else if (keyWords.isEmpty()){
            return (URL + getCity());
        }
        else {
            String[] list = keyWords.split(" ");
            sb = new StringBuilder(URL + "text=");

            for (int i = 0; i <= list.length - 1; i++) {
                if (i == list.length - 1) {
                    sb.append(list[i] + "&" + getCity());
                } else {
                    sb.append(list[i] + "+");
                }
            }
            return sb.toString();
        }
    }
}
