package ru.eremenko.elfin.service;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Service;
import ru.eremenko.elfin.dto.UserDataDto;
import ru.eremenko.elfin.entity.User;
import ru.eremenko.elfin.repository.UserRepository;

/**
 * @author eremenko
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final StandardPBEStringEncryptor encryptor;

    public UserService(UserRepository userRepository,
                       StandardPBEStringEncryptor encryptor) {
        this.userRepository = userRepository;
        this.encryptor = encryptor;
    }

    public boolean checkUserExistence(String userEmail, String username) {
        return userRepository.checkExistenceUsersWithSameEmailOrUsername(username, userEmail);
    }

    public void saveUser(UserDataDto userData) {
        User user = new User();
        user.setUserName(userData.getUsername());
        user.setEmail(userData.getEmail());
        user.setPassword(encryptor.encrypt(userData.getPassword()));

        userRepository.save(user);
    }
}
