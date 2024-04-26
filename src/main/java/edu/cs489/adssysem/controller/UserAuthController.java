package edu.cs489.adssysem.controller;

import edu.cs489.adssysem.config.ADSWebAPISecurityConfig;
import edu.cs489.adssysem.dto.request.UserAuthRequest;
import edu.cs489.adssysem.dto.request.UserRequest;
import edu.cs489.adssysem.dto.response.RoleResponse;
import edu.cs489.adssysem.dto.response.UserAuthResponse;
import edu.cs489.adssysem.dto.response.UserResponse;
import edu.cs489.adssysem.exception.RegisterUserException;
import edu.cs489.adssysem.model.Role;
import edu.cs489.adssysem.model.User;
import edu.cs489.adssysem.service.RoleService;
import edu.cs489.adssysem.service.UserService;
import edu.cs489.adssysem.util.JWTMgmtUtilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = {"/adsweb/api/v1/auth"})
public class UserAuthController {

    @Autowired
    private JWTMgmtUtilityService jwtMgmtUtilityService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) throws RegisterUserException {
        User user = userService.getUserByUserName(userRequest.username());
        if (user != null) {
            throw new RegisterUserException("User Already Exist");
        } else {
            List<Role> roles = new ArrayList<>();
            userRequest.roleIds().forEach(roleId ->
                    roles.add(roleService.getRoleById(roleId))
            );

            String password = new BCryptPasswordEncoder().encode(userRequest.password());

            user = userService.saveUser(new User(0, userRequest.username(), password, userRequest.firstName(), userRequest.lastName(), userRequest.email(), true, true, true, true, roles));
        }

        List<RoleResponse> roleResponses = new ArrayList<>();
        user.getRoles().forEach(role ->
                roleResponses.add(new RoleResponse(role.getRoleId(), role.getRoleName()))
        );

        return new ResponseEntity(new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                roleResponses
        ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> authenticateUser(@Valid @RequestBody UserAuthRequest userAuthRequest) throws Exception {
        UserAuthResponse userAuthResponse = null;
        try {
            var username = userAuthRequest.username();
            var password = userAuthRequest.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password)
            );
            var jwtToken = jwtMgmtUtilityService.generateToken(username);
            var user = userService.getUserByUserName(username);
            if (user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken, user.getFirstName(), user.getLastName());
            }
        } catch (Exception ex) {
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }

}
