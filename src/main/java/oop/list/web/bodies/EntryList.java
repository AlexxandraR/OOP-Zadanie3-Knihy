package oop.list.web.bodies;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class EntryList {
    //bookId
    private Long id;
    private String name;
    private String description;
    private long author;
    private int pages;
    private int amount;
    private int lendCount;

    public EntryList(oop.list.data.EntryList entryList){
        this.id = entryList.getBook().getId();
        this.name = entryList.getBook().getName();
        this.description = entryList.getBook().getDescription();
        this.author = entryList.getBook().getAuthor().getId();
        this.pages = entryList.getBook().getPages();
        this.amount = entryList.getBook().getAmount();
        this.lendCount = entryList.getBook().getLendCount();
    }
}
