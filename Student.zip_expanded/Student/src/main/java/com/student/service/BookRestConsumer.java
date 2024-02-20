package com.student.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.student.entity.Book;


@FeignClient(name="BOOK-SERVICE")
public interface BookRestConsumer {

      @GetMapping("/book/data")
      public List<Book> getBookData();

      @GetMapping("/book/{id}")
      public Book getBookById(@PathVariable  String id);

      @GetMapping("/book/all")
      public List<Book> getAllBooks();

      @PostMapping("/book/entity")
      public Book getEntityData(@RequestBody Book book);
}

//http://localhost:9100/student/data
//http://localhost:9100/student/allBooks
//http://localhost:9100/student/getOneBook/501
//http://localhost:9100/student/entityData
