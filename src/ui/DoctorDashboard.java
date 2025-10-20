package ui;

import model.Patient;
import service.DataService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DoctorDashboard extends JFrame {
    private String doctorUsername;
    private DefaultTableModel tableModel;

    public DoctorDashboard(String doctorUsername) {
        this.doctorUsername = doctorUsername;
        setTitle("Doctor Dashboard - " + doctorUsername);
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tableModel = new DefaultTableModel(new Object[]{"ID","Name","Gender","Place","Phone","Prescription"},0);
        JTable table = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(table);

        JButton refresh = new JButton("Refresh");
        JButton savePres = new JButton("Save Prescription");

        JPanel bottom = new JPanel();
        bottom.add(refresh);
        bottom.add(savePres);

        refresh.addActionListener(e -> reloadTable());
        savePres.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) { JOptionPane.showMessageDialog(this,"Select a row"); return; }
            String id = (String)tableModel.getValueAt(row, 0);
            String pres = (String)tableModel.getValueAt(row, 5);
            Patient p = DataService.findPatientById(id);
            if (p != null) {
                p.setPrescription(pres);
                DataService.saveAll();
                JOptionPane.showMessageDialog(this,"Prescription saved for " + id);
            }
        });

        add(sp, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        reloadTable();
        setVisible(true);
    }

    private void reloadTable() {
        tableModel.setRowCount(0);
        List<Patient> list = DataService.patientsForDoctor(doctorUsername);
        for (Patient p : list) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getGender(), p.getPlace(), p.getPhone(), p.getPrescription()});
        }
    }
}
