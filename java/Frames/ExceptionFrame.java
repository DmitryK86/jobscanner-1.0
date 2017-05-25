package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by dmitry on 16.05.17.
 */
public class ExceptionFrame extends JFrame {
    private final String title = "Error!";
    private final Dimension d = new Dimension(400, 120);

    JLabel label = new JLabel("Select at least one site!");
    JButton buttonOk = new JButton("Ok");

    public void init(){
        setTitle(title);
        setSize(d);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        buttonOk.addActionListener(new MyButtoListener());

        add(label, new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE, new Insets(1,1,1,1),0,0));
        add(buttonOk, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE, new Insets(1,1,1,1),0,0));

        setVisible(true);
        setResizable(false);
    }

    class MyButtoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}
