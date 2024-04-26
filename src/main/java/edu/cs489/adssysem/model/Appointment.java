package edu.cs489.adssysem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private String status;

    @ManyToOne
    @JoinColumn(name = "patient_id", unique = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "surgery_id", unique = false)
    private Surgery surgery;

    @ManyToOne
    @JoinColumn(name = "dentist_id", unique = false)
    private Dentist dentist;

}
