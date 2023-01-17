package ru.eremenko.elfin.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.eremenko.elfin.dto.UserDataDto;
import ru.eremenko.elfin.repository.MailDomainRepository;

import javax.security.auth.login.CredentialException;
import java.util.zip.DataFormatException;

/**
 * @author eremenko
 */
@Service
public class RegistrationUserService {

    private final MailDomainRepository mailDomainRepository;
    private final UserService userService;

    public RegistrationUserService(MailDomainRepository mailDomainRepository,
                                   UserService userService) {
        this.mailDomainRepository = mailDomainRepository;
        this.userService = userService;
    }


    public void registerUser(UserDataDto user) throws CredentialException, DataFormatException {

            if (userService.checkUserExistence(user.getEmail(), user.getUsername())) {
                throw new CredentialException();
            }

            if (!checkUserEmailDomain(user.getEmail())) {
                throw new DataFormatException();
            }

            userService.saveUser(user);

    }

    private boolean checkUserEmailDomain(String userEmail) {
        String userEmailDomain = userEmail.split("@")[1];
        return mailDomainRepository.findByName(userEmailDomain, PageRequest.of(0, 1)).hasContent();
    }

}
