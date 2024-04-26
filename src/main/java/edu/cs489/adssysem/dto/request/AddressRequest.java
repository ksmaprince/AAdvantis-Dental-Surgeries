package edu.cs489.adssysem.dto.request;

public record AddressRequest(
        String street,
        String city,
        String state,
        String zipCode
    ) {
}
