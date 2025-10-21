package model;
public class Receptionist {
    private String name;
    private String password;
    public Receptionist(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public String getName() { return name; }
    public String getPassword() { return password;}
}