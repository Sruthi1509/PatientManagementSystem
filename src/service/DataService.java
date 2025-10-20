package service;

import model.Patient;
import java.util.ArrayList;
import java.util.List;

public class DataService {

    private static List<Patient> patients = new ArrayList<>();

    public static void addPatient(Patient patient) {
        patients.add(patient);
    }

    public static Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // add getters for other data later if needed (like doctors, receptionist)
}

