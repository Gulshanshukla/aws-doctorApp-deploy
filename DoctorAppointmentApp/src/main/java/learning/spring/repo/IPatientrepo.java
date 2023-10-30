package learning.spring.repo;

import learning.spring.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPatientrepo  extends JpaRepository<Patient,Integer> {


    Patient findFirstByPatientEmail(String newEmail);
}
