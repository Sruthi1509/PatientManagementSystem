package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Hospital Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        // top banner with image and quote
        JPanel banner = new JPanel(new BorderLayout());
        banner.setPreferredSize(new Dimension(900,120));
        // image if exists
        try {
            ImageIcon icon = new ImageIcon("src/resources/images/hospital.jpg"); // put an image at this path if you want
            JLabel imgLabel = new JLabel(icon);
            banner.add(imgLabel, BorderLayout.WEST);
        } catch (Exception ignore) {}
        JLabel quote = new JLabel("<html><div style='padding:20px;font-size:16px'>Your Health, Our Priority</div></html>");
        banner.add(quote, BorderLayout.CENTER);

        // tabs for roles
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Receptionist", new RolePanel("Receptionist"));
        tabs.add("Doctor", new RolePanel("Doctor"));
        tabs.add("Patient", new RolePanel("Patient"));

        add(banner, BorderLayout.NORTH);
        add(tabs, BorderLayout.CENTER);

        setVisible(true);
    }

    // small panel with a button to open the login for the role
    static class RolePanel extends JPanel {
        public RolePanel(String role) {
            setLayout(new BorderLayout());
            JPanel center = new JPanel();
            center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
            center.setBorder(BorderFactory.createEmptyBorder(30,30,30,30));
            center.add(Box.createVerticalGlue());
            JButton open = new JButton("Go to " + role + " Login");
            open.setAlignmentX(Component.CENTER_ALIGNMENT);
            open.addActionListener(e -> {
                new LoginFrame(role.toLowerCase()); // open login for that role
            });
            center.add(open);
            center.add(Box.createVerticalGlue());
            add(center, BorderLayout.CENTER);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}

