package factory;

import factory.database.DatabaseReader;
import factory.database.DatabaseWriter;
import factory.filesystem.FileSystemReader;
import factory.filesystem.FileSystemWriter;


public class ConnectionFactoryImpl implements ConnectionFactory {
    Reader reader = null;
    Writer writer = null;
    @Override
    public Reader getReader(ConnectionType connectionType) {
        switch (connectionType){
            case File:
                reader= new FileSystemReader();
                break;
            case Database:
                reader= new DatabaseReader();
                break;
        }
        return reader;
    }

    @Override
    public Writer getWriter(ConnectionType connectionType){

        switch (connectionType){
            case File:
                writer= new FileSystemWriter();
                break;
            case Database:
                writer= new DatabaseWriter();
                break;
        }
        return writer;
        }


}
