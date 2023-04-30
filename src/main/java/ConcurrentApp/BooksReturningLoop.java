package ConcurrentApp;

import Books.BookDescription;
import Books.BooksManagerImpl;
import Books.Client;

public class BooksReturningLoop implements Runnable {

    BooksManagerImpl booksManager;
    Client client;
    BookDescription book;

    BooksReturningLoop(BooksManagerImpl booksManager, Client client, BookDescription book){
        this.booksManager=booksManager;
        this.client=client;
        this.book=book;
    }

    @Override
    public synchronized void run() {
        int i =0;
        while(i<10){
            try {
                Thread.sleep(500);
                booksManager.returnBook(client,book);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("im returning books! With "+Thread.currentThread().getName());
            i++;
        }


    }
}
