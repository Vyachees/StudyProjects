package Books;

import java.util.*;

public class BooksManagerImpl implements BooksManager {
    private final Map<BookDescription,Integer> library = new HashMap<>();
    private final Map<Client,Set<BookDescription>> clientsRentedBooks = new HashMap<>();
    private final Map<BookDescription, Set<Client>> rentedBooksClients= new HashMap<>();



    @Override
    public int addBook(BookDescription bookDescription) {
        library.put(bookDescription,library.getOrDefault(bookDescription,0)+1);
        return library.get(bookDescription);
    }

    @Override
    public int addBook(BookDescription bookDescription, int count) {
        library.put(bookDescription,library.getOrDefault(bookDescription,0)+count);
        return library.get(bookDescription);
    }

    @Override
    public int getBookAmount(BookDescription book) {
        return library.getOrDefault(book, 0);
    }

    @Override
    public List<BookDescription> getBooksByCount() {
        List<BookDescription> result= new ArrayList<>(library.keySet());
        result.sort((a,b)->library.get(b).compareTo(library.get(a)));
        return result;
    }

    @Override
    public synchronized boolean rentBook(Client client, BookDescription book) throws InterruptedException {

        while (library.containsKey(book) && library.get(book)==0){
            try {
                wait(1000);
                System.out.println("Im waiting for returning book "+book.getTitle());
                System.out.println(getRentedBooks());
            }
            catch (InterruptedException e){throw new InterruptedException(e+"");}
        }

        if (library.containsKey(book) && library.get(book) > 0){
            if (!clientsRentedBooks.containsKey(client)) {
                Set<BookDescription> rentBooksList = new HashSet<>();
                rentBooksList.add(book);
                clientsRentedBooks.put(client, rentBooksList);
                library.put(book, library.get(book) - 1);
                rentBookToRentedBooks(client,book);
                notify();
                return true;
            }
            else if ( !getRentedBooks(client).contains(book) ) {
                clientsRentedBooks.get(client).add(book);
                library.put(book, library.get(book) - 1);
                rentBookToRentedBooks(client,book);
                notify();
                return true;
            }
    }
        notify();
        return false;
    }

    public void rentBookToRentedBooks(Client client, BookDescription book){
        if(!rentedBooksClients.containsKey(book)){
            Set<Client> clientList = new HashSet<>(Collections.singletonList(client));
            rentedBooksClients.put(book,clientList);
        }
        else rentedBooksClients.get(book).add(client);
    }

    @Override
    public synchronized boolean returnBook(Client client, BookDescription book) {
        if(clientsRentedBooks.containsKey(client) && getRentedBooks(client).contains(book)){
            clientsRentedBooks.get(client).remove(book);
            rentedBooksClients.get(book).remove(client);
            library.put(book, library.get(book) + 1);
            return true;
        }
        return false;
    }

    @Override
    public Collection<BookDescription> getRentedBooks(Client client) {
        if(clientsRentedBooks.get(client)==null){
            return new HashSet<>();
        }
        else
            return new HashSet<>(clientsRentedBooks.get(client));
    }

    @Override
    public Map<BookDescription, Integer> getRentedBooks() {
        HashMap<BookDescription,Integer> result = new HashMap<>();
        library.keySet().forEach(t->result.put(t,0));
        rentedBooksClients.keySet().forEach(t->result.put(t,rentedBooksClients.get(t).size()));
        return result;
    }

    @Override
    public final Collection<Client> getBookRenters(BookDescription book) {
        return Set.copyOf(rentedBooksClients.get(book));
    }



    @Override
    public List<Client> getRentChampions() {
        ArrayList<Client> result = new ArrayList<>();
        Map<Integer,Client> treemap = new TreeMap<>(Collections.reverseOrder());
        clientsRentedBooks.keySet().forEach(t->treemap.put(clientsRentedBooks.get(t).size(),t));
        int count=0;
        for (Map.Entry<Integer,Client > entry:treemap.entrySet()) {
            result.add(entry.getValue());
           count++;
           if(count==3){
               break;
           }
        }
        return result;
    }


}
