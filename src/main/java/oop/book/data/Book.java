package oop.book.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oop.autor.data.Author;
import oop.book.web.bodies.BookRequest;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    @Setter
    @ManyToOne
    private Author author;

    private int pages;

    private int amount;

    private int lendCount;

    public Book(BookRequest request, Author author) {
        this.name = request.getName();
        this.description = request.getDescription();
        this.author = author;
        this.pages = request.getPages();
        this.amount = request.getAmount();
        this.lendCount = request.getLendCount();
    }
}
