package com.getir.bookstore.module.book.validator;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.book.model.Book;
import com.getir.bookstore.module.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class BookValidator {

  private BookRepository bookRepo;

  @Autowired
  public BookValidator(BookRepository bookRepo) {
    this.bookRepo = bookRepo;
  }

  public Result validate(Book book) {
    if (bookRepo.findBookByTitleAndAuthor(book.getTitle(), book.getAuthor()) != null) {
      return Result.withError(HttpStatus.CONFLICT, "Book already exists.");
    }

    return Result.success();
  }

}
