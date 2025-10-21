package ui;

import model.Patient;
import service.PatientService;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class ReceptionistPanel extends JPanel {

    private final PatientService patientService = new PatientService();
    private final JFrame parentFrame;
    private JTextField nameField, genderField, placeField, phoneField;
    private JComboBox<String> doctorBox;
    private String currentPatientId;

    public ReceptionistPanel(String receptionistName, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        // --- Top panel with receptionist info and Back button ---
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome, " + receptionistName);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> goBack());
        topPanel.add(welcomeLabel, BorderLayout.WEST);
        topPanel.add(backButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // --- Form panel for patient registration ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        currentPatientId = patientService.getNextPatientId();

        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Patient ID:"), gbc);
        gbc.gridx = 1; formPanel.add(new JLabel(currentPatientId), gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; nameField = new JTextField(15); formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; genderField = new JTextField(10); formPanel.add(genderField, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(new JLabel("Place:"), gbc);
        gbc.gridx = 1; placeField = new JTextField(15); formPanel.add(placeField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; phoneField = new JTextField(12); formPanel.add(phoneField, gbc);

        gbc.gridx = 0; gbc.gridy = 5; formPanel.add(new JLabel("Doctor:"), gbc);
        gbc.gridx = 1; doctorBox = new JComboBox<>(new String[]{"Dr. Smith", "Dr. Jones", "Dr. Patel"});
        formPanel.add(doctorBox, gbc);

        gbc.gridx = 0; gbc.gridy = 6; JButton saveBtn = new JButton("Save"); formPanel.add(saveBtn, gbc);
        gbc.gridx = 1; JButton viewBtn = new JButton("View All Patients"); formPanel.add(viewBtn, gbc);

        add(formPanel, BorderLayout.CENTER);

        // --- Save new patient ---
        saveBtn.addActionListener(e -> savePatient());

        // --- View all patients ---
        viewBtn.addActionListener(e -> showAllPatients());
    }

    private void savePatient() {
        String name = nameField.getText().trim();
        String gender = genderField.getText().trim();
        String place = placeField.getText().trim();
        String phone = phoneField.getText().trim();
        String doctor = (String) doctorBox.getSelectedItem();

        if (name.isEmpty() || gender.isEmpty() || place.isEmpty() || phone.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create new patient
        patientService.createPatient(name, gender, place, phone, doctor);

        JOptionPane.showMessageDialog(this, "Patient saved with ID: " + currentPatientId);

        // Reset form for next patient
        currentPatientId = patientService.getNextPatientId();
        nameField.setText("");
        genderField.setText("");
        placeField.setText("");
        phoneField.setText("");
    }

    private void showAllPatients() {
        List<Patient> patients = patientService.getAllPatients();

        String[] columns = {"ID", "Name", "Gender", "Place", "Phone", "Doctor", "Prescription"};
        String[][] data = new String[patients.size()][7];

        for (int i = 0; i < patients.size(); i++) {
            Patient p = patients.get(i);
            data[i][0] = p.getId();
            data[i][1] = p.getName();
            data[i][2] = p.getGender();
            data[i][3] = p.getPlace();
            data[i][4] = p.getPhone();
            data[i][5] = p.getAssignedDoctor();
            data[i][6] = p.getPrescription();
        }

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);

        // Optional: edit selected patient
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JButton editBtn = new JButton("Edit Selected Patient");
        editBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String id = (String) table.getValueAt(row, 0);
                Optional<Patient> op = patients.stream().filter(p -> p.getId().equals(id)).findFirst();
                op.ifPresent(this::openEditPatientForm);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a patient to edit");
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(editBtn, BorderLayout.SOUTH);

        JFrame frame = new JFrame("All Patients");
        frame.add(panel);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void openEditPatientForm(Patient patient) {
        JFrame frame = new JFrame("Edit Patient: " + patient.getId());
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField nameF = new JTextField(patient.getName(), 15);
        JTextField genderF = new JTextField(patient.getGender(), 10);
        JTextField placeF = new JTextField(patient.getPlace(), 15);
        JTextField phoneF = new JTextField(patient.getPhone(), 12);
        JComboBox<String> doctorF = new JComboBox<>(new String[]{"Dr. Smith", "Dr. Jones", "Dr. Patel"});
        doctorF.setSelectedItem(patient.getAssignedDoctor());

        gbc.gridx = 0; gbc.gridy = 0; frame.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; frame.add(nameF, gbc);

        gbc.gridx = 0; gbc.gridy = 1; frame.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; frame.add(genderF, gbc);

        gbc.gridx = 0; gbc.gridy = 2; frame.add(new JLabel("Place:"), gbc);
        gbc.gridx = 1; frame.add(placeF, gbc);

        gbc.gridx = 0; gbc.gridy = 3; frame.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; frame.add(phoneF, gbc);

        gbc.gridx = 0; gbc.gridy = 4; frame.add(new JLabel("Doctor:"), gbc);
        gbc.gridx = 1; frame.add(doctorF, gbc);

        JButton saveBtn = new JButton("Save Changes");
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; frame.add(saveBtn, gbc);

        saveBtn.addActionListener(e -> {
            patient.setName(nameF.getText().trim());
            patient.setGender(genderF.getText().trim());
            patient.setPlace(placeF.getText().trim());
            patient.setPhone(phoneF.getText().trim());
            patient.setAssignedDoctor((String) doctorF.getSelectedItem());

            patientService.updatePatientInfo(patient);
            JOptionPane.showMessageDialog(frame, "Patient updated successfully");
            frame.dispose();
        });

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // --- FIXED: Go back to the main home screen ---
    private void goBack() {
        parentFrame.getContentPane().removeAll();
        parentFrame.add(new MainFrame().getContentPane()); // Go back to main home UI
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}
