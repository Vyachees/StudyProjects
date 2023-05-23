package Facade;

import checkParts.Header;
import checkParts.Position;

import java.io.IOException;

public interface CheckFacadeInterface {

    void add(Header header, Position position) throws IOException;

    String read(int idHeader);

    void delete(int idHeader, int idProduct) throws IOException;
}
