package edu.cs489.adssysem.controller;

import edu.cs489.adssysem.dto.request.RoleRequest;
import edu.cs489.adssysem.dto.response.RoleResponse;
import edu.cs489.adssysem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adsweb/api/v1/auth")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/addRole")
    public ResponseEntity<RoleResponse> saveRoll(@RequestBody RoleRequest role){
        return ResponseEntity.ok(roleService.saveRole(role));
    }

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }
}
