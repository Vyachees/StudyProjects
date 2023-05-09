package factory;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ConnectionFactory {
    Reader getReader(ConnectionType connectionType) throws FileNotFoundException;
    Writer getWriter(ConnectionType connectionType) throws IOException;
}
