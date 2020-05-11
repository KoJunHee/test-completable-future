package me.jko.testcompletablefuture;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BookServiceTest {
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(new BookRepository());

        bookService.setBook(1L, new Book("Java", 10000L));
        bookService.setBook(2L, new Book("Spring", 20000L));
        bookService.setBook(3L, new Book("Book", 30000L));
    }

    @Test
    void getPriceBySync() {
        Long price = bookService.getPriceBySync(1L);

        assertEquals(10000L, price);
    }

    @Test
    void getPriceByAsync() {
        log.info("client will call a async method");
        CompletableFuture<Long> future = bookService.getPriceByAsync(1L);
        log.info("non-blocking client");

        Long price = future.join();

        assertEquals(10000L, price);
    }

    @Test
    void getPriceByAsyncWithThenAccept() {
        log.info("client will call a async method");
        CompletableFuture<Void> future = bookService
            .getPriceByAsync(1L)
            .thenAccept(price -> {
                log.info("async thread ended");
                assertEquals(10000L, price);
            });

        log.info("non-blocking client will do something");

        // async thead 결과를 보기 위해서, main thread stop
        assertNull(future.join());
    }
}
