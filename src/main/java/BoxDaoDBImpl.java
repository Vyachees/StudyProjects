import java.sql.*;


public class BoxDaoDBImpl implements BoxDao {

    @Override
    public void add(Box box) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into boxes values(?,?);")
        ) {
            statement.setLong(1, box.getId());
            statement.setString(2, box.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to database", e);
        }
    }

    @Override
    public Box get(Long id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("select id,name from boxes where id=?")
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Box(
                        resultSet.getLong("id")
                        , resultSet.getString("name")
                );
            } else {
                throw new SQLException("Box not found by id " + id);
            }
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to database ", e);
        }

    }


    @Override
    public void update(Long id, String newName) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("update boxes set name=? WHERE ID=?;")
        ) {
            statement.setString(1, newName);
            statement.setLong(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to database", e);
        }
    }

    @Override
    public void delete(String delName) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from boxes where name=?;")
        ) {
            statement.setString(1, delName);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to database", e);
        }
    }

    private Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    public void recreateBoxes() throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("DROP TABLE IF EXISTS boxes;"
                     +
                     "create table boxes (id bigint PRIMARY KEY, name varchar(255))"
             )
        ) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Cannot connect to database", e);
        }
    }
}
