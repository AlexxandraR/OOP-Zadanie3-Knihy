package oop.list.logic;


import oop.exception.IllegalOperationException;
import oop.exception.NotFoundException;
import oop.list.data.List;
import oop.list.web.bodies.EntryList;

public interface IListService {
    java.util.List<List> getAll();
    List create();
    List getById(long id) throws NotFoundException;
    void delete(long id) throws NotFoundException;
    List addToList(long id, EntryList body) throws NotFoundException, IllegalOperationException;
    List removeFromList(long id, EntryList body) throws NotFoundException, IllegalOperationException;
    void lend(long id) throws NotFoundException, IllegalOperationException;
}
