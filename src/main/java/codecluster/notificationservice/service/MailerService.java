package codecluster.notificationservice.service;

import codecluster.notificationservice.dto.SendMailDto;
import codecluster.notificationservice.dto.SentMailDto;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Service
public class MailerService {

    @Value("classpath:templates/mail-template.html")
    private Resource mailTemplate;

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

            // Load HTML template
            String html = getHtmlTemplate();

            // Replace placeholders
            html = html.replace("{{TITLE}}", dto.getTitle());
            html = html.replace("{{CONTENT}}", dto.getContent());

            //prepare email
            helper.setTo(dto.getTo());
            helper.setSubject(dto.getSubject());

            //true = html email
            helper.setText(html, true);

            //send email
            sender.send(message);

            confirmation.setStatus("Mail sent successfully");
            confirmation.setSuccessStatus(true);

        } catch (Exception e) {
            confirmation.setSentAt(LocalDate.MIN);
            confirmation.setStatus("Internal Mail Failure :: Or May be Your ISP/Institute/PC is not allowing these ports for smtp service.");
            confirmation.setSuccessStatus(false);
            e.printStackTrace();
        }

        return confirmation;

    }

    private String getHtmlTemplate() throws IOException {
        return new String(
                mailTemplate.getInputStream().readAllBytes(),
                StandardCharsets.UTF_8
        );
    }
}

