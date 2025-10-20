package ui;
import javax.swing.*;

public class ReceptionistDashboard extends JFrame {
    public ReceptionistDashboard() {
        setTitle("Receptionist Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new JLabel("Welcome, Receptionist!", SwingConstants.CENTER));
        setVisible(true);
    }
}

