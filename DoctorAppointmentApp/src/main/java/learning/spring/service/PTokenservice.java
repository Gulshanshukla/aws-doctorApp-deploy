package learning.spring.service;

import learning.spring.model.PatientAuthenticationToken;
import learning.spring.model.dto.AuthenticationInputDto;
import learning.spring.repo.IPTokenrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PTokenservice {
    @Autowired
    IPTokenrepo ipTokenrepo;

    public void createToken(PatientAuthenticationToken token) {
        ipTokenrepo.save(token);
    }


    public void deleteToken(String tokenValue) {
        PatientAuthenticationToken token = ipTokenrepo.findFirstByTokenValue(tokenValue);


    }

    public boolean authenticate(AuthenticationInputDto authenticationinfo) {
        String email= authenticationinfo.getEmail();
        String tokenValue=authenticationinfo.getTokenValue();
        //fing the actual token then get the connected patient gets its email and verify by past email if equals
        //then delete token
     //  return  ipTokenrepo.findFirstByTokenValue(tokenValue).getPatient().getPatientEmail().equals(email);
        PatientAuthenticationToken token =  ipTokenrepo.findFirstByTokenValue(tokenValue);
        if(token!=null)
        {
            return token.getPatient().getPatientEmail().equals(email);
        }
        else
        {
            return false;
        }

    }
}
