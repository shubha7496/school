package com.book.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.config.MessagingConfig;
import com.book.entity.Book;
import com.book.exception.BooKIdNotFoundException;
import com.book.repo.Studentrepo;
import com.book.response.APIResponse;

@RestController
@RequestMapping("/book")
public class BookRestController {

      @Autowired
      Environment environment;
      
      @Autowired
      private Studentrepo studentrepo;
      
      @Autowired
      private RabbitTemplate template;

      @GetMapping("/data")
      public List<Book> getBookData() {
    	 List< Book> book=studentrepo.findAll();
    	  APIResponse apiResponse =new APIResponse(HttpStatus.OK,"all books", studentrepo.findAll());
         return book ;
      }

      @GetMapping("/{id}")
      public  Book getBookById(@PathVariable  String id) {
    	  Book user = studentrepo.findBybookId(id)
    		       .orElseThrow(() -> new BooKIdNotFoundException("book not found with this bookid: " + id));
    	  APIResponse apiResponse =new APIResponse(HttpStatus.OK,"book id "+id+" data", studentrepo.findBybookId(id));
    	  
    	  return user;
      }

      @GetMapping("/all")
      public List<Book> getAll(){
    	  List<Book> book=studentrepo.findAll();
    	  APIResponse apiResponse =new APIResponse(HttpStatus.OK,"all books", book);

         return book;
      }

//      @PostMapping("/entity")
//      public ResponseEntity<String> getEntityData(@RequestBody Book book) {
////    	  book.setBookId(book.getBookId());
////    	  book.setBookCost(book.getBookCost());
////    	  book.setBookName(book.getBookName());
//         return new ResponseEntity(studentrepo.save(book),HttpStatus.OK);
//      }
      
      @PostMapping("/entity")
      public ResponseEntity<String> getEntityData(@RequestBody Book book) {
          try {
              // Save the book data to the database
              studentrepo.save(book);
              // Publish the book data to RabbitMQ
              template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, book);
              return new ResponseEntity<>("Book data saved and published to RabbitMQ", HttpStatus.OK);
          } catch (Exception e) {
              return new ResponseEntity<>("Failed to save or publish book data", HttpStatus.INTERNAL_SERVER_ERROR);
          }
      }
}


