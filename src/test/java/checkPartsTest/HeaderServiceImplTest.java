package checkPartsTest;

import checkParts.Header;
import checkParts.HeaderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HeaderServiceImplTest {
    HeaderServiceImpl headerService;
    Header header1;
    Header header2;
    Header header3;

    @BeforeEach
    void setUp() throws IOException {
        headerService = new HeaderServiceImpl();
        headerService.recreate();
        header1 = new Header(1, "2023-05-22 10:00:00", 100, 2);
        header2 = new Header(2, "2023-05-22 11:00:00", 100, 2);
        header3 = new Header(3, "2023-05-22 12:00:00", 100, 2);
        headerService.add(header1);
        headerService.add(header3);
    }

    @Test
    void add() throws IOException {
        headerService.add(header2);
        Assertions.assertEquals("2;2023-05-22 11:00:00;100;2", headerService.read(2).toString());
    }

    @Test
    void read() {
        Assertions.assertEquals("1;2023-05-22 10:00:00;100;2", headerService.read(1).toString());
    }

    @Test
    void delete() throws IOException {
        headerService.delete(1);
        Assertions.assertThrows(NullPointerException.class, () -> headerService.read(1).getIdHeader());
        //Assertions.assert("",headerService.read(1));
    }
}
