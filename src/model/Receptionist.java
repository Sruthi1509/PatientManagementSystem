package model;

import java.io.Serializable;

public class Receptionist extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String contactNumber;
    private String email;

    public Receptionist(int id, String name, String username, String password, String contactNumber, String email) {
        super(id, name, username, password, Role.RECEPTIONIST);
        validateInput(contactNumber, email);
        this.contactNumber = contactNumber;
        this.email = email;
    }

    private void validateInput(String contactNumber, String email) {
        if (contactNumber == null || contactNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Contact number cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }

    // Getters and Setters
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        validateInput(contactNumber, this.email);
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        validateInput(this.contactNumber, email);
        this.email = email;
    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", username='" + getUsername() + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}