package edu.cs489.adssysem.service;


import edu.cs489.adssysem.dto.request.RoleRequest;
import edu.cs489.adssysem.dto.response.RoleResponse;
import edu.cs489.adssysem.model.Role;

import java.util.List;

public interface RoleService {
    RoleResponse saveRole(RoleRequest roleRequest);

    List<RoleResponse> getAllRoles();

    Role getRoleById(Integer roleId);

    Role updateRole(Role role);

    void deleteRoleById(Integer roleId);
}
