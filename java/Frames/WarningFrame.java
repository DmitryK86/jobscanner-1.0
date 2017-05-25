package Frames;

import Engine.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmitry on 17.05.17.
 */
public class WarningFrame extends JFrame {
    private final String title = "Warning!";
    private final Dimension d = new Dimension(400, 120);

    JLabel label = new JLabel("You does not entered keywords of vacancy.");
    JLabel label2 = new JLabel(" All vacancies will be displayed.");
    JButton buttonOk = new JButton("Ok");
    JButton buttonCancel = new JButton("Cancel");


    public void init(){
        setTitle(title);
        setSize(d);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());


        add(label, new GridBagConstraints(0,0,2,1,1,1, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));
        add(label2, new GridBagConstraints(0,1,2,1,1,0.5, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));
        add(buttonOk, new GridBagConstraints(0,2,1,1,1,1, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(1,1,1,1),1,1));
        add(buttonCancel, new GridBagConstraints(1,2,1,1,1,1, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(1,1,1,1),0,0));

        buttonOk.addActionListener(new MyOkListener());
        buttonCancel.addActionListener(new MyCancelListener());

        setVisible(true);
        setResizable(false);
    }

    private class MyOkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            Frame[] frames = FirstFrame.getFrames();
            frames[0].dispose();
            Engine engine = new Engine();
            engine.begin();
        }
    }
    private class MyCancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}

