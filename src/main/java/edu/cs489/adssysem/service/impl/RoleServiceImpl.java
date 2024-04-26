package edu.cs489.adssysem.service.impl;

import edu.cs489.adssysem.dto.request.RoleRequest;
import edu.cs489.adssysem.dto.response.RoleResponse;
import edu.cs489.adssysem.model.Role;
import edu.cs489.adssysem.repository.RoleRepository;
import edu.cs489.adssysem.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleResponse saveRole(RoleRequest roleRequest) {
        Role role = new Role();
        role.setRoleName(roleRequest.roleName());

        Role addedRole = roleRepository.save(role);
        return new RoleResponse(addedRole.getRoleId(), addedRole.getRoleName());
    }

    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles =  roleRepository.findAll();

        return roles.stream().map( role -> new RoleResponse(role.getRoleId(), role.getRoleName())).toList();
    }

    @Override
    public Role getRoleById(Integer roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Integer roleId) {
        roleRepository.deleteById(roleId);
    }
}
