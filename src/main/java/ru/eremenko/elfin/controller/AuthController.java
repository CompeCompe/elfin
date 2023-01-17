package ru.eremenko.elfin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eremenko.elfin.dto.UserDataDto;
import ru.eremenko.elfin.service.RegistrationUserService;

import java.util.zip.DataFormatException;

/**
 * @author eremenko
 */
@RestController
@RequestMapping("/api/registration")
public class AuthController {

    private final RegistrationUserService registrationUserService;

    public AuthController(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDataDto userData) {
        try {
            registrationUserService.registerUser(userData);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataFormatException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
