package MyApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyAppTest {
    private MyApp myApp;

    @BeforeEach
    void init() {
        myApp = new MyApp();
    }


    @Test
    void arrayAfterZeroTest() {
        int[] goodArr = new int[]{1, 2, 3, 0, 1, 2};
        int[] goodArr1 = new int[]{1, 2, 3, 0};
        int[] badArr = new int[]{1, 2, 3};
        int[] emptyArr = new int[]{};
        assertArrayEquals(new int[]{1, 2}, myApp.arrayAfterZero(goodArr));
        assertArrayEquals(new int[]{}, myApp.arrayAfterZero(goodArr1));
        assertThrowsExactly(RuntimeException.class, () -> myApp.arrayAfterZero(badArr));
        assertThrowsExactly(RuntimeException.class, () -> myApp.arrayAfterZero(emptyArr));
    }

    @Test
    void sixNinesTest() {
        int[] goodArr = new int[]{66, 99, 66, 99};
        int[] badArr = new int[]{66, 99, 1};
        int[] emptyArr = new int[]{};
        int[] only66Arr = new int[]{66, 66};
        int[] only99Arr = new int[]{66, 66};
        assertTrue(myApp.sixNines(goodArr));
        assertFalse(myApp.sixNines(badArr));
        assertFalse(myApp.sixNines(emptyArr));
        assertFalse(myApp.sixNines(only66Arr));
        assertFalse(myApp.sixNines(only99Arr));
    }


}
