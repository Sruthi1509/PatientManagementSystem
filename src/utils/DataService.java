package com.patientmanagement.utils;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    private static final String BASE_PATH = "data/";
    private static final String RECEPTIONIST_FILE = BASE_PATH + "receptionists.txt";
    private static final String DOCTOR_FILE = BASE_PATH + "doctors.txt";
    private static final String PATIENT_FILE = BASE_PATH + "patients.txt";
    private static final String PRESCRIPTION_FILE = BASE_PATH + "prescriptions.txt";

    // Make sure to call this once at app start to ensure data folder exists
    public static void initialize() {
        java.io.File dir = new java.io.File(BASE_PATH);
        if (!dir.exists()) dir.mkdirs();
    }

    public static List<String> getReceptionists() {
        return FileManager.readFile(RECEPTIONIST_FILE);
    }

    public static List<String> getDoctors() {
        return FileManager.readFile(DOCTOR_FILE);
    }

    public static List<String> getPatients() {
        return FileManager.readFile(PATIENT_FILE);
    }

    public static void addPatient(String patientLine) {
        FileManager.appendLine(PATIENT_FILE, patientLine);
    }

    public static void addPrescription(String prescriptionLine) {
        FileManager.appendLine(PRESCRIPTION_FILE, prescriptionLine);
    }

    // For future: methods to search or update a record
    public static List<String> getPrescriptions() {
        return FileManager.readFile(PRESCRIPTION_FILE);
    }
}
