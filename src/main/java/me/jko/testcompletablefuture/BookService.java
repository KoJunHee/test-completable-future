package me.jko.testcompletablefuture;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private Executor executor = Executors.newFixedThreadPool(10);

    Long getPriceBySync(Long id) {
        return bookRepository.getBookPrice(id);
    }

    CompletableFuture<Long> getPriceByAsync(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("async thread works, takes 5 secs");

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return bookRepository.getBookPrice(id);
        }, executor);
    }


    void setBook(Long id, Book book) {
        bookRepository.setBook(id, book);
    }
}
