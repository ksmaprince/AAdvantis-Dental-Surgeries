package edu.cs489.adssysem.dto.request;

public record UserAuthRequest(
        String username,
        String password
) {
}
