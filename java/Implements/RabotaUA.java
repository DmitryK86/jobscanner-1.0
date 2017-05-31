package Implements;

import Enums.Cities;
import Interfaces.IMakeUrl;

/**
 * Created by dmitry on 25.05.17.
 */
public class RabotaUA implements IMakeUrl {

    private String keyWords;
    private Cities city;
    private final String URL = "https://rabota.ua/jobsearch/vacancy_list";

    public RabotaUA(String keyWords, Cities city) {
        this.keyWords = keyWords;
        this.city = city;
    }

    private String getCity(){
        switch (city) {
            case Киев:
                return "regionId=1";
            case Одесса:
                return "regionId=3";
            case Днепр:
                return "regionId=4";
            case Харьков:
                return "regionId=21";
            case Львов:
                return "regionId=2";
            default:
                return "";
        }
    }

    @Override
    public String makeUrl() {
        StringBuilder sb;
        if (keyWords.isEmpty()&&getCity()==""){
            return URL;
        }
        else if (keyWords.isEmpty()){
            return (URL + "?" + getCity());
        }
        else {
            String[] list = keyWords.split(" ");
            sb = new StringBuilder(URL);
            if (getCity()==""){
                sb.append("?keyWords=");
            }
            else {
                sb.append("?" + getCity() + "&keyWords=");
            }
            for (int i = 0; i <= list.length - 1; i++) {
                if (i == list.length - 1) {
                    sb.append(list[i]);
                } else {
                    sb.append(list[i] + "+");
                }
            }
            return sb.toString();
        }
    }
}
