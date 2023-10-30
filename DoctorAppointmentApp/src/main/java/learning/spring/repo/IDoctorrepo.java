package learning.spring.repo;

import learning.spring.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface IDoctorrepo extends JpaRepository<Doctor ,Integer> {
}
