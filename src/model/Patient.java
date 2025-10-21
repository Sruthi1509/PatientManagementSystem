package model;

public class Patient {

    private String id;
    private String name;
    private String gender;
    private String place;
    private String phone;
    private String assignedDoctor;
    private String prescription;

    // No-argument constructor
    public Patient() {}

    // All-argument constructor
    public Patient(String id, String name, String gender, String place,
                   String phone, String assignedDoctor, String prescription) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.place = place;
        this.phone = phone;
        this.assignedDoctor = assignedDoctor;
        this.prescription = prescription;
    }

    // --- Getters and Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPlace() { return place; }
    public void setPlace(String place) { this.place = place; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAssignedDoctor() { return assignedDoctor; }
    public void setAssignedDoctor(String assignedDoctor) { this.assignedDoctor = assignedDoctor; }

    public String getPrescription() { return prescription; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
}