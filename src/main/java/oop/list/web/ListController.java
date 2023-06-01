package oop.list.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oop.exception.IllegalOperationException;
import oop.exception.NotFoundException;
import oop.list.logic.IListService;
import oop.list.web.bodies.EntryList;
import oop.list.web.bodies.ListResponse;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/list")
public class ListController {
    @Autowired
    private IListService service;

    @GetMapping()
    public java.util.List<ListResponse> getAllLists(){
        return this.service.getAll().stream().map(ListResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ListResponse> addList() {
        return new ResponseEntity<>(new ListResponse(this.service.create()), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ListResponse getList(@PathVariable("id") long listId) throws NotFoundException {
        return new ListResponse(this.service.getById(listId));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteList(@PathVariable("id") long listId) throws NotFoundException {
        this.service.delete(listId);
    }

    @PostMapping(value = "/{id}/add")
    public ListResponse addToList(@PathVariable("id") Long listId, @RequestBody EntryList entryList) throws NotFoundException, IllegalOperationException {
        return new ListResponse(this.service.addToList(listId, entryList));
    }

    @DeleteMapping(value = "/{id}/remove")
    public ListResponse removeFromList(@PathVariable("id") Long listId, @RequestBody EntryList entryList) throws NotFoundException, IllegalOperationException {
        return new ListResponse(this.service.removeFromList(listId, entryList));
    }

    @GetMapping(value = "/{id}/lend")
    public void lendList(@PathVariable("id") long listId) throws NotFoundException, IllegalOperationException {
        this.service.lend(listId);
    }

}
