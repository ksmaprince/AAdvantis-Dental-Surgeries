package edu.cs489.adssysem.dto.response;

import edu.cs489.adssysem.model.Address;

public record PatientResponse(Integer patientId,
                              String firstName,

                              String lastName,

                              String contactPhoneNo,

                              String email,

                              String dob,
                              Address address) {

}
