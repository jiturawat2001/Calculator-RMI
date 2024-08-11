import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

// Main class to start the RMI server
public class CalculatorServer {
    public static void main(String[] args) {
        try {
            // Create and export a registry on port 1099
            LocateRegistry.createRegistry(1099);
            // Instantiate the Calculator implementation
            Calculator calculator = new CalculatorImplementation();
            // Bind the remote object's stub in the registry
            Naming.rebind("CalculatorService", calculator);
            System.out.println("Calculator Server is ready."); 
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString()); 
            e.printStackTrace(); 
        }
    }
}