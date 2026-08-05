package codecluster.notificationservice.listener;

import codecluster.notificationservice.dto.SendMailDto;
import codecluster.notificationservice.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationKafkaListener {

    @Autowired
    private MailerService mailerService;

    @KafkaListener(topics = "send-notification-topic", groupId = "notification-service-group")
    public void handleEmailEvent(SendMailDto sendMailDto){
        System.out.println("Kafka event received for: " + sendMailDto.getTo());
        mailerService.trySend(sendMailDto);
    }
}
