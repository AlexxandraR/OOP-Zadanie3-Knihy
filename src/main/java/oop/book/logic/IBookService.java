package oop.book.logic;

import oop.book.web.bodies.BookRequest;
import oop.book.web.bodies.BookUpdateRequest;
import oop.book.data.Book;
import oop.exception.NotFoundException;

import java.util.List;

public interface IBookService {
    List<Book> getAll();
    Book create(BookRequest request) throws NotFoundException;
    Book getById(long id) throws NotFoundException;
    Book update(long id, BookUpdateRequest request) throws NotFoundException;
    void delete(long id) throws NotFoundException;
    int getAmount(long id) throws NotFoundException;
    int addAmount(long id, int increment) throws NotFoundException;
    int getLendCount(long id) throws NotFoundException;
}
