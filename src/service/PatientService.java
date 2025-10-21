package service;
import data.DataStore;
import model.Patient;

import java.util.List;

public class PatientService {

    // Create new patient
    public String createPatient(String name, String gender, String place, String phone, String assignedDoctor) {
        String id = getNextPatientId();
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

    // Get next patient ID
    public String getNextPatientId() {
        return DataStore.getNextPatientId();
    }

    // Get all patients
    public List<Patient> getAllPatients() {
        return DataStore.loadPatients();
    }

    // Update prescription only
    public void updatePrescription(String id, String prescription) {
        DataStore.updatePatientPrescription(id, prescription);
    }

    // Update full patient info
    public void updatePatientInfo(Patient patient) {
        DataStore.updatePatient(patient);
    }
}