package me.jko.testcompletablefuture;

class Book {

    Book(String name, Long price) {
        this.name = name;
        this.price = price;
    }

    private String name;
    private Long price;


    Long getPrice() {
        return price;
    }
}
