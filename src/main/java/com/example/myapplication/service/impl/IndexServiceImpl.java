package com.example.myapplication.service.impl;

import com.example.myapplication.entity.Message;
import com.example.myapplication.repository.MessageRepository;
import com.example.myapplication.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/** メッセージ取得サービスの実装。 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {

    private final MessageRepository messageRepository;

    public IndexServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public String getMessage() {
        log.info("getMessage was called");
        Message message = messageRepository.findById(1L).orElse(null);
        return message == null ? "Error!" : message.getText();
    }
}
