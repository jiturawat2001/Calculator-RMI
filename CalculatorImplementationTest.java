import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorImplementationTest {
    private CalculatorImplementation calculator;

    @BeforeEach
    void setUp() throws RemoteException {
        calculator = new CalculatorImplementation();
    }

    @Test
    void testPushValue() throws RemoteException {
        calculator.pushValue(10);
        assertFalse(calculator.isEmpty());
        assertEquals(10, calculator.pop());
    }

    @Test
    void testPushOperationMin() throws RemoteException {
        calculator.pushValue(10);
        calculator.pushValue(5);
        calculator.pushOperation("min");
        assertEquals(5, calculator.pop());
    }

    @Test
    void testPushOperationMax() throws RemoteException {
        calculator.pushValue(10);
        calculator.pushValue(5);
        calculator.pushOperation("max");
        assertEquals(10, calculator.pop());
    }

    @Test
    void testPushOperationLcm() throws RemoteException {
        calculator.pushValue(12);
        calculator.pushValue(15);
        calculator.pushOperation("lcm");
        assertEquals(60, calculator.pop());
    }

    @Test
    void testPushOperationGcd() throws RemoteException {
        calculator.pushValue(12);
        calculator.pushValue(15);
        calculator.pushOperation("gcd");
        assertEquals(3, calculator.pop());
    }

    @Test
    void testPop() throws RemoteException {
        calculator.pushValue(20);
        assertEquals(20, calculator.pop());
        assertTrue(calculator.isEmpty());
    }

    @Test
    void testDelayPop() throws RemoteException, InterruptedException {
        calculator.pushValue(25);
        int result = calculator.delayPop(1000); // wait for 1 second before pop
        assertEquals(25, result);
        assertTrue(calculator.isEmpty());
    }

    @Test
    void testIsEmpty() throws RemoteException {
        assertTrue(calculator.isEmpty());
        calculator.pushValue(30);
        assertFalse(calculator.isEmpty());
    }
}
