import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class BoxDaoDBImplTest {
    BoxDaoDBImpl boxDaoDB;
    Box box1;
    Box box2;


    @BeforeEach
    void init() throws SQLException {
        boxDaoDB = new BoxDaoDBImpl();
        boxDaoDB.recreateBoxes();
        box1 = new Box(1L, "Box1");
        box2 = new Box(2L, "Box2");
        boxDaoDB.add(box1);
    }

    @Test
    void get() throws SQLException {
        Assertions.assertEquals("Box1", boxDaoDB.get(1L).getName());
    }

    @Test
    void add() throws SQLException {
        boxDaoDB.add(box2);
        Assertions.assertEquals("Box2", boxDaoDB.get(2L).getName());
    }


    @Test
    void update() throws SQLException {
        boxDaoDB.update(1L, "NewBox1");
        Assertions.assertEquals("NewBox1", boxDaoDB.get(1L).getName());
    }


    @Test
    void delete() throws SQLException {
        boxDaoDB.delete("Box1");
        Assertions.assertThrows(SQLException.class, () -> boxDaoDB.get(1L));
    }
}
