package oop.book.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oop.book.web.bodies.BookRequest;
import oop.book.web.bodies.BookUpdateRequest;
import oop.book.logic.IBookService;
import oop.book.web.bodies.Amount;
import oop.book.web.bodies.BookResponse;
import oop.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService service;

    @GetMapping()
    public List<BookResponse> getAllBooks(){
        return this.service.getAll().stream().map(BookResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest request) throws NotFoundException {
        return new ResponseEntity<>(new BookResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public BookResponse getBook(@PathVariable("id") Long bookId) throws NotFoundException {
        return new BookResponse(this.service.getById(bookId));
    }

    @PutMapping(value = "/{id}")
    public BookResponse updateBook(@PathVariable("id") Long bookId, @RequestBody BookUpdateRequest body) throws NotFoundException {
        return new BookResponse(this.service.update(bookId, body));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteBook(@PathVariable("id") Long bookId) throws NotFoundException {
        this.service.delete(bookId);
    }

    @GetMapping(value = "/{id}/amount")
    public Amount getAmount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.service.getAmount(bookId));
    }

    @PostMapping(value = "/{id}/amount")
    public Amount addAmount(@PathVariable("id") Long bookId, @RequestBody Amount body) throws NotFoundException {
        return new Amount(this.service.addAmount(bookId, body.getAmount()));
    }

    @GetMapping(value = "/{id}/lendCount")
    public Amount getLendCount(@PathVariable("id") Long bookId) throws NotFoundException {
        return new Amount(this.service.getLendCount(bookId));
    }

}
