package tests;

import classes.Calculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    @Nested
    class CommonTest{
        @ParameterizedTest(name = "{0} * {1} should equal {2}")
        @CsvSource({
                // Standard positive cases
                "4, 5, 20",

                // Negative and positive combinations
                "-4, 5, -20",
                "4, -5, -20",

                // Negative and negative combinations
                "-4, -5, 20",

                // Zero edge cases
                "0, 5, 0",
                "5, 0, 0",
                "0, 0, 0",
                "-5, 0, 0",

                // Identity element (One) edge cases
                "1, 7, 7",
                "7, 1, 7",
                "-1, 7, -7",
                "1, -7, -7",
                "-1, -7, 7",

                // Boundary values (Integer limits)
                "2147483647, 1, 2147483647",   // Integer.MAX_VALUE * 1
                "-21483648, 1, -21483648"     // Integer.MIN_VALUE * 1
        })
        @DisplayName("Test all cases of Multiply")
        void testMultiplyEdgeCases(int a, int b, int expected) {
            int actual = Calculator.multiplyTwoNumber(a, b);
            assertEquals(expected, actual, () -> String.format("Failed for: %d * %d", a, b));
        }
        @Test
        @DisplayName("Test Integer Overflow Behavior")
        void testMultiplyOverflow() {
            // Standard Java integer multiplication overflows silently (wraps around)
            int result = Calculator.multiplyTwoNumber(Integer.MAX_VALUE, 2);
            assertEquals(-2, result, "Integer overflow should wrap around predictably in standard arithmetic");
        }



        // tests cases for the remainder call

        @ParameterizedTest(name = "getRemainderOfTwoNumbers({0}, {1}) should return {2}")
        @CsvSource({
                // Standard positive remainder cases
                "5, 2, 1",
                "10, 3, 1",

                // Base case where divisor > dividend (b > a)
                "3, 5, 3",

                // Equal numbers (remainder 0)
                "5, 5, 0",

                // Mixed sign combinations
                "-5, 2, 1",
                "5, -2, 1",

                // Both negative combinations
                "-5, -2, 1"
        })
        void testGetRemainderOfTwoNumbers(int a, int b, int expected) {
            int actual = Calculator.getRemainderOfTwoNumbers(a, b);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test Zero and Divisor-Zero Infinite Recursion Edge Cases")
        void testZeroEdgeCases() {
            // Passing 0 and 0 triggers infinite mutual recursion leading to StackOverflowError
            assertThrows(StackOverflowError.class, () -> {
                Calculator.getRemainderOfTwoNumbers(0, 0);
            });

            // Passing b = 0 with a non-zero 'a' also results in infinite recursion in this logic flow
            assertThrows(StackOverflowError.class, () -> {
                Calculator.getRemainderOfTwoNumbers(5, 0);
            });
        }


        @ParameterizedTest(name = "divideTwoNumbers({0}, {1}) should return {2}")
        @CsvSource({
                // Standard positive division
                "6, 2, 3",
                "10, 3, 3", // Truncates decimal part

                // Negative and positive combinations
                "-6, 2, -3",
                "6, -2, -3",

                // Negative and negative combinations
                "-6, -2, 3",

                // Zero dividend
                "0, 5, 0",
                "0, -5, 0"
        })
        void testDivideTwoNumbersStandard(int a, int b, int expected) {
            int actual = Calculator.divideTwoNumbers(a, b);
            assertEquals(expected, actual, () -> String.format("Failed for: %d / %d", a, b));
        }

        @Test
        @DisplayName("Test Division by Zero Throws ArithmeticException")
        void testDivideByZero() {
            assertThrows(ArithmeticException.class, () -> {
                Calculator.divideTwoNumbers(5, 0);
            });

            assertThrows(ArithmeticException.class, () -> {
                Calculator.divideTwoNumbers(-5, 0);
            });

            assertThrows(ArithmeticException.class, () -> {
                Calculator.divideTwoNumbers(0, 0);
            });
        }

    }

}
