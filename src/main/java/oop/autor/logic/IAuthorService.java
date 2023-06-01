package oop.autor.logic;

import oop.autor.web.bodies.AuthorRequest;
import oop.autor.web.bodies.AuthorUpdateRequest;
import oop.autor.data.Author;
import oop.exception.NotFoundException;

import java.util.List;

public interface IAuthorService {
    List<Author> getAll();
    Author findAuthorById(Long id);
    Author create(AuthorRequest request);
    Author getById(long id) throws NotFoundException;
    Author update(long id, AuthorUpdateRequest request) throws NotFoundException;
    void delete(long id) throws NotFoundException;
}
