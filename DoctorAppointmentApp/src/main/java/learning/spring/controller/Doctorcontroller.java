package learning.spring.controller;

import learning.spring.model.Doctor;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.service.Doctorservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class Doctorcontroller {
    @Autowired
    Doctorservice doctorservice;
    @GetMapping("doctors")
    public List<Doctor> getalldoctors(@RequestBody AuthenticationInputDto authenticationinfo)
    {
        return doctorservice.getAllDoctors(authenticationinfo);
    }
    @GetMapping("doctor/{id}")
    public Doctor getalldoctorbyId(@PathVariable Integer id)
    {
        return doctorservice.getDoctorById(id);
    }


}
