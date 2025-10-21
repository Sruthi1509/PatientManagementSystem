package model;
public class Doctor {
    private String name;
    private String password;
    public Doctor(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() { return name; }
    public String getPassword() { return password; }
    @Override public String toString() { return name; }
}