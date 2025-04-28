package bank.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {
    JRadioButton r1, r2, r3, r4;
    JCheckBox c1, c2, c3, c4, c5, c6;
    JButton s, c;
    String form_no;

    Signup3(String form_no) {
        this.form_no = form_no;
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25, 10, 100, 100);
        add(image);

        JLabel l1 = new JLabel("Page 3:");
        l1.setFont(new Font("Raleway", Font.BOLD, 22));
        l1.setBounds(280, 40, 400, 40);
        add(l1);

        JLabel l2 = new JLabel("Account Details:");
        l2.setFont(new Font("Raleway", Font.BOLD, 22));
        l2.setBounds(280, 70, 400, 40);
        add(l2);

        JLabel l3 = new JLabel("Account Type:");
        l3.setFont(new Font("Raleway", Font.BOLD, 18));
        l3.setBounds(100, 140, 200, 30);
        add(l3);

        r1 = new JRadioButton("Saving Account");
        r1.setFont(new Font("Raleway", Font.BOLD, 16));
        r1.setBackground(new Color(215, 252, 252));
        r1.setBounds(100, 180, 150, 30);
        add(r1);

        r2 = new JRadioButton("Fixed Deposit Account");
        r2.setFont(new Font("Raleway", Font.BOLD, 16));
        r2.setBackground(new Color(215, 252, 252));
        r2.setBounds(350, 180, 300, 30);
        add(r2);

        r3 = new JRadioButton("Current Account");
        r3.setFont(new Font("Raleway", Font.BOLD, 16));
        r3.setBackground(new Color(215, 252, 252));
        r3.setBounds(100, 220, 250, 30);
        add(r3);

        r4 = new JRadioButton("Recurring Deposit Account");
        r4.setFont(new Font("Raleway", Font.BOLD, 16));
        r4.setBackground(new Color(215, 252, 252));
        r4.setBounds(350, 220, 250, 30);
        add(r4);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);
        buttonGroup.add(r3);
        buttonGroup.add(r4);

        JLabel l4 = new JLabel("Card No:");
        l4.setFont(new Font("Raleway", Font.BOLD, 18));
        l4.setBounds(100, 280, 200, 30);
        add(l4);

        JLabel l5 = new JLabel("(Your 16 digit Card Number)");
        l5.setFont(new Font("Raleway", Font.BOLD, 12));
        l5.setBounds(100, 300, 250, 30);
        add(l5);

        JLabel l6 = new JLabel("XXXX-XXXX-XXXX-4841");
        l6.setFont(new Font("Raleway", Font.BOLD, 18));
        l6.setBounds(330, 280, 250, 30);
        add(l6);

        JLabel l8 = new JLabel("PIN:");
        l8.setFont(new Font("Raleway", Font.BOLD, 18));
        l8.setBounds(100, 330, 200, 30);
        add(l8);

        JLabel l9 = new JLabel("XXXX");
        l9.setFont(new Font("Raleway", Font.BOLD, 18));
        l9.setBounds(330, 330, 200, 30);
        add(l9);

        JLabel l11 = new JLabel("Services Required:");
        l11.setFont(new Font("Raleway", Font.BOLD, 18));
        l11.setBounds(100, 400, 200, 30);
        add(l11);

        c1 = new JCheckBox("ATM CARD");
        c2 = new JCheckBox("Internet Banking");
        c3 = new JCheckBox("Mobile Banking");
        c4 = new JCheckBox("Email Alerts");
        c5 = new JCheckBox("Cheque Book");
        c6 = new JCheckBox("E-Statement");

        JCheckBox[] services = {c1, c2, c3, c4, c5, c6};
        int y = 450;
        for (JCheckBox service : services) {
            service.setBackground(new Color(215, 252, 252));
            service.setFont(new Font("Raleway", Font.BOLD, 16));
            service.setBounds(100, y, 200, 30);
            add(service);
            y += 50;
        }

        s = new JButton("Submit");
        s.setFont(new Font("Raleway", Font.BOLD, 14));
        s.setBackground(Color.BLACK);
        s.setForeground(Color.WHITE);
        s.setBounds(250, 660, 100, 30);
        s.addActionListener(this);
        add(s);

        c = new JButton("Cancel");
        c.setFont(new Font("Raleway", Font.BOLD, 14));
        c.setBackground(Color.BLACK);
        c.setForeground(Color.WHITE);
        c.setBounds(420, 660, 100, 30);
        c.addActionListener(this);
        add(c);

        setSize(850, 750);
        setLocation(450, 80);
        getContentPane().setBackground(new Color(215, 252, 252));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String atype = null;
        if (r1.isSelected()) atype = "Saving Account";
        else if (r2.isSelected()) atype = "Fixed Deposit Account";
        else if (r3.isSelected()) atype = "Current Account";
        else if (r4.isSelected()) atype = "Recurring Deposit Account";

        Random ran = new Random();
        long first7 = (Math.abs(ran.nextLong()) % 9000000000000000L) + 1000000000000000L;
        String cardno = "" + first7;
        long first3 = (Math.abs(ran.nextLong()) % 9000L) + 1000L;
        String pin = "" + first3;

        String fac = "";
        for (JCheckBox service : new JCheckBox[]{c1, c2, c3, c4, c5, c6}) {
            if (service.isSelected()) {
                fac += service.getText() + ", ";
            }
        }

        try {
            if (e.getSource() == s) {
                if (atype == null) {
                    JOptionPane.showMessageDialog(null, "Fill all the fields");
                } else {
                    Connn c1 = new Connn();
                    String q1 = "INSERT INTO signup3 VALUES('" + form_no + "','" + atype + "','" + cardno + "','" + pin + "','" + fac + "')";
                    String q2 = "INSERT INTO login VALUES('" + form_no + "','" + cardno + "','" + pin + "')";
                    c1.statement.executeUpdate(q1);
                    c1.statement.executeUpdate(q2);
                    JOptionPane.showMessageDialog(null, "Card Number: " + cardno + "\nPIN: " + pin);
                   new Deposit(pin);
                    setVisible(false);
                }
            } else if (e.getSource() == c) {
                System.exit(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup3("");
    }
}
