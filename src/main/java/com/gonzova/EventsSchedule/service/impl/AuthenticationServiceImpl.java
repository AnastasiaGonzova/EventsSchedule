package com.gonzova.EventsSchedule.service.impl;

import com.gonzova.EventsSchedule.domain.dto.LoginRequest;
import com.gonzova.EventsSchedule.domain.emuns.RoleName;
import com.gonzova.EventsSchedule.domain.entity.Credential;
import com.gonzova.EventsSchedule.domain.entity.Employee;
import com.gonzova.EventsSchedule.domain.entity.Role;
import com.gonzova.EventsSchedule.repository.CredentialRepository;
import com.gonzova.EventsSchedule.repository.RoleRepository;
import com.gonzova.EventsSchedule.service.AuthenticationService;
import com.gonzova.EventsSchedule.service.EmployeeService;
import com.gonzova.EventsSchedule.service.TokenService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @NonNull
    private EmployeeService employeeService;

    @NonNull
    private TokenService tokenService;

    @NonNull
    private CredentialRepository credentialRepository;

    @NonNull
    private RoleRepository roleRepository;

    @NonNull
    private PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest loginRequest) {
        Credential credential = credentialRepository.findByLogin(loginRequest.getLogin())
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(loginRequest.getPass(), credential.getPass())) {
            return tokenService.generateToken(credential.getEmployee());
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public String signUp(Employee employeeJson) {
        Random random = new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        Credential credential = new Credential();
        credential.setEmployee(employeeJson);
        credential.setLogin(random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
        credential.setPass(passwordEncoder.encode(random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()));

        employeeJson.setCredential(credential);
        RoleName roleName = RoleName.USER;
        Role role = roleRepository.findByName(roleName).orElseThrow();
        employeeJson.addRole(role);
        employeeService.create(employeeJson);
        return tokenService.generateToken(employeeJson);
    }
}
