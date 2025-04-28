package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {
    String pin;
    JButton button;

    JLabel label1, label2, label3, label4;

    mini(String pin) {
        this.pin = pin;

        getContentPane().setBackground(new Color(255, 204, 204));
        setSize(400, 600);
        setLocation(20, 20);
        setLayout(null);

        label1 = new JLabel();
        label1.setBounds(20, 140, 400, 200); // increased height for transaction list
        label1.setVerticalAlignment(JLabel.TOP);
        add(label1);

        label2 = new JLabel("TechCoder A, V");
        label2.setFont(new Font("System", Font.BOLD, 15));
        label2.setBounds(150, 20, 200, 20);
        add(label2);

        label3 = new JLabel();
        label3.setBounds(20, 80, 300, 20);
        add(label3);

        label4 = new JLabel();
        label4.setBounds(20, 400, 300, 20);
        add(label4);

        try {
            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM login WHERE pin = '" + pin + "'");

            while (resultSet.next()) {
                String cardNumber = resultSet.getString("card_no");
                label3.setText("Card No:  " + cardNumber.substring(0, 4) + "XXXXXXXX" + cardNumber.substring(12));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            int balance = 0;
            StringBuilder sb = new StringBuilder("<html>");

            Connn c = new Connn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

            while (resultSet.next()) {
                sb.append(resultSet.getString("date"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(resultSet.getString("type"))
                        .append("&nbsp;&nbsp;&nbsp;&nbsp;")
                        .append(resultSet.getString("amount"))
                        .append("<br><br>");

                if (resultSet.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(resultSet.getString("amount"));
                } else {
                    balance -= Integer.parseInt(resultSet.getString("amount"));
                }
            }

            sb.append("</html>");
            label1.setText(sb.toString());
            label4.setText("Your Total Balance is Rs " + balance);
        } catch (Exception e) {
            e.printStackTrace();
        }

        button = new JButton("Exit");
        button.setBounds(20, 500, 100, 25);
        button.addActionListener(this);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        add(button);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini(" ");  // Pass a sample pin for testing
    }
}
