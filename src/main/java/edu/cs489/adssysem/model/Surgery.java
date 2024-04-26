package edu.cs489.adssysem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "surgery")
public class Surgery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer surgeryId;
    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", unique = true, nullable = true)
    private Address address;
    @Column(length = 15)
    private String telephoneNo;

    @OneToMany(mappedBy = "surgery")
    private List<Appointment> appointments;

    public Surgery(Integer surgeryId, String name, Address address, String telephoneNo) {
        this.surgeryId = surgeryId;
        this.name = name;
        this.address = address;
        this.telephoneNo = telephoneNo;
    }
}
