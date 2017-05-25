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
    public synchronized void refresh(LinkedHashMap<String, String> linkedHashMap){
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
        int n = 0;
        while (it.hasNext()){
            String newString = it.next();
            if (!compareTitle(labelsTitle, newString)){
                ArrayList<String> text = new ArrayList<>();
                for (int i = 0; i < labelsTitle.size()-1; i++) {
                    text.add(labelsTitle.get(i).getText());
                }
                labelsTitle.get(n).setText("New!");
                labelsTitle.get(n).setForeground(Color.red);
                labelsTitle.get(n).addMouseListener(new RefMouseListener(n, newString));
                for (int i = 0; i < text.size(); i++) {
                    labelsTitle.get(i+1).setText(text.get(i));
                }

            }
            n++;
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
        private int n;
        private String text;

        public RefMouseListener(int n, String text) {
            this.n = n;
            this.text = text;
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            labelsTitle.get(n).setText(text);
            labelsTitle.get(n).setForeground(Color.black);
        }
    }

    private boolean compareTitle(ArrayList<JLabel> list, String text){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().equalsIgnoreCase(text))
                return true;
        }
        return false;
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
