package factory.filesystem;

import factory.ConnectionFactory;
import factory.Reader;
import factory.Writer;


public class FileSystemConnectionFactory implements ConnectionFactory {
    @Override
    public Reader getReader() {
        return new FileSystemReader();
    }

    @Override
    public Writer getWriter(){
        return new FileSystemWriter();
    }
}
