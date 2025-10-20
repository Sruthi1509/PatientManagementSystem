package ui;

import service.DataService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String role;

    public LoginFrame(String role) {
        this.role = role;
        setTitle("Hospital Management System - Login");
        setSize(450, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Role:"), gbc);
        gbc.gridx = 1;
        panel.add(new JLabel(role.substring(0,1).toUpperCase() + role.substring(1)), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(18);
        panel.add(usernameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(18);
        panel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        JButton loginBtn = new JButton("Login");
        panel.add(loginBtn, gbc);

        loginBtn.addActionListener(e -> attemptLogin());

        add(panel);
        setVisible(true);
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        switch (role) {
            case "receptionist":
                if (DataService.authenticateReceptionist(username, password)) {
                    JOptionPane.showMessageDialog(this, "Receptionist login successful");
                    new ui.ReceptionistDashboard(); // open receptionist UI
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid receptionist credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "doctor":
                if (DataService.authenticateDoctor(username, password)) {
                    JOptionPane.showMessageDialog(this, "Doctor login successful");
                    new ui.DoctorDashboard(username); // pass doctor username
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid doctor credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "patient":
                String id = DataService.authenticatePatient(username, password);
                if (id != null) {
                    JOptionPane.showMessageDialog(this, "Patient login successful");
                    new ui.PatientDashboard(id);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid patient credentials", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Unknown role", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
