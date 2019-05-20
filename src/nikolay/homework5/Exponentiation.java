package nikolay.homework5;

public class Exponentiation {
    public static int exponentiation(int number, int exponent, boolean throughRecursion) {
        return throughRecursion? recursion(number, exponent) : cycle(number, exponent);
    }

    private static int recursion(int number, int exponent) {
        if (exponent == 0) {
            return 1;
        }
        return (number * recursion(number, exponent - 1));
    }

    private static int cycle(int number, int exponent) {
        int result = number;
        int cursor = 0;
        while (cursor < exponent - 1) {
            result *= number;
            cursor++;
        }
        return result;
    }
}
