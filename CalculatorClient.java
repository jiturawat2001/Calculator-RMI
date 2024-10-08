
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

// Main class to start the RMI client
public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the registry
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Print menu options for the user
                System.out.println("1. Push Value");
                System.out.println("2. Push Operation (min, max, lcm, gcd)");
                System.out.println("3. Pop Value");
                System.out.println("4. Check if Stack is Empty");
                System.out.println("5. Delayed Pop");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt(); 
                switch (choice) {
                    case 1:
                        System.out.print("Enter value: ");
                        int value = scanner.nextInt(); 
                        calculator.pushValue(value);
                        break;
                    case 2:
                        System.out.print("Enter operation (min, max, lcm, gcd): ");
                        String operation = scanner.next(); 
                        calculator.pushOperation(operation); 
                        break;
                    case 3:
                        try {
                            int result = calculator.pop();
                            System.out.println("Popped value: " + result);
                        } catch (RemoteException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        System.out.println("Is stack empty? " + calculator.isEmpty()); 
                        break;
                    case 5:
                        System.out.print("Enter delay in milliseconds: ");
                        int millis = scanner.nextInt(); 
                        System.out.println("Popped value after delay: " + calculator.delayPop(millis)); 
                                                                                                        
                                                                                                        
                        break;
                    case 6:
                        System.out.println("Exiting..."); 
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); 
            e.printStackTrace(); 
        }
    }
}