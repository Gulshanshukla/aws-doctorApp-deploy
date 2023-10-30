package learning.spring.service;

import learning.spring.model.Doctor;
import learning.spring.model.Patient;
import learning.spring.model.PatientAuthenticationToken;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.model.dto.SignInInputDto;
import learning.spring.repo.IPatientrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service

public class Patientservice {
    @Autowired
    IPatientrepo iPatientrepo;
    @Autowired
    PTokenservice pTokenservice;

    public String patientSignup(Patient patient) {
        // check if already exist --> try logging in
        String newEmail=patient.getPatientEmail();
       Patient existingPatient= iPatientrepo.findFirstByPatientEmail(newEmail);
       if(existingPatient!=null)
       {
           return "email already in use";

       }
       //patient password encrypted before store it in the table
        String signupPassword= patient.getPatientPassword();
       try {
           String encryptedPassword = PasswordEncrypter.encrypt(signupPassword);
           patient.setPatientPassword(encryptedPassword);
           //patient table --> save patient
           iPatientrepo.save(patient);
           return "Patient  Registered";

       }
       catch(NoSuchAlgorithmException e)
       {
           return "Internal Server Issue while saving password ,try signin later";

       }







    }
    public String patientSignIn(SignInInputDto signInInput) {

        //check if the email is there in your tables
        //sign in only possible if this person ever signed up

        String email = signInInput.getEmail();

        Patient existingPatient = iPatientrepo.findFirstByPatientEmail(email);

        if(existingPatient == null)
        {
            return "Not a valid email, Please sign up first !!!";
        }

        //password should be matched

        String password = signInInput.getPassword();

        try {
            String encryptedPassword = PasswordEncrypter.encrypt(password);

            if(existingPatient.getPatientPassword().equals(encryptedPassword))
            {
                // return a token for this sign in
                PatientAuthenticationToken token  = new PatientAuthenticationToken(existingPatient);
              pTokenservice.createToken(token);
                return token.getTokenValue();

            }
            else {
                //password was wrong!!!
                return "Invalid Credentials!!!";
            }

        } catch (NoSuchAlgorithmException e) {

            return "Internal Server issues while saving password, try again later!!!";
        }


    }


    public String patientSignOut(AuthenticationInputDto authenticationinfo) {
        //the authentication token should get deleted
        if (pTokenservice.authenticate(authenticationinfo)) {
            String tokenValue = authenticationinfo.getTokenValue();
            pTokenservice.deleteToken(tokenValue);
            return "signout successful!!!";


        } else {
            return "UnAuthenticated Access!!!!!";
        }


    }

    public List<Patient> getAllPatient() {
     return  iPatientrepo.findAll();
    }
}
