package oop.list.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oop.exception.NotFoundException;
import oop.list.data.EntryList;
import oop.list.data.EntryListRepository;

@Service
public class EntryListService implements IEntryListService{
    @Autowired
    EntryListRepository repository;

    @Override
    public EntryList create(){
        return this.repository.save(new EntryList());
    }

    @Override
    public EntryList getById(long id) throws NotFoundException {
        EntryList entryList = this.repository.findEntryListById(id);
        if (entryList == null)
            throw new NotFoundException();
        return entryList;
    }

    @Override
    public void delete(long id) throws NotFoundException{
        this.repository.delete(this.getById(id));
    }

    @Override
    public EntryList save(EntryList entryList){
        return this.repository.save(entryList);
    }

}
