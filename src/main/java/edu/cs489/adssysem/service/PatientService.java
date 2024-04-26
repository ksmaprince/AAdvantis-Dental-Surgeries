package edu.cs489.adssysem.service;


import edu.cs489.adssysem.dto.request.PatientRequest;
import edu.cs489.adssysem.dto.response.PatientResponse;
import edu.cs489.adssysem.exception.PatientNotFoundException;
import edu.cs489.adssysem.model.Patient;

import java.util.List;

public interface PatientService {
    PatientResponse savePatient(PatientRequest patientRequest);

    List<PatientResponse> getAllPatient();

    PatientResponse getPatientById(Integer patientId) throws PatientNotFoundException;

    PatientResponse updatePatient(Integer patientId, Patient editPatient);

    String deletePatientById(Integer patientId);

    void deletePatientByAddressId(Integer patientId);

    List<PatientResponse> searchPatient(String searchString);
}
