package oop.book.web.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookUpdateRequest {
    private String name;

    private String description;

    private Long author;

    private int pages;
}
