package service;

import model.Patient;
import data.DataStore;

import java.util.List;
import java.util.Optional;

public class PatientService {

    /**
     * Generates the next patient ID based on existing data.
     * Example: "0001", "0002", etc.
     */
    public String getNextPatientId() {
        List<Patient> patients = DataStore.loadPatients();
        int nextId = patients.size() + 1;
        return String.format("%04d", nextId);
    }

    /**
     * Creates a new patient with a system-generated ID.
     * (Used for cases where the UI doesn't pass an ID)
     */
    public String createPatient(String name, String gender, String place, String phone, String assignedDoctor) {
        String id = getNextPatientId();
        return createPatientWithId(id, name, gender, place, phone, assignedDoctor);
    }

    /**
     * ✅ Creates a new patient with a provided (UI-generated) ID.
     * This ensures that the displayed and stored IDs match.
     */
    public String createPatientWithId(String id, String name, String gender, String place, String phone, String assignedDoctor) {
        Patient p = new Patient();
        p.setId(id);
        p.setName(name);
        p.setGender(gender);
        p.setPlace(place);
        p.setPhone(phone);
        p.setAssignedDoctor(assignedDoctor);
        p.setPrescription(""); // initially empty

        List<Patient> patients = DataStore.loadPatients();
        patients.add(p);
        DataStore.savePatients(patients);

        return id;
    }

    /**
     * Returns a list of all patients.
     */
    public List<Patient> getAllPatients() {
        return DataStore.loadPatients();
    }

    /**
     * Finds a patient by ID.
     */
    public Optional<Patient> findPatientById(String id) {
        List<Patient> patients = DataStore.loadPatients();
        return patients.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    /**
     * Updates the details of an existing patient.
     */
    public void updatePatientInfo(Patient updatedPatient) {
        List<Patient> patients = DataStore.loadPatients();
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(updatedPatient.getId())) {
                patients.set(i, updatedPatient);
                break;
            }
        }
        DataStore.savePatients(patients);
    }

    /**
     * Updates a patient's prescription.
     */
    public void updatePrescription(String patientId, String newPrescription) {
        List<Patient> patients = DataStore.loadPatients();
        for (Patient p : patients) {
            if (p.getId().equals(patientId)) {
                p.setPrescription(newPrescription);
                break;
            }
        }
        DataStore.savePatients(patients);
    }
}
