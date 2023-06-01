package oop.autor.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oop.autor.web.bodies.AuthorRequest;
import oop.autor.web.bodies.AuthorUpdateRequest;
import oop.autor.data.Author;
import oop.autor.data.AuthorRepository;
import oop.exception.NotFoundException;

import java.util.List;

@Service
public class AuthorService implements IAuthorService {
    @Autowired
    private AuthorRepository repository;

    @Override
    public List<Author> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Author findAuthorById(Long id) {
        return this.repository.findAuthorById(id);
    }

    @Override
    public Author create(AuthorRequest request) {
        return this.repository.save(new Author(request));
    }

    @Override
    public Author getById(long id) throws NotFoundException {
        Author author = this.repository.findAuthorById(id);
        if (author == null) {
            throw new NotFoundException();
        }
        return author;
    }

    @Override
    public Author update(long id, AuthorUpdateRequest request) throws NotFoundException {
        Author author = this.getById(id);
        if (request.getName() != null) {
            author.setName(request.getName());
        }
        if (request.getSurname() != null) {
            author.setSurname(request.getSurname());
        }
        return this.repository.save(author);
    }

    @Override
    public void delete(long id) throws NotFoundException {
        this.repository.delete(this.getById(id));
    }

}