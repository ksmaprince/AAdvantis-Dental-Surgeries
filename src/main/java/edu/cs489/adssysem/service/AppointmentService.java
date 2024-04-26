package edu.cs489.adssysem.service;


import edu.cs489.adssysem.model.Appointment;

import java.util.List;

public interface AppointmentService {
    Appointment saveAppointment(Appointment appointment);

    List<Appointment> getAllAppointment();

    Appointment getAppointmentById(Integer appointmentId);

    Appointment updateAppointment(Appointment appointment);

    void deleteAppointmentById(Integer appointmentId);

}
