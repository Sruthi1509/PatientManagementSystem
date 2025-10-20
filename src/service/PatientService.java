package service;

import java.util.ArrayList;

public class PatientService {
    private ArrayList<String> patients = new ArrayList<>();

    public void addPatient(String name) {
        patients.add(name);
        System.out.println("Patient added: " + name);
    }

    public void listPatients() {
        System.out.println("Patient List:");
        for (String name : patients) {
            System.out.println(name);
        }
    }
}
