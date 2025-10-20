package ui;

import model.Patient;
import service.DataService;

import javax.swing.*;
import java.awt.*;

public class PatientDashboard extends JFrame {
    public PatientDashboard(String patientId) {
        setTitle("Patient - Medical History");
        setSize(500, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Patient p = DataService.findPatientById(patientId);
        JPanel panel = new JPanel(new GridLayout(0,1,6,6));
        panel.setBorder(BorderFactory.createEmptyBorder(12,12,12,12));

        if (p == null) {
            panel.add(new JLabel("Patient not found"));
        } else {
            panel.add(new JLabel("Patient ID: " + p.getId()));
            panel.add(new JLabel("Name: " + p.getName()));
            panel.add(new JLabel("Gender: " + p.getGender()));
            panel.add(new JLabel("Place: " + p.getPlace()));
            panel.add(new JLabel("Phone: " + p.getPhone()));
            panel.add(new JLabel("Assigned Doctor: " + p.getAssignedDoctor()));
            panel.add(new JLabel("Prescription: " + (p.getPrescription()==null? "": p.getPrescription())));
        }

        add(panel);
        setVisible(true);
    }
}
