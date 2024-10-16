package com.example.notificationservice.core.services;

import com.example.notificationservice.core.dtos.event.Event;
import com.example.notificationservice.core.dtos.notification.Notification;
import com.example.notificationservice.core.dtos.transaction.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void send(Event event){
        String[] statusMessage = createMessageStatus(event);
        String subject = statusMessage[0];
        String message = statusMessage[1];

        var notification = createNotification(event, subject, message);
        this.sendEmail(notification);
    }

    private Notification createNotification(Event event, String subject, String message){
        var notification = Notification.builder()
                .email(event.getEmail())
                .subject(subject)
                .message(message)
                .build();
        return notification;
    }

    private void sendEmail(Notification notification){
        this.emailService.sendEmail(notification.getEmail(), notification.getSubject(), notification.getMessage());
    }

    private String[] createMessageStatus(Event event) {
        String subject;
        String message;

        switch (event.getStatus()) {
            case FAIL -> {
                subject = "Falha na Transação";
                message = "A transação falhou. Por favor, tente novamente ou entre em contato com o suporte.";
            }
            case PENDING -> {
                subject = "Transação Pendente";
                message = "A transação está pendente.";
            }
            case SUCCESS -> {
                subject = "Transação feita com Sucesso";
                message = "A transação foi realizada com sucesso. Obrigado por utilizar nossos serviços.";
            }
            default -> throw new IllegalArgumentException("Status desconhecido: " + event.getStatus());
        }

        return new String[]{subject, message};
    }

}
