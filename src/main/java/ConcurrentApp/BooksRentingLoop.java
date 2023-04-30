package ConcurrentApp;

import Books.BookDescription;
import Books.BooksManagerImpl;
import Books.Client;

public class BooksRentingLoop implements Runnable{

    BooksManagerImpl booksManager;
    Client client;
    BookDescription book;

    BooksRentingLoop(BooksManagerImpl booksManager, Client client, BookDescription book){
        this.booksManager=booksManager;
        this.client=client;
        this.book=book;
    }



    @Override
    public synchronized void run() {
        int i =0;
        while(i<10){

            try {
                Thread.sleep(1000);
                booksManager.rentBook(client,book);
                System.out.println(booksManager.getRentedBooks());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("im renting books! With "+Thread.currentThread().getName());
            i++;
        }
    }
}
