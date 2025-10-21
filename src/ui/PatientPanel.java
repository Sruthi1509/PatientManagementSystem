
package ui;

import model.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    public PatientPanel(Patient patient, JFrame parentFrame) {
        setLayout(new BorderLayout());

        // --- Header ---
        JLabel head = new JLabel("Patient: " + patient.getName(), SwingConstants.CENTER);
        head.setFont(new Font("Arial", Font.BOLD, 16));
        add(head, BorderLayout.NORTH);

        // --- Patient info area ---
        JTextArea info = new JTextArea();
        info.setEditable(false);
        info.setText(String.format("""
                ID: %s
                Name: %s
                Gender: %s
                Place: %s
                Phone: %s
                Assigned Doctor: %s
                Prescription: %s
                """,
                patient.getId(),
                patient.getName(),
                patient.getGender(),
                patient.getPlace(),
                patient.getPhone(),
                patient.getAssignedDoctor(),
                patient.getPrescription()));

        add(new JScrollPane(info), BorderLayout.CENTER);

        // --- Back button ---
        JPanel bottom = new JPanel();
        JButton backBtn = new JButton("Back");
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);

        backBtn.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.add(new MainFrame().getContentPane());
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}