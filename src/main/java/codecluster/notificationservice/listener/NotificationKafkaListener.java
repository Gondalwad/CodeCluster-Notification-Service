package codecluster.notificationservice.listener;

import codecluster.notificationservice.dto.SendMailDto;
import codecluster.notificationservice.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka listener responsible for consuming email notification events
 * and delegating them to the mail service for processing.
 */
@Component
public class NotificationKafkaListener {

    @Autowired
    private MailerService mailerService;

    /**
     * Consumes email notification events from the configured Kafka topic.
     * Each received message contains the details required to send an email.
     *
     * @param sendMailDto email request received from the Kafka topic
     */
    @KafkaListener(topics = "send-notification-topic", groupId = "notification-service-group")
    public void handleEmailEvent(SendMailDto sendMailDto){

        // Log the recipient of the received notification event.
        System.out.println("Kafka event received for: " + sendMailDto.getTo());

        // Delegate email sending to the mail service.
        mailerService.trySend(sendMailDto);
    }
}