package data;

import model.Doctor;
import model.Patient;
import model.Receptionist;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataStore {

    private static final String RECEPTIONIST_FILE = "receptionists.txt";
    private static final String DOCTOR_FILE = "doctors.txt";
    private static final String PATIENT_FILE = "patients.txt";
    private static final String NEXT_ID_FILE = "next_id.txt";

    // Initialize files with dummy data
    public static void initFiles() {
        try {
            File f;

            f = new File(RECEPTIONIST_FILE);
            if (!f.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
                    writer.write("alice,alicepass\nbob,bobpass\n");
                }
            }

            f = new File(DOCTOR_FILE);
            if (!f.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
                    writer.write("Dr. Smith,docpass1\nDr. Jones,docpass2\nDr. Patel,docpass3\n");
                }
            }

            f = new File(PATIENT_FILE);
            if (!f.exists()) {
                f.createNewFile();
            }

            f = new File(NEXT_ID_FILE);
            if (!f.exists()) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
                    writer.write("1"); // Start from 0001
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load receptionists
    public static List<Receptionist> loadReceptionists() {
        List<Receptionist> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(RECEPTIONIST_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2)
                    list.add(new Receptionist(parts[0], parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Load doctors
    public static List<Doctor> loadDoctors() {
        List<Doctor> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2)
                    list.add(new Doctor(parts[0], parts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Load patients
    public static List<Patient> loadPatients() {
        List<Patient> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    Patient p = new Patient();
                    p.setId(parts[0]);
                    p.setName(parts[1]);
                    p.setGender(parts[2]);
                    p.setPlace(parts[3]);
                    p.setPhone(parts[4]);
                    p.setAssignedDoctor(parts[5]);
                    p.setPrescription(parts.length >= 7 ? parts[6] : "");
                    list.add(p);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get next patient ID (fixed)
    public synchronized static String getNextPatientId() {
        int nextId = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(NEXT_ID_FILE))) {
            String line = reader.readLine();
            if (line != null && !line.isEmpty()) {
                nextId = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        String idToReturn = String.format("%04d", nextId);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NEXT_ID_FILE))) {
            writer.write(String.valueOf(nextId + 1));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return idToReturn;
    }

    // Update patient prescription
    public static void updatePatientPrescription(String id, String prescription) {
        List<Patient> patients = loadPatients();

        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                p.setPrescription(prescription);
                break;
            }
        }

        savePatients(patients);
    }

    // Save a list of patients to file
    public static void savePatients(List<Patient> patients) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PATIENT_FILE))) {
            for (Patient p : patients) {
                writer.write(String.join(",",
                        p.getId(),
                        p.getName(),
                        p.getGender(),
                        p.getPlace(),
                        p.getPhone(),
                        p.getAssignedDoctor(),
                        p.getPrescription() != null ? p.getPrescription() : ""));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Update full patient info
    public static void updatePatient(Patient updatedPatient) {
        List<Patient> patients = loadPatients();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(updatedPatient.getId())) {
                patients.set(i, updatedPatient);
                break;
            }
        }
        savePatients(patients);
    }
}
