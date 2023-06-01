package oop.list.data;

import lombok.*;
import oop.book.data.Book;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class EntryList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Book book;

}
