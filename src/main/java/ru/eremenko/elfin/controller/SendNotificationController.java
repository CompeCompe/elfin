package ru.eremenko.elfin.controller;

import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.eremenko.elfin.dto.UserDataDto;
import ru.eremenko.elfin.service.NotificationSenderService;

/**
 * @author eremenko
 */
@RestController
@RequestMapping("/api/notification")
public class SendNotificationController {

    private final NotificationSenderService notificationSenderService;

    public SendNotificationController(NotificationSenderService notificationSenderService) {
        this.notificationSenderService = notificationSenderService;
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(@RequestBody UserDataDto userData,
                                              @RequestParam("isSuccess") Boolean isSuccess) {
        try {
            notificationSenderService.sendEmail(userData, isSuccess);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (MessagingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
