package ui;
import data.DataStore;
import javax.swing.*;

public class MainFrame extends JFrame {

    private final JTabbedPane tabs = new JTabbedPane();

    public MainFrame() {
        super("Hospital Patient Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize data files
        DataStore.initFiles();

        // Build Tabs
        tabs.addTab("Home", buildHomePanel());
        tabs.addTab("Receptionist", new LoginPanel(LoginPanel.UserType.RECEPTIONIST, this));
        tabs.addTab("Doctor", new LoginPanel(LoginPanel.UserType.DOCTOR, this));
        tabs.addTab("Patient", new LoginPanel(LoginPanel.UserType.PATIENT, this));

        add(tabs);
    }

    private JPanel buildHomePanel() {
        JPanel panel = new JPanel(new java.awt.BorderLayout());

        JLabel img = new JLabel(
                "<html><div style='text-align:center;font-size:20px;margin-top:80px'>[Hospital Image Here]</div></html>",
                SwingConstants.CENTER);
        JLabel quote = new JLabel(
                "<html><div style='text-align:center;padding:20px;font-size:16px'>Caring for life — professional, compassionate, reliable services</div></html>",
                SwingConstants.CENTER);

        panel.add(img, java.awt.BorderLayout.CENTER);
        panel.add(quote, java.awt.BorderLayout.SOUTH);
        return panel;
    }

    // Helper to switch panels from LoginPanel or other child panels
    public void switchContent(JPanel newPanel) {
        getContentPane().removeAll();
        add(newPanel);
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}