package service;
import data.DataStore;
import model.*;

import java.util.List;
import java.util.Optional;

public class AuthService {
    public boolean receptionistLogin(String name, String password) {
        List<Receptionist> list = DataStore.loadReceptionists();
        return list.stream().anyMatch(r -> r.getName().equals(name) && r.getPassword().equals(password));
    }

    public Optional<Doctor> doctorLogin(String name, String password) {
        return DataStore.loadDoctors().stream()
                .filter(d -> d.getName().equals(name) && d.getPassword().equals(password))
                .findFirst();
    }

    public Optional<Patient> patientLogin(String id, String name) {
        return DataStore.loadPatients().stream()
                .filter(p -> p.getId().equals(id) && p.getName().equals(name))
                .findFirst();
    }
}
