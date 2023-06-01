package oop.list.logic;

import oop.exception.NotFoundException;
import oop.list.data.EntryList;

public interface IEntryListService {
    EntryList create();
    EntryList getById(long id) throws NotFoundException;
    void delete(long id) throws NotFoundException;
    EntryList save(EntryList entryList);
}
