package com.bc.producermessage.controller;

import com.bc.producermessage.dto.UserDTO;
import com.bc.producermessage.rabbitmq.UserAMQPConfig;
import com.bc.producermessage.service.UserAMQPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserAMQPService userAMQPService;

    @PostMapping
    public ResponseEntity<HttpStatus> writeMessage(@RequestBody UserDTO userDTO) {
        try {
            userAMQPService.sendMessage(UserAMQPConfig.QUEUE, userDTO);
            System.out.println(userDTO.getName());
            return ResponseEntity.ok().build();

        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
