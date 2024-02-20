package com.book.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.book.entity.Book;
@Repository
public interface Studentrepo extends MongoRepository<Book, String> {

	Optional<Book> findBybookId(String bookId);

}
