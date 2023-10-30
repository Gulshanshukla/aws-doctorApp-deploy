package learning.spring.controller;

import learning.spring.model.Doctor;
import learning.spring.model.Patient;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.service.Doctorservice;
import learning.spring.service.Patientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class Admincontroller {
    @Autowired
    Doctorservice doctorservice;
    @Autowired
    Patientservice patientservice;

    @PostMapping("doctor")
    public String adddoctor(@RequestBody Doctor newdoctor) {
        return doctorservice.addDoctor(newdoctor);
    }
    @GetMapping("patients")
    public List<Patient> getallpatient()
    {
        return patientservice.getAllPatient();
    }
}
