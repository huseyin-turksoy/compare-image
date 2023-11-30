package com.github.romankh3.usage.imagecomparison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFrameSample implements ActionListener {

    static JButton button;
    static JTextField textField1;
    static JTextField textField2;

    public String expectedURL;
    public String actualURL;


    public static void main(String[] args) {
        /*JFrame jFrame = new JFrame("GUI Test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new FlowLayout());
        jFrame.setSize(700,500);
        jFrame.add(new JLabel("Enter Expected Site URL "));
        jFrame.add(new JLabel("Enter Actual Site URL "));
        button = new JButton("Start Test");
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250,40));

        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(250,40));




        jFrame.add(textField1);
        jFrame.add(textField2);
        jFrame.add(button);
        jFrame.setVisible(true);*/
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
