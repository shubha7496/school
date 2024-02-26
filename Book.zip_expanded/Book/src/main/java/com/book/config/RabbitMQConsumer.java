package com.book.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.book.entity.Book;
import com.book.repo.Studentrepo;

@Component
public class RabbitMQConsumer {
	 
	@Autowired
	private Studentrepo repo;

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void processMessage(Book book) {
        // Process the book data received from RabbitMQ
        // Example: Save the book data to the database
        repo.save(book);
    }
}

