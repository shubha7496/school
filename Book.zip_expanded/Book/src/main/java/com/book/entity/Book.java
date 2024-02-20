package com.book.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@Getter
@Setter
@Document(collection = "Book")
public class Book {

     private String bookId;
     private String bookName;
     private Double bookCost;
}
