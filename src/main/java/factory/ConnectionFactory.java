package factory;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ConnectionFactory {
    Reader getReader() throws FileNotFoundException;
    Writer getWriter() throws IOException;
}
