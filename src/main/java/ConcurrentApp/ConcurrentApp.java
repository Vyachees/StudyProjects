package ConcurrentApp;

import Books.BookDescription;
import Books.BooksManagerImpl;
import Books.Client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ConcurrentApp {
    static BooksManagerImpl booksManager = new BooksManagerImpl();
    static ExecutorService executor = Executors.newFixedThreadPool(4);
    ThreadPoolExecutor pool = (ThreadPoolExecutor) executor;
    private static Client client1 = new Client("Петр", "Михайлов", "320804506");
    private static Client client2 = new Client("Васисуалий", "Лоханкин", "987344234");

    private static BookDescription book1 = new BookDescription("The Hitchhiker's guide to the Galaxy", "Douglas Adams", 1979);
    private static BookDescription book2 = new BookDescription("Core Java. Volume I - Fundamentals", "Horstmann C.S.", 2019);

    public static void main(String[] args) throws InterruptedException {
        booksManager.addBook(book1);
        booksManager.addBook(book2);

        executor.execute(new BooksRentingLoop(booksManager, client1,book1));
        executor.execute(new BooksRentingLoop(booksManager, client2,book2));

        executor.execute(new BooksReturningLoop(booksManager,client1,book1));
        executor.execute(new BooksReturningLoop(booksManager,client2,book2));
        executor.shutdown();



    }

}
