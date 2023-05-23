package FacadeTest;

import Facade.CheckFacadeImpl;
import checkParts.Header;
import checkParts.HeaderServiceImpl;
import checkParts.Position;
import checkParts.PositionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class CheckFacadeImplTest {
    HeaderServiceImpl headerService;
    PositionServiceImpl positionService;
    CheckFacadeImpl checkFacade;

    Header header1;
    Header header2;
    Header header3;

    Position position1;
    Position position2;
    Position position3;

    @BeforeEach
    void setUp() throws IOException {
        headerService = new HeaderServiceImpl();
        positionService = new PositionServiceImpl();
        checkFacade = new CheckFacadeImpl(headerService, positionService);
        checkFacade.recreate();

        header1 = new Header(1, "2023-05-22 10:00:00", 100, 2);
        header2 = new Header(2, "2023-05-22 11:00:00", 100, 2);
        header3 = new Header(3, "2023-05-22 12:00:00", 100, 2);

        position1 = new Position(1, 111, 1, 10);
        position2 = new Position(2, 222, 2, 20);
        position3 = new Position(1, 333, 3, 30);
        checkFacade.add(header1, position1);
        checkFacade.add(header1, position3);
    }

    @Test
    void add() throws IOException {
        checkFacade.add(header2, position2);
        Assertions.assertEquals("idHeader;dateTime;storeNum;cashBox;totalSum\n" +
                "2;2023-05-22 11:00:00;100;2;40\n" +
                "idHeader;idProduct;count;pricePerId;priceTotalPerId\n" +
                "2;222;2;20;40", checkFacade.read(2));
    }

    @Test
    void read() {
        Assertions.assertEquals("idHeader;dateTime;storeNum;cashBox;totalSum\n" +
                "1;2023-05-22 10:00:00;100;2;100\n" +
                "idHeader;idProduct;count;pricePerId;priceTotalPerId\n" +
                "1;111;1;10;10\n" +
                "1;333;3;30;90", checkFacade.read(1));
    }


    @Test
    void delete() throws IOException {
        checkFacade.delete(1, 111);
        Assertions.assertEquals("idHeader;dateTime;storeNum;cashBox;totalSum\n" +
                "1;2023-05-22 10:00:00;100;2;90\n" +
                "idHeader;idProduct;count;pricePerId;priceTotalPerId\n" +
                "1;333;3;30;90", checkFacade.read(1));
    }
}
