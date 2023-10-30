package learning.spring.controller;

import learning.spring.model.Patient;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.model.dto.ScheduleAppointmentDTO;
import learning.spring.model.dto.SignInInputDto;
import learning.spring.service.Appointmentservice;
import learning.spring.service.Patientservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class Patientcontroller {
    @Autowired
    Patientservice patientservice;
    @Autowired
    Appointmentservice appointmentservice;
    //signup
    @PostMapping("patient/signup")
    public String patientsignup(@RequestBody Patient patient)
    {
        return patientservice.patientSignup(patient);

    }
    //sign in
    @PostMapping("patient/signin")
    public String patientsignin( @RequestBody SignInInputDto signInInput)

    {
        return patientservice.patientSignIn(signInInput);



    }
    //signout
    @DeleteMapping("patient/signout")
    public String patientsignout(@RequestBody AuthenticationInputDto authenticationinfo)
    {
      return  patientservice.patientSignOut(authenticationinfo);

    }
    //shedule an appointment
    @PostMapping("patient/appointment/schedule")
    public String scheduleAppointment(@RequestBody ScheduleAppointmentDTO scheduleAppointmentDTO)
    {
        return appointmentservice.scheduleAppointment(scheduleAppointmentDTO.getAuthInfo(),scheduleAppointmentDTO.getAppointment());
    }
    //cancel appointment
    @DeleteMapping("patient/appointment/{appointmentId}/cancel")
    public String cancelAppointment(@RequestBody AuthenticationInputDto authInfo, @PathVariable Integer appointmentId)
    {
        return appointmentservice.cancelAppointment(authInfo,appointmentId);
    }
}
