package com.getir.bookstore.module.book.repository;

import com.getir.bookstore.module.book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Book findBookByTitleAndAuthor(String title, String author);

}
