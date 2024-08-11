import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorClientTest {

    private static Calculator calculator;

    @BeforeAll
    static void setUp() throws Exception {
        LocateRegistry.createRegistry(1099);
        CalculatorImplementation calculatorImplementation = new CalculatorImplementation();
        Naming.rebind("CalculatorService", calculatorImplementation);
        calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
    }

    @Test
    void testClientServerInteraction() throws Exception {
        calculator.pushValue(50);
        calculator.pushValue(25);
        calculator.pushOperation("gcd");
        assertEquals(25, calculator.pop());
    }
}
