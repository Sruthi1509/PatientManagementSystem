package ui;
import data.DataStore;
import model.Doctor;
import model.Patient;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorPanel extends JPanel {

    public DoctorPanel(Doctor doctor, JFrame parentFrame) {
        setLayout(new BorderLayout());

        // --- Header ---
        JLabel head = new JLabel("Doctor: " + doctor.getName(), SwingConstants.LEFT);
        head.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        head.setFont(new Font("Arial", Font.BOLD, 16));
        add(head, BorderLayout.NORTH);

        // --- Table of assigned patients ---
        List<Patient> allPatients = DataStore.loadPatients().stream()
                .filter(p -> p.getAssignedDoctor().equals(doctor.getName()))
                .collect(Collectors.toList());

        String[] columns = {"ID", "Name", "Gender", "Place", "Phone", "Prescription"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        for (Patient p : allPatients) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getGender(),
                    p.getPlace(),
                    p.getPhone(),
                    p.getPrescription()
            });
        }

        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- Buttons panel ---
        JPanel bottom = new JPanel();
        JButton saveBtn = new JButton("Save Prescriptions");
        JButton backBtn = new JButton("Back");
        bottom.add(saveBtn);
        bottom.add(backBtn);
        add(bottom, BorderLayout.SOUTH);

        // Save updated prescriptions
        saveBtn.addActionListener(e -> {
            for (int i = 0; i < model.getRowCount(); i++) {
                String id = (String) model.getValueAt(i, 0);
                String prescription = (String) model.getValueAt(i, 5);
                DataStore.updatePatientPrescription(id, prescription);
            }
            JOptionPane.showMessageDialog(this, "Prescriptions saved successfully!");
        });

        // Back button action
        backBtn.addActionListener(e -> {
            parentFrame.getContentPane().removeAll();
            parentFrame.add(new MainFrame().getContentPane());
            parentFrame.revalidate();
            parentFrame.repaint();
        });
    }
}