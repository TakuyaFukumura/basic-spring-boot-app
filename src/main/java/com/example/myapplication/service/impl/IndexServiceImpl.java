package com.example.myapplication.service.impl;

import com.example.myapplication.entity.Message;
import com.example.myapplication.repository.MessageRepository;
import com.example.myapplication.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    private final MessageRepository messageRepository;

    public IndexServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public String getMessage() {
        log.info("getMessage was called"); // ログ出力例
        Message message = messageRepository.findById(1L).orElse(null);
        return message == null ? "Error!" : message.getText();
    }
}