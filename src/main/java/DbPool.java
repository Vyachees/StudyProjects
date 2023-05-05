import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DbPool {
/*
* Что нужно сделать:
Написать на java программу для подключения к базе данных H2,
используя данные (заполненные таблицы) из уроков 3 модуля
Например: перебрать всех клиентов в таблице клиент

Что должно получиться в результате:
Подключение и установка соединения с H2
Вывод на экран содержимого в таблице client
добавление/изменение записи в таблице client
фиксирование изменений в транзакции
*
* Дополнительные материалы:
Установка соединения и работа с БД через connection pool
* */

    private static final HikariConfig config = new HikariConfig();
    private static final HikariDataSource ds;

    static {
        config.setJdbcUrl( "jdbc:h2:tcp://localhost/~/test" );
        config.setUsername( "sa" );
        config.setPassword( "" );
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
        ds = new HikariDataSource( config );
    }

    private void DataSource() {}

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
