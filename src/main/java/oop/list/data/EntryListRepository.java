package oop.list.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryListRepository extends JpaRepository<EntryList, Long> {
    EntryList findEntryListById(Long id);
}
