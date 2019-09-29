package com.obit.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        Message save = messageRepository.save(message);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }
}
