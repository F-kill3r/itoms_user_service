package com.capston_design.fkiller.itoms.user_service.client;

import com.capston_design.fkiller.itoms.user_service.dto.RandomUserResponseDTO;
import com.capston_design.fkiller.itoms.user_service.model.User;
import com.capston_design.fkiller.itoms.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserKafkaListener {

    private final UserService userService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.user.random.request:itoms.user.random.request}")
    private String randomRequestTopic;

    @Value("${kafka.topic.user.random.response:itoms.user.random.response}")
    private String randomResponseTopic;

    @KafkaListener(topics = "#{'${kafka.topic.user.random.request:itoms.user.random.request}'}",
            groupId = "${spring.kafka.consumer.group-id:itoms-user-service}")
    public void handleRandomUserRequest(String incidentIdStr) {
        String sanitized = incidentIdStr.replace("\"", "");
        log.info("[Kafka] random user request received for incident {}", sanitized);
        try {
            User random = userService.getRandomRequesterUser();
            RandomUserResponseDTO responseDTO = new RandomUserResponseDTO(
                    UUID.fromString(sanitized),
                    random.getId(),
                    random.getName()
            );
            kafkaTemplate.send(randomResponseTopic, sanitized, responseDTO);
            log.info("[Kafka] random user response sent for incident {}", sanitized);
        } catch (Exception e) {
            log.error("[Kafka] failed processing random user request for incident {}", sanitized, e);
        }
    }
} 