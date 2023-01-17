package ru.eremenko.elfin.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ru.eremenko.elfin.dto.UserDataDto;

/**
 * @author eremenko
 */
@Service
public class NotificationSenderService {

    private final JavaMailSender mailSender;
    private final UserService userService;

    private final String from;

    public NotificationSenderService(JavaMailSender mailSender,
                                     UserService userService,
                                     @Value("${mail.from}") String from) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.from = from;
    }

    public void sendEmail(UserDataDto userData, boolean isRegisterSuccess) throws MessagingException {

        if (isRegisterSuccess) {
            userService.saveUser(userData);
        }

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");

        message.setFrom(from);
        message.setTo(userData.getEmail());
        message.setText(isRegisterSuccess ? "Вы успешно зарегистрированы" + userData.getUsername(): "Не удалось зарегистрироваться" + userData.getUsername());

        mailSender.send(message.getMimeMessage());
    }


}
