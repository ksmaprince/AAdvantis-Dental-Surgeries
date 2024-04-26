package edu.cs489.adssysem.controller;

import edu.cs489.adssysem.dto.request.PatientRequest;
import edu.cs489.adssysem.dto.response.PatientResponse;
import edu.cs489.adssysem.exception.PatientNotFoundException;
import edu.cs489.adssysem.model.Patient;
import edu.cs489.adssysem.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/list")
    public ResponseEntity<List<PatientResponse>> getAllPatient(){
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @GetMapping("/get/{patientId}")
    public ResponseEntity<PatientResponse> getPatientById(@PathVariable Integer patientId) throws PatientNotFoundException {
        return new ResponseEntity<>(patientService.getPatientById(patientId), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<PatientResponse> addNewPatient(@Valid @RequestBody PatientRequest patientRequest){
        return ResponseEntity.ok(patientService.savePatient(patientRequest));
    }

    @PutMapping("/update/{patientId}")
    public ResponseEntity<PatientResponse> updatePatient(@PathVariable Integer patientId, @RequestBody Patient patient){
        return new ResponseEntity<>(patientService.updatePatient(patientId, patient), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatientById(@PathVariable Integer patientId){
        return new ResponseEntity<>(patientService.deletePatientById(patientId), HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<PatientResponse>> searchPatient(@Param("searchString") String searchString){
        return new ResponseEntity<>(patientService.searchPatient(searchString), HttpStatus.OK);
    }
}
