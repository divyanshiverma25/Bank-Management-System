package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {
    private JTextField cardField;
    private JPasswordField pinField;

    public Login() {
        // Frame properties
        setTitle("Bank Management System - Login");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background Image
        ImageIcon bgIcon = new ImageIcon(ClassLoader.getSystemResource("icons/backbg.png"));
        Image bgImg = bgIcon.getImage().getScaledInstance(800, 500, Image.SCALE_DEFAULT);
        ImageIcon bgFinal = new ImageIcon(bgImg);
        JLabel background = new JLabel(bgFinal);
        background.setBounds(0, 0, 800, 500);
        add(background);

        // Bank Logo (Top-Center)
        ImageIcon bankIcon = new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image bankImg = bankIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon bankFinal = new ImageIcon(bankImg);
        JLabel bankLogo = new JLabel(bankFinal);
        bankLogo.setBounds(350, 10, 100, 100);
        background.add(bankLogo);

        // Card Image (Bottom-Right)
        ImageIcon cardIcon = new ImageIcon(ClassLoader.getSystemResource("icons/card.png"));
        Image cardImg = cardIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon cardFinal = new ImageIcon(cardImg);
        JLabel cardLogo = new JLabel(cardFinal);
        cardLogo.setBounds(630, 350, 100, 100);
        background.add(cardLogo);

        // Welcome Text
        JLabel heading = new JLabel("WELCOME TO ATM");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setForeground(Color.WHITE);
        heading.setBounds(280, 120, 300, 40);
        background.add(heading);

        // Card Number Label & Field
        JLabel cardLabel = new JLabel("Card No:");
        cardLabel.setFont(new Font("Arial", Font.BOLD, 14));
        cardLabel.setForeground(Color.WHITE);
        cardLabel.setBounds(250, 180, 100, 30);
        background.add(cardLabel);

        cardField = new JTextField();
        cardField.setBounds(350, 180, 200, 30);
        background.add(cardField);

        // PIN Label & Field
        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Arial", Font.BOLD, 14));
        pinLabel.setForeground(Color.WHITE);
        pinLabel.setBounds(250, 230, 100, 30);
        background.add(pinLabel);

        pinField = new JPasswordField();
        pinField.setBounds(350, 230, 200, 30);
        background.add(pinField);

        // Buttons
        JButton signInButton = new JButton("Sign In");
        signInButton.setBounds(250, 300, 100, 30);
        background.add(signInButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(370, 300, 100, 30);
        background.add(signUpButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(490, 300, 100, 30);
        background.add(clearButton);

        // Button Actions
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verifyLogin();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Close Login Page
                new Signup();  // Open SignUp Page (Assuming Signup class exists)
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardField.setText("");
                pinField.setText("");
            }
        });

        setVisible(true);
    }

    // Database Connection & Login Verification
    private void verifyLogin() {
        String cardNo = cardField.getText();
        String pin = new String(pinField.getPassword());

        if (cardNo.isEmpty() || pin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Card No and PIN.");
            return;
        }

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/banksystem", "root", "your_password");
            String query = "SELECT * FROM login WHERE card_no = ? AND Pin = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, cardNo);
            pst.setString(2, pin);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new main_Class(pin); // Redirecting to main class
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials! Please try again.");
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Connection Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
