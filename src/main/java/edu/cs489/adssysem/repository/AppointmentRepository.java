package edu.cs489.adssysem.repository;

import edu.cs489.adssysem.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

//    @Query(name = "SELECT concat(d.first_name,\" \", d.last_name) as dentistName, p.patient_id as patNo, concat(p.first_name, \" \",p.last_name) as patName, concat(a.appointment_date, \" \",a.appointment_time) as appointmentDateTime, s.surgery_id as surgeryNo\n" +
//            "FROM appointment a, patient p, dentist d, surgery s\n" +
//            "WHERE a.dentist_id = d.dentist_id\n" +
//            "AND a.patient_id = p.patient_id\n" +
//            "AND a.surgery_id = s.surgery_id;")
//    List<AppointmentResult> getAllAppointmentWithDetailInfo();
}
