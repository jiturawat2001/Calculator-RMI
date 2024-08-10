import java.rmi.Remote;
import java.rmi.RemoteException;


// Define the remote interface for the Calculator service
public interface Calculator extends Remote{

    // Method to push a value onto the stack
    void pushValue(int val) throws RemoteException;

    // Method to push an operation onto the stack
    void pushOperation(String operator) throws RemoteException;

    // Method to pop a value from the stack
    int pop() throws RemoteException;

    // Method to check if the stack is empty
    boolean isEmpty() throws RemoteException;

    // Method to delay popping a value from the stack
    int delayPop(int millis) throws RemoteException;

}