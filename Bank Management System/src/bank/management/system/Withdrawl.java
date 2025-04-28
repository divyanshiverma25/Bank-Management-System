package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Withdrawl extends JFrame implements ActionListener {
    private String pin;
    private JTextField textField;
    private JButton withdrawButton, backButton;

    public Withdrawl(String pin) {
        this.pin = pin;

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1550, 830);
        add(background);

        // Labels for Withdrawal
        JLabel label1 = new JLabel("MAXIMUM WITHDRAWAL IS RS. 10,000");
        label1.setForeground(Color.BLACK);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 600, 35);
        background.add(label1);

        JLabel label2 = new JLabel("PLEASE ENTER YOUR AMOUNT");
        label2.setForeground(Color.BLACK);
        label2.setFont(new Font("System", Font.BOLD, 16));
        label2.setBounds(460, 220, 400, 35);
        background.add(label2);

        // Amount Input Field
        textField = new JTextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 260, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        background.add(textField);

        // Withdraw Button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(700, 362, 150, 35);
        withdrawButton.setBackground(new Color(65, 125, 128));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(this);
        background.add(withdrawButton);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        background.add(backButton);

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    // ActionListener implementation
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            String amount = textField.getText();
            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an amount to withdraw.");
                return;
            }

            int withdrawAmount;
            try {
                withdrawAmount = Integer.parseInt(amount);
                if (withdrawAmount > 10000) {
                    JOptionPane.showMessageDialog(this, "Maximum withdrawal limit is Rs. 10,000.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.");
                return;
            }

            // Database Operations
            try {
                Connn c = new Connn();
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
                int balance = 0;

                while (resultSet.next()) {
                    if (resultSet.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }

                if (balance < withdrawAmount) {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance.");
                    return;
                }

                // Get current date
                Date date = new Date();

                // Insert withdrawal transaction
                String query = "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawal', '" + withdrawAmount + "')";
                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Rs. " + withdrawAmount + " Debited Successfully.");

                setVisible(false);
                new main_Class(pin);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Transaction failed. Please try again.");
            }
        } else if (e.getSource() == backButton) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Withdrawl("");
    }
}
