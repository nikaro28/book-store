package com.getir.bookstore.module.book.controller;

import com.getir.bookstore.common.model.Result;
import com.getir.bookstore.module.book.model.Book;
import com.getir.bookstore.module.book.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/books")
@Api(value = "APIs related to resource : BOOK")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @ApiResponses(
      value = {
        @ApiResponse(code = 201, message = "Successfully added book."),
        @ApiResponse(code = 400, message = "The book info is not complete and valid."),
        @ApiResponse(code = 409, message = "Book with this info already exists."),
        @ApiResponse(code = 500, message = "Server encountered a problem while processing request.")
      })
  @PostMapping(produces = "application/json")
  public Object addBook(@RequestBody Book book, HttpServletResponse response) {
    Result result = bookService.addBook(book);
    if (result.isHasError()) {
      try {
        response.sendError(result.getStatus().value(), result.getObject().toString());
      } catch (IOException e) {
      }
      return result.getObject();
    }

    response.setStatus(HttpStatus.CREATED.value());
    return result.getObject();
  }

  @PutMapping(value = "/{bookId}", produces = "application/json")
  public void updateBook(@PathVariable Long bookId, @RequestBody Book book) {
    bookService.updateBook(bookId, book);
  }
}
