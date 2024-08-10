
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;

/* CalculatorImplementation is a remote object that implements the Calculator interface.
 * It provides methods to manipulate a stack and perform operations on the stack's elements.
 */
public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private Stack<Integer> stack; // Stack to hold integer values

    /* Constructs a CalculatorImplementation instance and initializes the stack.
     * @throws RemoteException if a communication-related exception occurs during the creation of the remote object.
     */
    protected CalculatorImplementation() throws RemoteException {
        stack = new Stack<>();
    }

    /* Pushes an integer value onto the stack.
     * @param val the integer value to be pushed onto the stack.
     * @throws RemoteException if a communication-related exception occurs during the remote method call.
     */
    @Override
    public synchronized void pushValue(int val) throws RemoteException {
        stack.push(val);
        System.out.println("Pushed value: " + val);
    }

    /* Performs an operation on all values currently in the stack and pushes the result back onto the stack.
     * Operations include "min", "max", "lcm", and "gcd".
     * @param operator the operation to be performed on the stack's values.
     * @throws RemoteException if a communication-related exception occurs during the remote method call.
     */
    @Override
    public synchronized void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty, cannot perform operation.");
            return; // Early return to avoid further processing
        }

        int result = stack.pop(); // Start with the top value of the stack
        while (!stack.isEmpty()) {
            int value = stack.pop(); // Pop the next value
            switch (operator) {
                case "min":
                    result = Math.min(result, value); // Compute minimum
                    break;
                case "max":
                    result = Math.max(result, value); // Compute maximum
                    break;
                case "lcm":
                    result = lcm(result, value); // Compute least common multiple
                    break;
                case "gcd":
                    result = gcd(result, value); // Compute greatest common divisor
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        stack.push(result); // Push the result of the operation back onto the stack
        System.out.println("Performed operation: " + operator + ", result: " + result);
    }

    /* Pops the top value from the stack.
     * @return the top value from the stack.
     * @throws RemoteException if the stack is empty or a communication-related exception occurs.
     */
    @Override
    public synchronized int pop() throws RemoteException {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty, cannot pop.");
            throw new RemoteException("Stack is empty, cannot pop."); // Return meaningful error message
        }
        int value = stack.pop();
        System.out.println("Popped value: " + value);
        return value;
    }

    /* Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise.
     * @throws RemoteException if a communication-related exception occurs.
     */
    @Override
    public synchronized boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }
    /* Waits for a specified number of milliseconds and then pops the top value from the stack.
     * @param millis the number of milliseconds to wait before performing the pop operation.
     * @return the top value from the stack after the delay.
     * @throws RemoteException if the stack is empty or a communication-related exception occurs.
     */
    @Override
    public synchronized int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis); // Delay execution
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (stack.isEmpty()) {
            System.out.println("Stack is empty, cannot pop after delay.");
            throw new RemoteException("Stack is empty, cannot pop."); // Return meaningful error message
        }

        return pop(); // Call pop method to get the value
    }

    /* Computes the greatest common divisor of two integers.
     * @param a the first integer.
     * @param b the second integer.
     * @return the greatest common divisor of a and b.
     */
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * Computes the least common multiple of two integers.
     * @param a the first integer.
     * @param b the second integer.
     * @return the least common multiple of a and b.
     */
    private int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }
}