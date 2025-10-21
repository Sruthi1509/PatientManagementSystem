package ui;

import model.Doctor;
import model.Patient;
import service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginPanel extends JPanel {

    public enum UserType { RECEPTIONIST, DOCTOR, PATIENT }

    private final UserType userType;
    private final AuthService auth = new AuthService();

    public LoginPanel(UserType type, JFrame parentFrame) {
        this.userType = type;
        setLayout(new GridBagLayout());
        init(parentFrame);
    }

    private void init(JFrame parentFrame) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6,6,6,6);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel l1 = new JLabel(), l2 = new JLabel();
        JTextField t1 = new JTextField(18);
        JPasswordField p1 = new JPasswordField(18);
        JButton loginBtn = new JButton("Login");

        switch (userType) {
            case RECEPTIONIST -> { l1.setText("Receptionist Name:"); l2.setText("Password:"); }
            case DOCTOR -> { l1.setText("Doctor Name:"); l2.setText("Password:"); }
            case PATIENT -> { l1.setText("Patient ID:"); l2.setText("Patient Name:"); }
        }

        gbc.gridx=0; gbc.gridy=0; add(l1,gbc);
        gbc.gridx=1; add(t1,gbc);
        gbc.gridx=0; gbc.gridy=1; add(l2,gbc);
        gbc.gridx=1; add(p1,gbc);
        gbc.gridy=2; add(loginBtn,gbc);

        loginBtn.addActionListener(e -> {
            String a = t1.getText().trim();
            String b = new String(p1.getPassword()).trim();

            switch (userType) {
                case RECEPTIONIST -> {
                    if (auth.receptionistLogin(a,b))
                        switchPanel(parentFrame, new ReceptionistPanel(a, parentFrame));
                    else
                        showError("Invalid receptionist login");
                }
                case DOCTOR -> {
                    Optional<Doctor> od = auth.doctorLogin(a,b);
                    if (od.isPresent())
                        switchPanel(parentFrame, new DoctorPanel(od.get(), parentFrame));
                    else
                        showError("Invalid doctor login");
                }
                case PATIENT -> {
                    Optional<Patient> op = auth.patientLogin(a,b);
                    if (op.isPresent())
                        switchPanel(parentFrame, new PatientPanel(op.get(), parentFrame));
                    else
                        showError("Invalid patient credentials");
                }
            }
        });
    }

    private void switchPanel(JFrame frame, JPanel panel) {
        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }

    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Login Failed", JOptionPane.ERROR_MESSAGE);
    }
}