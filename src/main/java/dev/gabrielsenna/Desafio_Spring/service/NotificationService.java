package dev.gabrielsenna.Desafio_Spring.service;

import dev.gabrielsenna.Desafio_Spring.client.NotificationClient;
import dev.gabrielsenna.Desafio_Spring.entity.Transfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient) {
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transaction) {

        try {
            logger.info("Sending notification...");

            var response = notificationClient.sendNotification(transaction);

            if (response.getStatusCode().isError()) {
                logger.error("Error while sending notification, status code is not OK");
            }
        } catch (Exception e) {
            logger.error("Error while sending notification", e);
        }
    }
}
