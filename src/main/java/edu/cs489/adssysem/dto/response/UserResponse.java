package edu.cs489.adssysem.dto.response;

import java.util.List;

public record UserResponse(
        Integer userId,
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean credentialsNonExpired,
        boolean enabled,
        List<RoleResponse> roleResponses
) {
}
