package checkParts;

import java.io.IOException;
import java.util.List;

public interface PositionService {
    void add(Position position) throws IOException;

    List<Position> read(int idHeader);

    void delete(int idHeader, int idProduct) throws IOException;
}
