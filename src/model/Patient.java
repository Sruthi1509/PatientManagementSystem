package model;

import java.io.Serializable;

public class Patient extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String medicalHistory;
    private String contactNumber;

    public Patient(int id, String name, String username, String password, String medicalHistory, String contactNumber) {
        super(id, name, username, password, Role.PATIENT);
        validateInput(medicalHistory, contactNumber);
        this.medicalHistory = medicalHistory;
        this.contactNumber = contactNumber;
    }

    private void validateInput(String medicalHistory, String contactNumber) {
        if (medicalHistory == null) {
            throw new IllegalArgumentException("Medical history cannot be null");
        }
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact number cannot be null or empty");
        }
    }

    // Getters and Setters
    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        validateInput(medicalHistory, this.contactNumber);
        this.medicalHistory = medicalHistory;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        validateInput(this.medicalHistory, contactNumber);
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", medicalHistory='" + medicalHistory + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}