package com.bc.producermessage.service;

import com.bc.producermessage.dto.UserDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAMQPService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String queue, UserDTO userDTO) throws JsonProcessingException {
        rabbitTemplate
                .convertAndSend(queue, new ObjectMapper().writeValueAsString(userDTO));
    }

}
