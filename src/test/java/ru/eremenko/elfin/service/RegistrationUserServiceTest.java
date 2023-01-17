package ru.eremenko.elfin.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ru.eremenko.elfin.dto.UserDataDto;
import ru.eremenko.elfin.entity.MailDomain;
import ru.eremenko.elfin.repository.MailDomainRepository;

import javax.security.auth.login.CredentialException;

import java.util.List;
import java.util.zip.DataFormatException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * @author eremenko
 */
@RunWith(MockitoJUnitRunner.class)
public class RegistrationUserServiceTest {

    @InjectMocks
    private RegistrationUserService sut;
    @Mock
    private UserService userService;
    @Mock
    private MailDomainRepository mailDomainRepository;


    @Test
    public void successRegister() {
        when(userService.checkUserExistence(anyString(), anyString())).thenReturn(false);
        when(mailDomainRepository.findByName(anyString(), any())).thenReturn(new PageImpl<MailDomain>(List.of(new MailDomain())));

        UserDataDto userData = fillUserData("username", "email@gmail.com", "password");

        try {
            sut.registerUser(userData);
        } catch (Exception e) {
            Assert.fail("Unexpected exception");
        }

        verify(userService, times(1)).saveUser(userData);
    }

    @Test(expected = CredentialException.class)
    public void userAlreadyExist() throws DataFormatException, CredentialException {
        when(userService.checkUserExistence(anyString(), anyString())).thenReturn(true);
        when(mailDomainRepository.findByName(anyString(), any())).thenReturn(Mockito.mock(Page.class));

        UserDataDto userData = fillUserData("username", "email@mail", "password");

        sut.registerUser(userData);
    }

    @Test(expected = DataFormatException.class)
    public void notAllowedDomain() throws DataFormatException, CredentialException {
        when(userService.checkUserExistence(anyString(), anyString())).thenReturn(false);

        when(mailDomainRepository.findByName(anyString(), any())).thenReturn(Page.empty());

        UserDataDto userData = fillUserData("username", "email@mail.ru", "password");

        sut.registerUser(userData);
    }

    private UserDataDto fillUserData(String username, String email, String password) {
        UserDataDto userDataDto = new UserDataDto();
        userDataDto.setUsername(username);
        userDataDto.setEmail(email);
        userDataDto.setPassword(password);

        return userDataDto;
    }







}