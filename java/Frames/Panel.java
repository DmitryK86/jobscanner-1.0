package Frames;

import Serialize.FileToSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by dmitry on 18.05.17.
 */
public class Panel extends JPanel{

    private ArrayList<JLabel> labelsTitle = new ArrayList<>();
    private ArrayList<JLabel> labelsURI = new ArrayList<>();
    public FileToSave f = new FileToSave();



    public Panel (LinkedHashMap<String, String> linkedHashMap) {
        super();
        this.setLayout(new GridBagLayout());
        Iterator<String> it = linkedHashMap.values().iterator();
        while (it.hasNext()){
            labelsTitle.add(new JLabel(it.next()));
        }
        it = linkedHashMap.keySet().iterator();
        while (it.hasNext()){
            String str = it.next();
            JLabel label = new JLabel(str);
            if (compareURL(f.set, str)){
                label.setForeground(Color.red);
            }
            else {
                label.setForeground(Color.blue);
            }
            label.setCursor(new Cursor(Cursor.HAND_CURSOR));
            label.addMouseListener(new MyMouseListener(label));
            labelsURI.add(label);
        }
        for (int i = 0; i < labelsTitle.size(); i++) {
            this.add(labelsTitle.get(i), new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 0, 0));
            this.add(labelsURI.get(i), new GridBagConstraints(1, i,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE, new Insets(1,1,1,1), 0,0));
        }
    }
    public void refresh(LinkedHashMap<String, String> linkedHashMap){
        if (linkedHashMap.size() > labelsTitle.size()){
            int p = linkedHashMap.size() - labelsTitle.size();
            for (int i = 0; i < p; i++) {
                labelsTitle.add(new JLabel());
                this.add(labelsTitle.get(labelsTitle.size()-1), new GridBagConstraints(0, i, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 0, 0));
                labelsURI.add(new JLabel());
                this.add(labelsURI.get(labelsURI.size()-1), new GridBagConstraints(1, i, 1, 1, 1, 1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1, 1, 1, 1), 0, 0));
            }
        }
        Iterator<String> it = linkedHashMap.values().iterator();
        ArrayList<String> list = getTextFromLabels(labelsTitle);
        ArrayList<String> newList = new ArrayList<>();
        int n = 0;
        while (it.hasNext()) {
            String str = it.next();
            if (!list.contains((String) str)){
                newList.add(str);
                n++;
            }
        }
        if (n > 0) {
            newList.addAll(list.subList(0, list.size() - n));
            setTextToLabels(newList);
            for (int i = 0; i < n; i++) {
                labelsTitle.get(i).setForeground(Color.magenta);
            }
        }

        it = linkedHashMap.keySet().iterator();
        int p = 0;
        while (it.hasNext()){
            String str = it.next();
            labelsURI.get(p).setText(str);
            if (compareURL(f.set, str)){
                labelsURI.get(p).setForeground(Color.red);
            }
            else {
                labelsURI.get(p).setForeground(Color.blue);
            }
            p++;
        }
        this.updateUI();
    }

    class MyMouseListener extends MouseAdapter {

        private JLabel label;

        public MyMouseListener(JLabel label) {
            this.label = label;
        }

        public void mouseClicked(MouseEvent e) {
            try {
                Desktop.getDesktop().browse(new URI(label.getText()));
            } catch (URISyntaxException | IOException ex) {
                System.out.println("Some exception!");
            }
            f.set.add(label.getText());
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            label.setForeground(Color.red);
        }
    }
    class RefMouseListener extends MouseAdapter {
        int label;

        public RefMouseListener(int label) {
            this.label = label;
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            if (labelsTitle.get(label).getForeground() != Color.black){
                labelsTitle.get(label).setForeground(Color.black);
            }
        }
    }

    private ArrayList<String> getTextFromLabels(ArrayList<JLabel> list){
        ArrayList<String> text = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            text.add(list.get(i).getText());
        }
        return text;
    }
    private void setTextToLabels(ArrayList<String> list){
        for (int i = 0; i < labelsTitle.size(); i++) {
            labelsTitle.get(i).setText(list.get(i));
            labelsTitle.get(i).addMouseListener(new RefMouseListener(i));
        }
    }
    private boolean compareURL(LinkedHashSet<String> set, String text){
        Iterator<String> it = set.iterator();
        while (it.hasNext()){
            if (it.next().equalsIgnoreCase(text))
                return true;
        }
        return false;
    }
}

