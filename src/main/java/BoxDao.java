import java.io.IOException;
import java.sql.SQLException;

public interface BoxDao {
    void add(Box box) throws SQLException, IOException;

    Box get(Long id) throws SQLException;

    void update(Long id, String name) throws SQLException, IOException;

    void delete(String delName) throws SQLException, IOException;
}
