package nikolay.homework5.test;

import nikolay.homework5.Exponentiation;

public class ExponentiationTests {
    @org.junit.jupiter.api.Test
    public void expTest() {
        int number = 3;
        int exp = 5;

        boolean result = Exponentiation.exponentiation(number, exp, true)
                == Exponentiation.exponentiation(number, exp, false);

        System.out.println(Exponentiation.exponentiation(number, exp, true)
                + " == "  + Exponentiation.exponentiation(number, exp, false) + " : " + result);

        assert result;

    }


}