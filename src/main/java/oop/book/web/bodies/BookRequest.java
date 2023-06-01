package oop.book.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequest {
    private String name;

    private String description;

    private long author;

    private int pages;

    private int amount;

    private int lendCount;
}
