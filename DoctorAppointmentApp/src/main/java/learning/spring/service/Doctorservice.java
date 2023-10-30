package learning.spring.service;

import learning.spring.model.Doctor;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.repo.IDoctorrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class Doctorservice {
    @Autowired
    IDoctorrepo iDoctorrepo;
    @Autowired
    PTokenservice pTokenservice;

    public List<Doctor> getAllDoctors( AuthenticationInputDto authenticationinfo) {
        if (pTokenservice.authenticate(authenticationinfo)) {


            return iDoctorrepo.findAll();
        }
        else {
            return null;
        }
    }

    public String addDoctor(Doctor newdoctor) {
        Integer doctorId= newdoctor.getDoctorId();
        if(doctorId!=null && iDoctorrepo.existsById(doctorId))
        {
            return "DOCTOR ALREADY EXIST";
        }
        newdoctor.setAppointments(null);
        iDoctorrepo.save(newdoctor);
        return "DOCTOR ADDED";
    }

    public Doctor getDoctorById(Integer id) {
       return iDoctorrepo.findById(id).orElseThrow();

    }
}
