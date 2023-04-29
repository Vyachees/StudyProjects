import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TestManagerImpl {

    /*private final Supplier<BooksManager> managerSupplier;

    public TestManagerImpl(Supplier<BooksManager> managerSupplier) {
        this.managerSupplier = managerSupplier;
        init();
    }*/



    private static Client client1;
    private static Client client2;
    private static Client client3;
    private static Client client4;
    private static Client client5;
    private static Client client6;

    private static BookDescription book1;
    private static BookDescription book2;
    private static BookDescription book3;
    private static BookDescription book4;
    private static BookDescription book5;
    private static BookDescription book6;


   /* public TestManagerImpl(Supplier<BooksManager> managerSupplier) {
        this.managerSupplier = managerSupplier;
        init();
    }*/
    @BeforeAll
    static void init() {
        client1 = new Client("Петр", "Михайлов", "320804506");
        client2 = new Client("Васисуалий", "Лоханкин", "987344234");
        client3 = new Client("Нечай", "Петров", "3422110233");
        client4 = new Client("Алевтина", "Ангилоки", "342342344");
        client5 = new Client("Ингеборга", "Дапкунайте", "2343242342");
        client6 = new Client("Стивен", "Спилберг", "530804306");

        book1 = new BookDescription("The Hitchhiker's guide to the Galaxy", "Douglas Adams", 1979);
        book2 = new BookDescription("Core Java. Volume I - Fundamentals", "Horstmann C.S.", 2019);
        book3 = new BookDescription("Core Java. Volume II - Advanced Features", "Horstmann C.S.", 2019);
        book4 = new BookDescription("Былое и думы", "А. И. Герцен", 1868);
        book5 = new BookDescription("Cryptonomicon", "Neal Stephenson", 1999);
        book6 = new BookDescription("В глубине великого кристалла", "Владислав Крапивин", 1989);
    }

    @Test
    public void testSingleBookAddition() {
        var manager = new BooksManagerImpl();
        assert (manager.getBookAmount(book1) == 0);
        assert (manager.addBook(book1) == 1);
        assert (manager.getBookAmount(book1) == 1);
        assert (manager.addBook(book1) == 2);
        assert (manager.getBookAmount(book1) == 2);
    }

    @Test
    public void testMultiBookAddition() {
        var manager = new BooksManagerImpl();
        assert (manager.getBookAmount(book2) == 0);
        assert (manager.addBook(book2, 3) == 3);
        assert (manager.getBookAmount(book2) == 3);
        assert (manager.addBook(book2, 2) == 5);
        assert (manager.getBookAmount(book2) == 5);
    }

    @Test
    public void testRentBook() {
        var manager = new BooksManagerImpl();
        manager.addBook(book2, 3);

        assert (manager.rentBook(client1, book2));
        assert (manager.getBookAmount(book2) == 2);
        var clientBooks = manager.getRentedBooks(client1);
        assert (clientBooks.size() == 1);
        assert (clientBooks.contains(book2));
    }

    @Test
    public void testRentBookTwice() {
        var manager = new BooksManagerImpl();
        manager.addBook(book1, 3);

        assert (manager.rentBook(client1, book1)) : "1 assert";
        assert (!manager.rentBook(client1, book1)) : "2 assert";
        var clientBooks = manager.getRentedBooks(client1);
        assert (clientBooks.size() == 1) : "3 assert";
        assert (clientBooks.contains(book1)) : "4 assert";
    }

    @Test
    public void testRentAbsentBook() {
        var manager = new BooksManagerImpl();
        assert (!manager.rentBook(client1, book1));
    }

    @Test
    public void testRentAbsentBook2() {
        var manager = new BooksManagerImpl();
        manager.addBook(book3);
        assert (manager.rentBook(client3, book3));
        assert (!manager.rentBook(client1, book3));
    }

    @Test
    public void testReturnBook() {
        var manager = new BooksManagerImpl();
        assert (!manager.returnBook(client3, book2)) : "assert1";
        assert (manager.getRentedBooks(client3).size() == 0) : "assert2";
    }

    @Test
    public void testReturnBook2() {
        var manager = new BooksManagerImpl();
        manager.addBook(book3);
        manager.rentBook(client4, book3);
        assert (manager.returnBook(client4, book3));
        assert (manager.getBookAmount(book3) == 1);
        assert (manager.getRentedBooks(client4).size() == 0);
    }

    @Test
    public void testRentBooks() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);
        rentBooks(manager);

        var clientBooks = manager.getRentedBooks(client1);
        assert (clientBooks.size() == 4);
        assert (clientBooks.contains(book2));
        assert (clientBooks.contains(book3));
        assert (clientBooks.contains(book4));
        assert (clientBooks.contains(book5));

        clientBooks.add(book6);
        var clientBooks2 = manager.getRentedBooks(client1);
        assert (clientBooks2.size() == 4);
    }

    @Test
    public void testRentBooks2() {
        var manager = new BooksManagerImpl();
        manager.addBook(book1, 2);

        manager.rentBook(client1, book1);
        manager.rentBook(client2, book1);

        var renters = manager.getBookRenters(book1);
        assert (renters.size() == 2);
        assert (renters.contains(client1));
        assert (renters.contains(client2));
        boolean exceptionHappened = false;
        try {
            renters.add(client3);
        } catch (UnsupportedOperationException e) {
            exceptionHappened = true;
        }
        assert (exceptionHappened);
    }



    private void fillBooks(BooksManager manager) {
        manager.addBook(book1);
        manager.addBook(book2, 3);
        manager.addBook(book3, 5);
        manager.addBook(book4, 7);
        manager.addBook(book5, 5);
        manager.addBook(book6, 2);
    }


    private void rentBooks(BooksManager manager) {
        manager.rentBook(client1, book2);
        manager.rentBook(client1, book3);
        manager.rentBook(client1, book5);
        manager.rentBook(client1, book4);

        manager.rentBook(client2, book3);
        manager.rentBook(client2, book6);
        manager.rentBook(client2, book1);

        manager.rentBook(client3, book5);

        manager.rentBook(client4, book2);
        manager.rentBook(client4, book5);

        manager.rentBook(client5, book2);
        manager.rentBook(client5, book3);
        manager.rentBook(client5, book4);
        manager.rentBook(client5, book5);
        manager.rentBook(client5, book6);

        manager.rentBook(client6, book4);
        manager.rentBook(client6, book3);
    }

    @Test
    public void testBooksList() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);
        rentBooks(manager);
        var books = manager.getRentedBooks(client1);
        assert (books.size() == 4) : "Assert error check1";
        books.add(book6);
        assert (manager.getRentedBooks(client1).size() == 4) : "Exptected size 4, actual size is " + manager.getRentedBooks(client1).size();
    }

    @Test
    public void testBooksRentAll() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);
        rentBooks(manager);

        assert (manager.getBookAmount(book1) == 0);
        assert (manager.getBookAmount(book2) == 0);
        assert (manager.getBookAmount(book3) == 1);
        assert (manager.getBookAmount(book4) == 4);
        assert (manager.getBookAmount(book5) == 1);
        assert (manager.getBookAmount(book6) == 0);
    }

    @Test
    public void testRentChampions() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);
        rentBooks(manager);

        List<Client> rentChampions = manager.getRentChampions();
        assert (rentChampions.size() == 3);
        assert rentChampions.get(0).equals(client5);
        assert rentChampions.get(1).equals(client1);
        assert rentChampions.get(2).equals(client2);
    }

    @Test
    public void testRentChampions2() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);

        manager.rentBook(client2, book1);
        manager.rentBook(client2, book2);
        manager.rentBook(client1, book3);

        List<Client> rentChampions = manager.getRentChampions();
        assert (rentChampions.size() == 2);
        assert rentChampions.get(0).equals(client2);
        assert rentChampions.get(1).equals(client1);
    }

    @Test
    public void testRentBooksByCount() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);
        rentBooks(manager);
        List<BookDescription> booksByCount = manager.getBooksByCount();
        assert (booksByCount.size() == 6);
        assert (booksByCount.get(0).equals(book4));
    }

    @Test
    public void testRentedBooksCount() {
        var manager = new BooksManagerImpl();
        fillBooks(manager);
        rentBooks(manager);

        var rentedBooks = manager.getRentedBooks();
        assert (rentedBooks.size() == 6);
        assert (rentedBooks.get(book1) == 1);
        assert (rentedBooks.get(book2) == 3);
        assert (rentedBooks.get(book3) == 4);
        assert (rentedBooks.get(book4) == 3);
        assert (rentedBooks.get(book5) == 4);
        assert (rentedBooks.get(book6) == 2);
    }

    @Test
    public void testRentedBooksCount2() {
        var manager = new BooksManagerImpl();
        manager.addBook(book1);
        manager.addBook(book2);
        var rentedBooks = manager.getRentedBooks();
        assert (rentedBooks.size() == 2);
        assert (rentedBooks.get(book1) == 0);
        assert (rentedBooks.get(book2) == 0);
    }


}
