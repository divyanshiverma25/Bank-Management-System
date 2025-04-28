package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Pin extends JFrame implements ActionListener {
    JButton submitButton, backButton;
    JPasswordField newPinField, confirmPinField;
    String pin;  // Store the current PIN

    Pin(String pin) {
        this.pin = pin; // Assign the user's PIN

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1550, 830);
        add(background);

        // Labels
        JLabel titleLabel = new JLabel("CHANGE YOUR PIN");
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("System", Font.BOLD, 18));
        titleLabel.setBounds(460, 180, 400, 35);
        background.add(titleLabel);

        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setForeground(Color.BLACK);
        newPinLabel.setFont(new Font("System", Font.BOLD, 16));
        newPinLabel.setBounds(460, 220, 150, 35);
        background.add(newPinLabel);

        newPinField = new JPasswordField();
        newPinField.setBackground(new Color(65, 125, 128));
        newPinField.setForeground(Color.WHITE);
        newPinField.setBounds(600, 220, 180, 25);
        newPinField.setFont(new Font("Raleway", Font.BOLD, 22));
        background.add(newPinField);

        JLabel confirmPinLabel = new JLabel("Re-enter New PIN:");
        confirmPinLabel.setForeground(Color.BLACK);
        confirmPinLabel.setFont(new Font("System", Font.BOLD, 16));
        confirmPinLabel.setBounds(460, 260, 200, 35);
        background.add(confirmPinLabel);

        confirmPinField = new JPasswordField();
        confirmPinField.setBackground(new Color(65, 125, 128));
        confirmPinField.setForeground(Color.WHITE);
        confirmPinField.setBounds(600, 260, 180, 25);
        confirmPinField.setFont(new Font("Raleway", Font.BOLD, 22));
        background.add(confirmPinField);

        // Buttons
        submitButton = new JButton("Change");
        submitButton.setBounds(600, 310, 150, 35);
        submitButton.setBackground(new Color(65, 125, 128));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(this);
        background.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(600, 360, 150, 35);
        backButton.setBackground(new Color(255, 0, 0));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        background.add(backButton);

        // Frame properties
        setLayout(null);
        setSize(1500, 900);
        setLocation(100, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String newPin = new String(newPinField.getPassword());
            String confirmPin = new String(confirmPinField.getPassword());

            if (!newPin.equals(confirmPin)) {
                JOptionPane.showMessageDialog(null, "PIN does not match. Try again!");
                return;
            }

            if (newPin.length() != 4 || !newPin.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "PIN should be exactly 4 digits!");
                return;
            }

            try {
                Connn c = new Connn();
                String query1 = "UPDATE bank SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
                String query2 = "UPDATE login SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";
                String query3 = "UPDATE signup3 SET pin = '" + newPin + "' WHERE pin = '" + pin + "'";

                c.statement.executeUpdate(query1);
                c.statement.executeUpdate(query2);
                c.statement.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN Changed Successfully!");

                setVisible(false);
                new main_Class(newPin);  // Redirect to main_Class after PIN change
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error updating PIN. Try again!");
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Pin(" ");  // Example PIN
    }
}
