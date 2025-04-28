package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main_Class extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    // Constructor
    main_Class(String pin) {
        this.pin = pin;

        // Load image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH); // Adjusted image size
        ImageIcon i3 = new ImageIcon(i2);

        JLabel l3 = new JLabel(i3);
        l3.setBounds(150, 100, 1200, 600); // Centered image in frame

        // Add label inside the image (Upper Left Position)
        JLabel label = new JLabel("Please Select Your Transaction");
        label.setBounds(320, 100, 500, 40); // Moved UP and towards LEFT
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 22)); // Adjusted font size
        l3.add(label); // Adding label to the image

        // Buttons
        b1 = new JButton("DEPOSIT");
        b1.setBounds(315, 195, 100, 30);
        b1.setForeground(Color.WHITE);
        b1.setBackground(new Color(65, 125, 128));
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("CASH WITHDRAW");
        b2.setBounds(520, 192, 140, 30);
        b2.setForeground(Color.WHITE);
        b2.setBackground(new Color(65, 125, 128));
        b2.addActionListener(this);
        l3.add(b2);

        b3 = new JButton("FAST CASH");
        b3.setBounds(315, 235, 100, 30);
        b3.setForeground(Color.WHITE);
        b3.setBackground(new Color(65, 125, 128));
        b3.addActionListener(this);
        l3.add(b3);

        b4 = new JButton("MINI STATEMENT");
        b4.setBounds(520, 232, 140, 30);
        b4.setForeground(Color.WHITE);
        b4.setBackground(new Color(65, 125, 128));
        b4.addActionListener(this);
        l3.add(b4);

        b5 = new JButton("PIN CHANGE");
        b5.setBounds(315, 275, 110, 30);
        b5.setForeground(Color.WHITE);
        b5.setBackground(new Color(65, 125, 128));
        b5.addActionListener(this);
        l3.add(b5);

        b6 = new JButton("BALANCE ENQUIRY");
        b6.setBounds(520, 272, 140, 30);
        b6.setForeground(Color.WHITE);
        b6.setBackground(new Color(65, 125, 128));
        b6.addActionListener(this);
        l3.add(b6);

        b7 = new JButton("EXIT");
        b7.setBounds(420, 305, 130, 30);
        b7.setForeground(Color.WHITE);
        b7.setBackground(new Color(255, 0, 0)); // Red for Exit
        b7.addActionListener(this);
        l3.add(b7);

        // Frame properties
        setLayout(null);
        add(l3); // Add the image label AFTER setting layout

        setSize(1500, 900); // Adjusted frame size
        setLocation(100, 50); // Centered positioning
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            new Deposit(pin); // Ensure Deposit class exists
            setVisible(false);
        } else if (e.getSource() == b7) {
            System.exit(0); // Closes the application
        }else if(e.getSource()==b2){
            new Withdrawl(pin);
            setVisible(false);
        }else if(e.getSource()==b6){
            new BalanceEnquiry(pin);
            setVisible(false);
        }else if(e.getSource()==b3){
            new FastCash(pin);
            setVisible(false);
        }else if(e.getSource()==b5){
            new Pin(pin);
            setVisible(false);
        }else if(e.getSource()==b4){
            new mini(pin);
        }
    }

    // Main method
    public static void main(String[] args) {
        new main_Class(""); // Empty pin for now
    }
}
