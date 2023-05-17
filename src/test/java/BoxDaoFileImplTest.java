import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


class BoxDaoFileImplTest {

    BoxDaoFileImpl boxDaoFile;
    Box box1;
    Box box2;
    Box box3;

    @BeforeEach
    void setUp() throws IOException {
        boxDaoFile = new BoxDaoFileImpl();
        boxDaoFile.recreateBoxes();
        box1 = new Box(1L, "Box1");
        box2 = new Box(2L, "Box2");
        box3 = new Box(3L, "Box3");
        boxDaoFile.add(box1);
        boxDaoFile.add(box3);
    }

    @Test
    void add() throws IOException {
        boxDaoFile.add(box2);
        Assertions.assertEquals("Box2", boxDaoFile.get(2L).getName());
    }

    @Test
    void get() {
        Assertions.assertEquals("Box1", boxDaoFile.get(1L).getName());
    }

    @Test
    void update() throws IOException {
        boxDaoFile.update(1L, "Box11");
        Assertions.assertEquals("Box11", boxDaoFile.get(1L).getName());
    }

    @Test
    void delete() throws IOException {
        boxDaoFile.delete("Box1");
        Assertions.assertThrows(NullPointerException.class, () -> boxDaoFile.get(1L).getName());
    }
}
