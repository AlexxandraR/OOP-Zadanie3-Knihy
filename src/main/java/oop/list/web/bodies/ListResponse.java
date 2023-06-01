package oop.list.web.bodies;

import lombok.Getter;
import lombok.Setter;
import oop.list.data.List;

import java.util.stream.Collectors;

@Getter
@Setter
public class ListResponse {
    private long id;

    private java.util.List<EntryList> lendingList;

    private boolean lended;

    public ListResponse(List list) {
        this.id = list.getId();
        this.lendingList = list.getLendingList().stream().map(EntryList :: new).collect(Collectors.toList());
        this.lended = list.isLended();
    }
}
