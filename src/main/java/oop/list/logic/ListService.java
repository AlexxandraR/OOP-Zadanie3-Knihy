package oop.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oop.book.logic.IBookService;
import oop.exception.IllegalOperationException;
import oop.exception.NotFoundException;
import oop.list.data.List;
import oop.list.data.ListRepository;
import oop.list.web.bodies.EntryList;

@Service
public class ListService implements IListService {
    @Autowired
    private ListRepository repository;

    @Autowired
    private IBookService bookService;

    @Autowired
    private IEntryListService entryListService;

    @Override
    public java.util.List<List> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List create() {
        return this.repository.save(new List());
    }

    @Override
    public List getById(long id) throws NotFoundException {
        List list = this.repository.findListById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        return list;
    }

    @Override
    public void delete(long id) throws NotFoundException {
        List list = this.repository.findListById(id);
        if(list != null) {
            this.increaseLendCount(list, -1);
        }
        this.repository.delete(this.getById(id));
    }

    @Override
    public List addToList(long id, EntryList body) throws NotFoundException, IllegalOperationException {
        List list = this.getUnlended(id);
        var existingEntry = this.findEntryListWithBook(list.getLendingList(), body.getId());
        if (existingEntry == null) {
            oop.list.data.EntryList entryList = entryListService.create();
            entryList.setBook(bookService.getById(body.getId()));
            list.getLendingList().add(entryListService.save(entryList));
        } else {
            throw new IllegalOperationException();
        }
        return this.repository.save(list);
    }

    @Override
    public List removeFromList(long id, EntryList body) throws NotFoundException, IllegalOperationException{
        List list = this.getUnlended(id);
        var existingEntry = this.findEntryListWithBook(list.getLendingList(), body.getId());
        if (existingEntry == null) {
            throw new NotFoundException();
        }
        else{
            list.getLendingList().remove(existingEntry);
        }
        return this.repository.save(list);
    }

    @Override
    public void lend(long id) throws NotFoundException, IllegalOperationException {
        List list = this.repository.findListById(id);
        if (list == null) {
            throw new NotFoundException();
        }
        if(list.isLended()){
            throw new IllegalOperationException();
        }
        else{
            list.setLended(true);
            this.increaseLendCount(list, 1);
            this.repository.save(list);
        }
    }

    private void increaseLendCount(List list, int increment){
        for(var book : list.getLendingList()){
            book.getBook().setLendCount(book.getBook().getLendCount() + increment);
        }
    }

    private List getUnlended(long id) throws NotFoundException, IllegalOperationException {
        List list = this.getById(id);
        if (list.isLended()) {
            throw new IllegalOperationException();
        }
        return list;
    }

    private oop.list.data.EntryList findEntryListWithBook(java.util.List<oop.list.data.EntryList> entries, long bookId) {
        for (var entry : entries) {
            if (entry.getBook().getId().equals(bookId)) {
                return entry;
            }
        }
        return null;
    }

}
