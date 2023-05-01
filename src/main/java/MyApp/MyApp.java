package MyApp;

public class MyApp {

    public static void main(String[] args) {

    }

    public static boolean isBalanced(String input) {
        final int length = input.length();

        if (length > 0) {
            final char[] stack = new char[length];
            int stackPos = 0;

            final char[] inputChars = input.toCharArray();
            for (int i = 0; i < length; i++) {
                final char currentChar = inputChars[i];
                switch (currentChar) {
                    case '[':
                    case '(':
                        stack[stackPos++] = currentChar;
                        break;
                    case ']':
                        if (stackPos <= 0 || stack[--stackPos] != '[') {
                            return false;
                        }
                        break;
                    case ')':
                        if (stackPos <= 0 || stack[--stackPos] != '(') {
                            return false;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        return true;
    }

}
