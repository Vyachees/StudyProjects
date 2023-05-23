package checkParts;

import java.io.IOException;

public interface HeaderService {
    void add(Header header) throws IOException;

    Header read(int idHeader);

    void delete(int idHeader) throws IOException;
}
