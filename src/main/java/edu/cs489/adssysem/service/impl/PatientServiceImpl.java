package edu.cs489.adssysem.service.impl;

import edu.cs489.adssysem.dto.request.PatientRequest;
import edu.cs489.adssysem.dto.response.PatientResponse;
import edu.cs489.adssysem.exception.PatientNotFoundException;
import edu.cs489.adssysem.model.Address;
import edu.cs489.adssysem.model.Patient;
import edu.cs489.adssysem.repository.AddressRepository;
import edu.cs489.adssysem.repository.PatientRepository;
import edu.cs489.adssysem.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public PatientResponse savePatient(PatientRequest patientRequest) {
        Patient newPatient = new Patient(
                0,
                patientRequest.firstName(),
                patientRequest.lastName(),
                patientRequest.contactPhoneNo(),
                patientRequest.email(),
                patientRequest.dob(),
                new Address(
                        0,
                        patientRequest.address().getStreet(),
                        patientRequest.address().getCity(),
                        patientRequest.address().getState(),
                        patientRequest.address().getZip()
                )
        );
        Patient patient = patientRepository.save(newPatient);
        return new PatientResponse(
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getContactPhoneNo(),
                patient.getEmail(),
                patient.getDob(),
                new Address(
                        patient.getAddress().getAddressId(),
                        patient.getAddress().getStreet(),
                        patient.getAddress().getCity(),
                        patient.getAddress().getState(),
                        patient.getAddress().getZip()));
    }

    @Override
    public List<PatientResponse> getAllPatient() {
        return patientRepository.findAll(Sort.by("lastName"))
                .stream()
                .map(p -> new PatientResponse(p.getPatientId(), p.getFirstName(), p.getLastName(), p.getContactPhoneNo(), p.getEmail(), p.getDob(), (p.getAddress() != null) ? new Address(p.getAddress().getAddressId(), p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZip()) : null)).toList();
    }

    @Override
    public PatientResponse getPatientById(Integer patientId) throws PatientNotFoundException {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isPresent()) {
            Patient p = patientOptional.get();
            return new PatientResponse(p.getPatientId(), p.getFirstName(), p.getLastName(), p.getContactPhoneNo(), p.getEmail(), p.getDob(), p.getAddress() != null ? new Address(p.getAddress().getAddressId(), p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZip()) : null);
        } else {
            throw new PatientNotFoundException(String.format("Error: Patient with Id, %d, is not found.", patientId));
        }
    }

    @Override
    public PatientResponse updatePatient(Integer patientId, Patient editPatient) {
        Patient patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null) {
            patient.setFirstName(editPatient.getFirstName());
            patient.setLastName(editPatient.getLastName());
            patient.setContactPhoneNo(editPatient.getContactPhoneNo());
            patient.setEmail(editPatient.getEmail());
            patient.setDob(editPatient.getDob());
            if (patient.getAddress() != null) {
                Address address = patient.getAddress();
                address.setStreet(editPatient.getAddress().getStreet());
                address.setCity(editPatient.getAddress().getCity());
                address.setState(editPatient.getAddress().getState());
                address.setZip(editPatient.getAddress().getZip());
                patient.setAddress(address);
            } else {
                Address newAddress = new Address();
                newAddress.setStreet(editPatient.getAddress().getStreet());
                newAddress.setCity(editPatient.getAddress().getCity());
                newAddress.setState(editPatient.getAddress().getState());
                newAddress.setZip(editPatient.getAddress().getZip());
                newAddress.setPatient(patient);
                patient.setAddress(newAddress);
            }
            Patient p = patientRepository.save(patient);
            return new PatientResponse(
                    p.getPatientId(),
                    p.getFirstName(),
                    p.getLastName(),
                    p.getContactPhoneNo(),
                    p.getEmail(),
                    p.getDob(),
                    new Address(
                            p.getAddress().getAddressId(),
                            p.getAddress().getStreet(),
                            p.getAddress().getCity(),
                            p.getAddress().getState(),
                            p.getAddress().getZip()));
        } else {
            return null;
        }
    }

    @Override
    public String deletePatientById(Integer patientId) {
        patientRepository.deleteById(patientId);
        return String.format("Patient with Id, %d, is deleted successfully.", patientId);
    }

    @Override
    public void deletePatientByAddressId(Integer patientId) {
        var patient = patientRepository.findById(patientId).orElse(null);
        if (patient != null){
            var address = patient.getAddress();
            if (address!=null){
                patient.setAddress(null);
                patientRepository.save(patient);
                addressRepository.findById(address.getAddressId());
            }
        }
    }

    @Override
    public List<PatientResponse> searchPatient(String searchString) {
        var patients = patientRepository.findAll();
        return patients.stream().filter(p ->
                p.getFirstName().contains(searchString) ||
                        p.getLastName().contains(searchString) ||
                        p.getEmail().contains(searchString) ||
                        p.getContactPhoneNo().contains(searchString) ||
                        p.getDob().contains(searchString) ||
                        p.getAddress().getStreet().contains(searchString) ||
                        p.getAddress().getCity().contains(searchString) ||
                        p.getAddress().getState().contains(searchString))
                .map(p -> new PatientResponse(p.getPatientId(), p.getFirstName(), p.getLastName(), p.getContactPhoneNo(), p.getEmail(), p.getDob(), (p.getAddress() != null) ? new Address(p.getAddress().getAddressId(), p.getAddress().getStreet(), p.getAddress().getCity(), p.getAddress().getState(), p.getAddress().getZip()) : null)).toList();

    }
}
