package codecluster.notificationservice.controller;

import codecluster.notificationservice.dto.SendMailDto;
import codecluster.notificationservice.dto.SentMailDto;
import codecluster.notificationservice.service.MailerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for handling mail-related API requests.
 */
@RestController
@RequestMapping("/api/v1/mail")
public class MailController {

    @Autowired
    private MailerService mailerService;

    /**
     * Sends an email using the provided request details.
     *
     * @param dto contains recipient, subject, body and other email details
     * @return response containing the status of the email delivery attempt
     */
    @PostMapping("/send")
    public ResponseEntity<SentMailDto> sendMail(
            @Valid @RequestBody SendMailDto dto) {

        // Delegate email sending to the service layer.
        SentMailDto response = mailerService.trySend(dto);

        // Return the email sending result with HTTP 200 status.
        return ResponseEntity.ok(response);
    }
}