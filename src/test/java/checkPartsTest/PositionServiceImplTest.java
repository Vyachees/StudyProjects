package checkPartsTest;

import checkParts.Position;
import checkParts.PositionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PositionServiceImplTest {

    PositionServiceImpl positionService;
    Position position1;
    Position position2;
    Position position3;

    //(int idHeader, int idProduct, int count, int pricePerId)
    @BeforeEach
    void setUp() throws IOException {
        positionService = new PositionServiceImpl();
        positionService.recreate();
        position1 = new Position(1, 111, 1, 10);
        position2 = new Position(2, 222, 2, 20);
        position3 = new Position(1, 333, 3, 30);
        positionService.add(position1);
        positionService.add(position3);
    }

    @Test
    void add() throws IOException {
        positionService.add(position2);
        Assertions.assertEquals("[2;222;2;20]", positionService.read(2).toString());
    }

    @Test
    void read() {
        Assertions.assertEquals("[1;111;1;10, 1;333;3;30]", positionService.read(1).toString());
    }

    @Test
    void delete() throws IOException {
        positionService.delete(1, 111);
        Assertions.assertEquals("[1;333;3;30]", positionService.read(1).toString());
    }
}
