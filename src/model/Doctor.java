package model;

import java.io.Serializable;

public class Doctor extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String doctorId; // Unique ID for doctor (e.g., D001)
    private String specialization; // e.g., Cardiologist, Dermatologist

    public Doctor(String doctorId, String name, String specialization, String username, String password) {
        super(Integer.parseInt(doctorId.replace("D", "")), name, username, password, Role.DOCTOR);
        validateInput(doctorId, specialization);
        this.doctorId = doctorId;
        this.specialization = specialization;
    }

    private void validateInput(String doctorId, String specialization) {
        if (doctorId == null || doctorId.trim().isEmpty()) {
            throw new IllegalArgumentException("Doctor ID cannot be null or empty");
        }
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty");
        }
    }

    // Getters and Setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        validateInput(doctorId, this.specialization);
        this.doctorId = doctorId;
        setId(Integer.parseInt(doctorId.replace("D", ""))); // Sync with User ID
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        validateInput(this.doctorId, specialization);
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", name='" + getName() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", username='" + getUsername() + '\'' +
                '}';
    }
}