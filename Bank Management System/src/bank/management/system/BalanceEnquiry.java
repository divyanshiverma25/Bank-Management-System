package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BalanceEnquiry extends JFrame implements ActionListener {
    private String pin;
    private JLabel balanceLabel;
    private JButton backButton;

    public BalanceEnquiry(String pin) {
        this.pin = pin;

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 1550, 830);
        add(background);

        // Label for balance
        JLabel infoLabel = new JLabel("Your current balance is:");
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setFont(new Font("System", Font.BOLD, 16));
        infoLabel.setBounds(460, 180, 600, 35);
        background.add(infoLabel);

        balanceLabel = new JLabel();
        balanceLabel.setForeground(Color.BLACK);
        balanceLabel.setFont(new Font("System", Font.BOLD, 16));
        balanceLabel.setBounds(460, 220, 400, 35);
        background.add(balanceLabel);

        // Back Button
        backButton = new JButton("Back");
        backButton.setBounds(700, 406, 150, 35);
        backButton.setBackground(new Color(65, 125, 128));
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        background.add(backButton);

        // Fetch and display balance
        fetchBalance();

        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    private void fetchBalance() {
        int balance = 0;
        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");

            while (resultSet.next()) {
                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            balanceLabel.setText("Rs. " + balance);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving balance. Please try again.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("");
    }
}
