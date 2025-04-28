package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;

    FastCash(String pin) {
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1200, 600, Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel l3 = new JLabel(i3);
        l3.setBounds(150, 100, 1200, 600);

        JLabel label = new JLabel("PLEASE SELECT WITHDRAWAL AMOUNT");
        label.setBounds(330, 100, 500, 40);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 17));
        l3.add(label);

        // Creating buttons
        b1 = new JButton("Rs.100");
        b2 = new JButton("Rs.500");
        b3 = new JButton("Rs.1000");
        b4 = new JButton("Rs.2000");
        b5 = new JButton("Rs.5000");
        b6 = new JButton("Rs.10,000");
        b7 = new JButton("Back");

        // Setting button positions
        b1.setBounds(315, 195, 100, 30);
        b2.setBounds(520, 192, 140, 30);
        b3.setBounds(315, 235, 100, 30);
        b4.setBounds(520, 232, 140, 30);
        b5.setBounds(315, 275, 110, 30);
        b6.setBounds(520, 272, 140, 30);
        b7.setBounds(420, 305, 130, 30);

        // Button colors
        Color buttonColor = new Color(65, 125, 128);
        b1.setBackground(buttonColor);
        b2.setBackground(buttonColor);
        b3.setBackground(buttonColor);
        b4.setBackground(buttonColor);
        b5.setBackground(buttonColor);
        b6.setBackground(buttonColor);
        b7.setBackground(Color.RED);

        // Button foreground color
        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);
        b4.setForeground(Color.WHITE);
        b5.setForeground(Color.WHITE);
        b6.setForeground(Color.WHITE);
        b7.setForeground(Color.WHITE);

        // Adding action listeners
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);

        // Adding buttons to the layout
        l3.add(b1);
        l3.add(b2);
        l3.add(b3);
        l3.add(b4);
        l3.add(b5);
        l3.add(b6);
        l3.add(b7);

        // Setting frame properties
        setLayout(null);
        add(l3);
        setSize(1500, 900);
        setLocation(100, 50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b7) {
            setVisible(false);
            new main_Class(pin);
        } else {
            String amount = ((JButton) e.getSource()).getText().substring(3); // Fixed method call
            Connn c = new Connn();
            Date date = new Date();
            try {
                ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin='" + pin + "'");
                int balance = 0;
                while (resultSet.next()) {
                    if (resultSet.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(resultSet.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(resultSet.getString("amount"));
                    }
                }
                resultSet.close(); // Close ResultSet

                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                c.statement.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawal', '" + amount + "')");
                JOptionPane.showMessageDialog(null, "Rs." + amount + " Debited Successfully");

            } catch (Exception E) {
                E.printStackTrace();
            }
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
