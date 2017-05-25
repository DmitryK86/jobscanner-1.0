package Frames;

import Engine.*;
import Enums.Cities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by dmitry on 16.05.17.
 */
public class FirstFrame extends JFrame {
    public static final String TITLE = "Job Scanner";
    public static final Dimension DIMENSION = new Dimension(220, 250);

    public static String keyWords = "";
    public static boolean[] sites = new boolean[3];
    public static Cities city;

    Cities[] cities = Cities.values();

    JLabel labelSite = new JLabel("Select sites:");
    JRadioButton radioButton1 = new JRadioButton("Work.ua");
    JRadioButton radioButton2 = new JRadioButton("Rabota.ua");
    JRadioButton radioButton3 = new JRadioButton("HeadHunter.ua");
    JLabel labelCity = new JLabel("Select city:");
    JComboBox comboBox = new JComboBox(cities);
    JLabel labelKeyWords = new JLabel("Enter keywords of vacancy:");
    JTextField textField = new JTextField();
    JButton button = new JButton("Start!");
    JLabel dev = new JLabel("Created by dmitryk86");


    public void init(){
        setResizable(false);
        setTitle(TITLE);
        setSize(DIMENSION);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(MyPoint.getPoint(this));
        setLayout(new GridBagLayout());
        setAlwaysOnTop(true);

        add(labelSite, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));
        add(radioButton1, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1,30,1,1),1,1));
        add(radioButton2, new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1,30,1,1),1,1));
        add(radioButton3, new GridBagConstraints(0,3,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1,30,1,1),1,1));
        add(labelCity, new GridBagConstraints(0,4,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));
        add(comboBox, new GridBagConstraints(0,5,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1,1,1,1),1,1));
        add(labelKeyWords, new GridBagConstraints(0,6,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));
        add(textField, new GridBagConstraints(0,7,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1,1,1,1),1,1));
        add(button, new GridBagConstraints(0,8,1,1,1,1,GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(1,1,1,1),1,1));
        add(dev, new GridBagConstraints(0,9,1,1,1,1,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));


        textField.setFont(new Font(null, 1,16));
        dev.setFont(new Font(null, 0,8));
        button.addActionListener(new ButtonListener());

        setVisible(true);
    }

    public class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            keyWords = textField.getText();
            sites[0] = radioButton1.isSelected();
            sites[1] = radioButton2.isSelected();
            sites[2] = radioButton3.isSelected();
            city = (Cities) comboBox.getSelectedItem();
            if (sites[0]==false&&sites[1]==false&&sites[2]==false){
                ExceptionFrame frame = new ExceptionFrame();
                frame.init();
            }
            else if (keyWords.isEmpty()){
                WarningFrame wf = new WarningFrame();
                wf.init();
            }
            else {
                dispose();
                Engine engine = new Engine();
                engine.begin();
            }
        }
    }
}
