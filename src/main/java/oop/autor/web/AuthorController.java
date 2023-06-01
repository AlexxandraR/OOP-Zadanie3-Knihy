package oop.autor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oop.autor.web.bodies.AuthorRequest;
import oop.autor.web.bodies.AuthorUpdateRequest;
import oop.autor.logic.IAuthorService;
import oop.autor.web.bodies.AuthorResponse;
import oop.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private IAuthorService service;

    @GetMapping()
    public List<AuthorResponse> getAllAuthors(){
        return this.service.getAll().stream().map(AuthorResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest request) {
        return new ResponseEntity<>(new AuthorResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public AuthorResponse getAuthor(@PathVariable("id") Long authorId) throws NotFoundException {
        return new AuthorResponse(this.service.getById(authorId));
    }

    @PutMapping(value = "/{id}")
    public AuthorResponse updateAuthor(@PathVariable("id") Long authorId, @RequestBody AuthorUpdateRequest body) throws NotFoundException {
        return new AuthorResponse(this.service.update(authorId, body));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteAuthor(@PathVariable("id") Long authorId) throws NotFoundException {
        this.service.delete(authorId);
    }

}