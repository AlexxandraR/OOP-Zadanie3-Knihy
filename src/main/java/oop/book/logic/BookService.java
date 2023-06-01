package oop.book.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oop.autor.logic.AuthorService;
import oop.book.web.bodies.BookRequest;
import oop.book.web.bodies.BookUpdateRequest;
import oop.book.data.Book;
import oop.book.data.BookRepository;
import oop.exception.NotFoundException;

import java.util.List;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorService authorService;

    @Override
    public List<Book> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Book create(BookRequest request) throws NotFoundException {
        Book book = new Book(request, authorService.findAuthorById(request.getAuthor()));
        authorService.findAuthorById(request.getAuthor()).getBooks().add(book);
        return this.repository.save(book);
    }

    @Override
    public Book getById(long id) throws NotFoundException {
        Book book = this.repository.findBookById(id);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @Override
    public Book update(long id, BookUpdateRequest request) throws NotFoundException {
        Book book = this.getById(id);
        if (request.getName() != null) {
            book.setName(request.getName());
        }
        if (request.getDescription() != null) {
            book.setDescription(request.getDescription());
        }
        if (authorService.findAuthorById(request.getAuthor()) != null || request.getAuthor() != 0) {
            book.setAuthor(authorService.findAuthorById(request.getAuthor()));
        }
        if (request.getPages() != 0) {
            book.setPages(request.getPages());
        }
        return this.repository.save(book);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        Book book = this.repository.findBookById(id);
        if(book != null) {
            book.getAuthor().getBooks().remove(book);
        }
        this.repository.delete(this.getById(id));
    }

    @Override
    public int getAmount(long id) throws NotFoundException {
        return this.getById(id).getAmount();
    }

    @Override
    public int addAmount(long id, int increment) throws NotFoundException {
        Book book = this.getById(id);
        book.setAmount(book.getAmount() + increment);
        this.repository.save(book);
        return book.getAmount();
    }

    @Override
    public int getLendCount(long id) throws NotFoundException {
        return this.getById(id).getLendCount();
    }
}