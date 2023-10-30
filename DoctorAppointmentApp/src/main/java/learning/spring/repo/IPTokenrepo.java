package learning.spring.repo;

import learning.spring.model.PatientAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IPTokenrepo extends JpaRepository<PatientAuthenticationToken,Integer> {

    PatientAuthenticationToken findFirstByTokenValue(String tokenValue);
}
