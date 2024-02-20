package com.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.entity.Book;
import com.student.response.APIResponse;
import com.student.service.BookRestConsumer;

@RestController
@RequestMapping("/student")
public class StudentRestController {

      @Autowired
      private BookRestConsumer consumer;

      @GetMapping("/data")
      public APIResponse<String> getStudentInfo() {
         System.out.println(consumer.getClass().getName());  //prints as a proxy class
         APIResponse apiResponse=new APIResponse(HttpStatus.OK,"all books", consumer.getBookData());

         return apiResponse;
        		// "Accessing from STUDENT-SERVICE ==> " +consumer.getBookData();
      }

      @GetMapping("/allBooks")
      public APIResponse<String> getBooksInfo() {
          APIResponse apiResponse=new APIResponse(HttpStatus.OK,"all books", consumer.getAllBooks());
   List<Book> book=consumer.getAllBooks();
          //"Accessing from STUDENT-SERVICE ==> " + consumer.getAllBooks();
          return apiResponse ;
      }

      @GetMapping("/getOneBook/{id}")
      public APIResponse<String> getOneBookForStd(@PathVariable ("id")   String id) {
          APIResponse apiResponse=new APIResponse(HttpStatus.OK,"find book by id "+id, consumer.getBookById(id));

    	//  "Accessing from STUDENT-SERVICE ==> " + consumer.getBookById(id); 
         return  apiResponse; 
      }

      @PostMapping("/entityData")
      public APIResponse<String> printEntityData(@RequestBody Book book) {
       //  String resp = consumer.getEntityData(book);
        		// "Accessing from STUDENT-SERVICE ==> " + resp.getBody() +" , status is:" + resp.getStatusCode();
         APIResponse apiResponse=new APIResponse(HttpStatus.OK,"all books", consumer.getEntityData(book));
         return  apiResponse;
      }
}
