package learning.spring.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, scope=Patient.class,property="patientId")

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;
    private String patientName;
    private String patientEmail;
    private String PatientPassword;
    @Enumerated(value = EnumType.STRING)

    private Gender patientGender;
    @Enumerated(value = EnumType.STRING)
    private BloodGroup patientBloodGroup;
    private String patientContact;
    private LocalDateTime patientDateOfBirth;
    @OneToMany(mappedBy = "patient")
    List<Appointment> appointments;


}
