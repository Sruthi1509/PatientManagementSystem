package service;

import java.util.ArrayList;

public class DoctorService {
    private ArrayList<String> doctors = new ArrayList<>();

    public void addDoctor(String name) {
        doctors.add(name);
        System.out.println("Doctor added: " + name);
    }

    public void listDoctors() {
        System.out.println("Doctor List:");
        for (String name : doctors) {
            System.out.println(name);
        }
    }
}
