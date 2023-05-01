package MyApp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyAppTest {


    @Test
    void isBalancedSimpleCheck() {
        assertFalse(MyApp.isBalanced(""));
        assertTrue(MyApp.isBalanced("[]"));
        assertTrue(MyApp.isBalanced("()"));
    }

    @Test
    void  isBalancedMediumCheck(){
        assertFalse(MyApp.isBalanced(")("));
        assertFalse(MyApp.isBalanced("]["));
        assertFalse(MyApp.isBalanced("((())"));
        assertFalse(MyApp.isBalanced("(()))"));
        assertFalse(MyApp.isBalanced("[[[]]"));
        assertFalse(MyApp.isBalanced("[[]]]"));
        assertTrue(MyApp.isBalanced("((((())))[[[[[]]]]fghjdsgdsjhgdshbjgsdjgdshjgdshgjsdhgsdhgsdfhg364])"));
    }

}
