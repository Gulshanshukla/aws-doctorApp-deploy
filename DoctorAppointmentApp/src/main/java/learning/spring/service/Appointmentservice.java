package learning.spring.service;

import learning.spring.model.Appointment;
import learning.spring.model.Doctor;
import learning.spring.model.Patient;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.repo.IAppointmentrepo;
import learning.spring.repo.IDoctorrepo;
import learning.spring.repo.IPatientrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class Appointmentservice {
    @Autowired
    IAppointmentrepo iAppointmentrepo;
    @Autowired
    PTokenservice pTokenservice;
    @Autowired
    IPatientrepo iPatientrepo;
    @Autowired
    IDoctorrepo iDoctorrepo;

    public String scheduleAppointment(AuthenticationInputDto authInfo, Appointment appointment) {
        if(pTokenservice.authenticate(authInfo)) {

            //find thr patient
            String email = authInfo.getEmail();

            Patient patient = iPatientrepo.findFirstByPatientEmail(email);

            appointment.setPatient(patient);


            //find the doctor

            Integer docId = appointment.getDoctor().getDoctorId();

            Doctor doc = iDoctorrepo.findById(docId).orElseThrow();

            appointment.setDoctor(doc);


            if(doc!= null)
            {
                appointment.setAppointmentCreationTime(LocalDateTime.now());
                iAppointmentrepo.save(appointment);
                return "appointment booked at time :" + appointment.getAppointmentSheduleTime() + " with Dr. " + doc.getDoctorName() ;
            }
            else
            {
                return "Doctor does not exist, Could not book appointment!!!";
            }
        }
        else {
            return "Un Authenticated access!!!";
        }

    }

    public String cancelAppointment(AuthenticationInputDto authInfo, Integer appointmentId) {
        if(pTokenservice.authenticate(authInfo)) {

            String email = authInfo.getEmail();

            Patient patient = iPatientrepo.findFirstByPatientEmail(email);

            Appointment existingAppointment =  iAppointmentrepo.findById(appointmentId).orElseThrow();

            if(existingAppointment.getPatient().equals(patient))
            {
                iAppointmentrepo.deleteById(appointmentId);
                return "appointment with " + existingAppointment.getDoctor().getDoctorName()+ " has been cancelled!!";

            }
            else
            {
                return "UnAuthorized cancel appointment!!";
            }

        }
        else {
            return "Un Authenticated access!!!";
        }

    }
}

