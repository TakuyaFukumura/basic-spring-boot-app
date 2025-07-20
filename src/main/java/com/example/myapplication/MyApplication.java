package com.example.myapplication;

import com.example.myapplication.entity.Message;
import com.example.myapplication.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyApplication implements CommandLineRunner {

    private final MessageRepository messageRepository;

    @Autowired
    public MyApplication(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // テストデータの初期化
        if (messageRepository.findById(1L).isEmpty()) {
            Message message = new Message();
            message.setId(1L);
            message.setText("Hello World!");
            messageRepository.save(message);
        }
    }
}
