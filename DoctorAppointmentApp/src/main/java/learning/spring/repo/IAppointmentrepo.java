package learning.spring.repo;

import learning.spring.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppointmentrepo extends JpaRepository<Appointment,Integer> {
}
