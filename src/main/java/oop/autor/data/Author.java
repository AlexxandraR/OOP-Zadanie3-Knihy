package oop.autor.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oop.autor.web.bodies.AuthorRequest;
import oop.book.data.Book;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    @OneToMany (orphanRemoval = true)
    private List<Book> books;

    public Author(AuthorRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.books = new ArrayList<>();
    }
}