package edu.cs489.adssysem.dto.request;

import java.util.List;

public record UserRequest(
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        List<Integer> roleIds
) {
}
