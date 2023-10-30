package learning.spring.model.dto;

import learning.spring.model.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ScheduleAppointmentDTO {
    AuthenticationInputDto authInfo;
    Appointment appointment;
}
