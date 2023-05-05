import java.sql.SQLException;

public class TestApp {

    public static void main(String[] args) throws SQLException {
        Data.getClients();
        Data.addClients(3, "Vasev3", "Vasya3", "Vasevich", 10000.0f);
        Data.getClients();
        Data.modClientsBalance(4L, 20000F);
        Data.getClients();
    }
}
