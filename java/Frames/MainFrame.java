package Frames;

import Engine.Loop;
import Engine.RefreshThread;
import Implements.WorkUA;
import ParseHtml.ParserWorkUA;
import Scanner.ScanWebSite;
import Serialize.FileToSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

/**
 * Created by dmitry on 17.05.17.
 */
public class MainFrame extends JFrame {

    public Panel panel;

    public MainFrame(LinkedHashMap<String, String> linkedHashMap) {
        panel = new Panel(linkedHashMap);
    }

    private final String title = "Job Scanner";
    private final Dimension d = new Dimension(800, 200);

    private JButton closeButton = new JButton("Close");
    private JLabel dev = new JLabel("Created by dmitryk86");
    private JLabel ref = new JLabel("Set time to refresh, min:");
    private JTextField text = new JTextField(5);
    private JButton setButton = new JButton("Set");
    private JLabel refNow = new JLabel("or click");
    private JButton refresh = new JButton("Refresh now");
    private JPanel downPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public void init(){
        setTitle(title);
        setSize(d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(MyPoint.getPoint(this));
        setLayout(new GridBagLayout());

        closeButton.addActionListener(new MyButtonListener());
        setButton.addActionListener(new SetButtonListener());
        refresh.addActionListener(new RefreshButtonListener());
        closeButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        dev.setFont(new Font(null, 0,8));
        ref.setForeground(Color.green);
        refNow.setForeground(Color.green);
        downPanel.add(ref);
        downPanel.add(text);
        downPanel.add(setButton);
        downPanel.add(refNow);
        downPanel.add(refresh);
        downPanel.add(closeButton);


        add(panel,  new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(1,1,1,1), 0,0));
        add(downPanel, new GridBagConstraints(0,1,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.NONE,new Insets(1,1,1,1), 0,0));
        add(dev, new GridBagConstraints(0,2,1,1,1,1,GridBagConstraints.EAST,GridBagConstraints.NONE,new Insets(1,1,1,1), 0,0));
        setVisible(true);
        pack();

    }
    public void refresh (LinkedHashMap<String, String> linkedHashMap){
        panel.refresh(linkedHashMap);
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
    }
    class MyButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            panel.f.save();
            System.exit(0);
        }
    }
    class SetButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String str = text.getText();
            if (str.isEmpty())
                return;
            text.setEditable(false);
            int time = Integer.parseInt(str);
            setButton.setEnabled(false);
            Loop loop = new Loop(time);
            loop.start();
        }
    }
    class RefreshButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            RefreshThread rt = new RefreshThread();
            rt.start();
        }
    }
}