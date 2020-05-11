package me.jko.testcompletablefuture;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {
    private Map<Long, Book> books = new HashMap<>();

    Long getBookPrice(Long id) {
        return books.get(id).getPrice();
    }

    void setBook(Long id, Book book) {
        books.put(id, book);
    }
}
