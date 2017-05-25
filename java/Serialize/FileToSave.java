package Serialize;

import java.io.*;
import java.util.LinkedHashSet;

/**
 * Created by dmitry on 23.05.17.
 */
public class FileToSave implements Serializable {

    public LinkedHashSet<String> set;

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream("temp.out");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            try {
                oos.writeObject(this.set);
            }finally {
                oos.flush();
                oos.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public FileToSave(){
        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream ois = new ObjectInputStream(fis);
            try {
                this.set = (LinkedHashSet<String>) ois.readObject();
            }finally {
                ois.close();
            }
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
