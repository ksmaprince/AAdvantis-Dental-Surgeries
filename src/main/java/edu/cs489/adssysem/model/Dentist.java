package edu.cs489.adssysem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dentist")
public class Dentist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer dentistId;
    private String firstName;
    @Column(nullable = false)
    @NotBlank(message = "Last name can't be null or blank")
    private String lastName;

    @Column(length = 15)
    private String contactPhoneNumber;
    @Column(length = 45)
    private String email;
    private String specialization;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointments;

    public Dentist(Integer dentistId, String firstName, String lastName, String contactPhoneNumber, String email, String specialization) {
        this.dentistId = dentistId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactPhoneNumber = contactPhoneNumber;
        this.email = email;
        this.specialization = specialization;
    }
}
