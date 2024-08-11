
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;


public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private Stack<Integer> stack; // Stack to hold integer values

    protected CalculatorImplementation() throws RemoteException {
        stack = new Stack<>();
    }

    
    @Override
    public synchronized void pushValue(int val) throws RemoteException {
        stack.push(val);
        System.out.println("Pushed value: " + val);
    }

    
    @Override
    public synchronized void pushOperation(String operator) throws RemoteException {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty, cannot perform operation.");
            return; 
        }

        int result = stack.pop(); 
        while (!stack.isEmpty()) {
            int value = stack.pop(); 
            switch (operator) {
                case "min":
                    result = Math.min(result, value); 
                    break;
                case "max":
                    result = Math.max(result, value);
                    break;
                case "lcm":
                    result = lcm(result, value); 
                    break;
                case "gcd":
                    result = gcd(result, value); 
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operator: " + operator);
            }
        }
        stack.push(result); 
        System.out.println("Performed operation: " + operator + ", result: " + result);
    }

    
    @Override
    public synchronized int pop() throws RemoteException {
        if (stack.isEmpty()) {
            System.out.println("Stack is empty, cannot pop.");
            throw new RemoteException("Stack is empty, cannot pop."); 
        }
        int value = stack.pop();
        System.out.println("Popped value: " + value);
        return value;
    }


    @Override
    public synchronized boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    @Override
    public synchronized int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (stack.isEmpty()) {
            System.out.println("Stack is empty, cannot pop after delay.");
            throw new RemoteException("Stack is empty, cannot pop."); 
        }

        return pop(); 
    }

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