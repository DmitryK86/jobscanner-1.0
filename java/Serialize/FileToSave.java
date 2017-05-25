package Serialize;

import java.io.*;
import java.util.LinkedHashSet;

/**
 * Created by dmitry on 23.05.17.
 */
public class FileToSave implements Serializable {

    public LinkedHashSet<String> set;
    private File file = new File("temp.out");

    public void save(){
        try {
            FileOutputStream fos = new FileOutputStream(file);
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
    public FileToSave() {
        if (!file.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                LinkedHashSet<String> st = new LinkedHashSet<>();
                st.add(" ");
                try {
                    oos.writeObject(st);
                } finally {
                    oos.flush();
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileInputStream fis = new FileInputStream(file);
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
