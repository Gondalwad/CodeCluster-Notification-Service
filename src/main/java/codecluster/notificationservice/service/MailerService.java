package codecluster.notificationservice.service;

import codecluster.notificationservice.dto.SendMailDto;
import codecluster.notificationservice.dto.SentMailDto;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MailerService {

    @Autowired
    private JavaMailSenderImpl sender;

    // tries to send email....
    public SentMailDto trySend(@Valid SendMailDto dto) {

        // create mime message to send0
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message); // helper to create MimeMessage

        // Preparing dto to send to user as a kind of confirmation.
        SentMailDto confirmation = new SentMailDto();
        confirmation.setFrom("Auto Emailer");
        confirmation.setTo(dto.getTo());
        confirmation.setSentAt(LocalDate.now());
        confirmation.setStatus("Sent Success!");
        confirmation.setSuccessStatus(true);
        try {
            helper.setTo(dto.getTo());
            helper.setText(dto.getContent());
            sender.send(message);

        } catch (Exception e) {
            confirmation.setSentAt(LocalDate.MIN);
            confirmation.setStatus("Internal Mail Failure :: Or May be Your ISP/Institute/PC is not allowing these ports for smtp service.");
            confirmation.setSuccessStatus(false);
            e.printStackTrace();
        }

        return confirmation;

    }
}

