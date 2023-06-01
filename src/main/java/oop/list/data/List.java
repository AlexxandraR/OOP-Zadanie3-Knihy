package oop.list.data;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class List {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(orphanRemoval = true)
    private java.util.List<EntryList> lendingList;

    private boolean lended;

    public List() {
        this.lendingList = new ArrayList<>();
    }
}
