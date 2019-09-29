package com.obit.springdemo;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@Slf4j
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        messageRepository.save(message);
        log.info("New message {} created", message);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @DeleteMapping("/messages")
    public ResponseEntity<Void> deleteMessage(@RequestParam Long messageId){
        messageRepository.deleteById(messageId);
        log.info("Message with messageId {} deleted" , messageId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Message>> getAllMessages(@RequestParam int page, @RequestParam int size){
        Page<Message> all = messageRepository.findAll(PageRequest.of(page, size));
        log.info("Found messages: {} from total elements: {}", all.getContent(), all.getTotalElements());
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

}
