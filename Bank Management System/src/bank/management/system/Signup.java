package bank.management.system;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {
    long formNumber;
    JTextField nameField, fnameField, emailField, addressField, cityField, pinField, stateField;
    JRadioButton male, female, married, unmarried, other;
    JButton next;
    JDateChooser dateChooser;

    Signup() {
        setLayout(null);
        getContentPane().setBackground(new Color(222, 255, 228));

        Random rand = new Random();
        formNumber = Math.abs((rand.nextLong() % 9000L) + 1000L);

        JLabel formLabel = new JLabel("APPLICATION FORM NO. " + formNumber);
        formLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        formLabel.setBounds(160, 20, 600, 40);
        add(formLabel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/bank.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(25, 10, 100, 100);
        add(image);

        JLabel label2 = new JLabel("Page 1 - Personal Details");
        label2.setFont(new Font("Raleway", Font.BOLD, 22));
        label2.setBounds(250, 70, 400, 30);
        add(label2);

        addLabel("Name:", 100);
        nameField = addTextField(100);

        addLabel("Father's Name:", 150);
        fnameField = addTextField(150);

        addLabel("Date of Birth:", 200);
        dateChooser = new JDateChooser();
        dateChooser.setBounds(250, 200, 300, 30);
        add(dateChooser);

        addLabel("Gender:", 250);
        male = addRadioButton("Male", 250, 250);
        female = addRadioButton("Female", 350, 250);
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        addLabel("Email:", 300);
        emailField = addTextField(300);

        addLabel("Marital Status:", 350);
        married = addRadioButton("Married", 250, 350);
        unmarried = addRadioButton("Unmarried", 350, 350);
        other = addRadioButton("Other", 450, 350);
        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

        addLabel("Address:", 400);
        addressField = addTextField(400);

        addLabel("City:", 450);
        cityField = addTextField(450);

        addLabel("Pin Code:", 500);
        pinField = addTextField(500);

        addLabel("State:", 550);
        stateField = addTextField(550);

        next = new JButton("Next");
        next.setFont(new Font("Raleway", Font.BOLD, 16));
        next.setBounds(300, 600, 100, 30);
        next.addActionListener(this);
        add(next);

        setSize(700, 700);
        setLocation(400, 100);
        setVisible(true);
    }

    private void addLabel(String text, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(100, y, 150, 30);
        add(label);
    }

    private JTextField addTextField(int y) {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Raleway", Font.BOLD, 14));
        textField.setBounds(250, y, 300, 30);
        add(textField);
        return textField;
    }

    private JRadioButton addRadioButton(String text, int x, int y) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Raleway", Font.BOLD, 14));
        radioButton.setBounds(x, y, 100, 30);
        radioButton.setOpaque(false); // Makes the radio button blend with the background
        add(radioButton);
        return radioButton;
    }

    public void actionPerformed(ActionEvent ae) {
        String form_no = String.valueOf(formNumber);  // Fixing form number
        String name = nameField.getText();
        String father_name = fnameField.getText();
        String DOB = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = male.isSelected() ? "Male" : female.isSelected() ? "Female" : "";
        String email = emailField.getText();
        String marital_status = married.isSelected() ? "Married" : unmarried.isSelected() ? "Unmarried" : other.isSelected() ? "Other" : "";
        String address = addressField.getText();
        String city = cityField.getText();
        String pincode = pinField.getText();
        String state = stateField.getText();

        try {
            if (name.isEmpty() || father_name.isEmpty() || DOB.isEmpty() || gender.isEmpty() || email.isEmpty() || marital_status.isEmpty() || address.isEmpty() || city.isEmpty() || pincode.isEmpty() || state.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required");
            } else {
                // Establish database connection (ensure Connn.java is correctly implemented)
                Connn con1 = new Connn();

                // SQL Query to insert data
                String query = "INSERT INTO signup (form_no, name, father_name, DOB, gender, email, marital_status, address, city, pincode, state) VALUES ('"
                        + form_no + "', '" + name + "', '" + father_name + "', '" + DOB + "', '" + gender + "', '" + email + "', '"
                        + marital_status + "', '" + address + "', '" + city + "', '" + pincode + "', '" + state + "')";

                // Execute the query
                con1.statement.executeUpdate(query);

                // Show confirmation message
                JOptionPane.showMessageDialog(null, "Form submitted successfully!");

                // Move to next page
                new Signup2(form_no);
                setVisible(false);
            }
        } catch(Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error while saving data");
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
