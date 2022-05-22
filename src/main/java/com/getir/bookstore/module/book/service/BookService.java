package com.getir.bookstore.module.book.service;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.book.model.Book;
import com.getir.bookstore.module.book.repository.BookRepository;
import com.getir.bookstore.module.book.validator.BookValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  Logger log = LoggerFactory.getLogger(BookService.class);

  private final BookRepository bookRepo;
  private final BookValidator bookValidator;

  @Autowired
  public BookService(BookRepository bookRepo, BookValidator bookValidator) {
    this.bookRepo = bookRepo;
    this.bookValidator = bookValidator;
  }

  public Result addBook(Book book) {
    Result result = bookValidator.validate(book);
    if (result.isHasError()) {
      return result;
    }

    Book savedBook = bookRepo.save(book);
    if (savedBook == null) {
      return Result.withError(
          HttpStatus.INTERNAL_SERVER_ERROR,
          "Internal Error while saving book!");
    }

    return Result.of(HttpStatus.CREATED, savedBook);
  }

  public void updateBook(Long bookId, Book book) {
    bookRepo.findById(bookId).ifPresent(b -> {
      b.setTitle(book.getTitle());
      b.setAuthor(book.getAuthor());
      b.setStock(book.getStock());
      b.setPrice(book.getPrice());
      b.setPublisher(book.getPublisher());

      bookRepo.save(b);
    });

    log.info("Book with id {bookId} updated successfully!", bookId);
  }

  public Book getBook(Long bookId) {
    return bookRepo.getReferenceById(bookId);
  }
}
