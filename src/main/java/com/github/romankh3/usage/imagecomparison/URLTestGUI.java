package com.github.romankh3.usage.imagecomparison;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class URLTestGUI  implements ActionListener {
    private JTextField textField1, textField2;
    private JButton button;

    String url1;
    String url2;

    public URLTestGUI() {
        JFrame jFrame = new JFrame("URL Test");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel with GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Label: Enter Expected Site URL
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Enter Expected Site URL"), gbc);

        // Text Field for Expected Site URL
        textField1 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        mainPanel.add(textField1, gbc);

        // Label: Enter Actual Site URL
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Enter Actual Site URL"), gbc);

        // Text Field for Actual Site URL
        textField2 = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(textField2, gbc);

        // Button: Start Test
        button = new JButton("Start Test");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(button, gbc);
        button.addActionListener(this);

        jFrame.add(mainPanel);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(URLTestGUI::new);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==button){
            url1 = textField1.getText().trim().toLowerCase();
            url2 = textField2.getText().trim().toLowerCase();

            System.out.println("url1 = " + url1);
            System.out.println("url2 = " + url2);

        }

    }
}
