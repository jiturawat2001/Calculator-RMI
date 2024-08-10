
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

// Main class to start the RMI client
public class CalculatorClient {
    public static void main(String[] args) {
        try {
            // Lookup the remote object from the registry
            Calculator calculator = (Calculator) Naming.lookup("rmi://localhost/CalculatorService");
            Scanner scanner = new Scanner(System.in); // Scanner to read user input
            while (true) {
                // Print menu options for the user
                System.out.println("1. Push Value");
                System.out.println("2. Push Operation (min, max, lcm, gcd)");
                System.out.println("3. Pop Value");
                System.out.println("4. Check if Stack is Empty");
                System.out.println("5. Delayed Pop");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt(); // Read the user's choice
                switch (choice) {
                    case 1:
                        System.out.print("Enter value: ");
                        int value = scanner.nextInt(); // Read the value to be pushed
                        calculator.pushValue(value); // Push the value onto the stack
                        break;
                    case 2:
                        System.out.print("Enter operation (min, max, lcm, gcd): ");
                        String operation = scanner.next(); // Read the operation to be pushed
                        calculator.pushOperation(operation); // Push the operation onto the stack
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
                        System.out.println("Is stack empty? " + calculator.isEmpty()); // Check if the stack is empty
                        break;
                    case 5:
                        System.out.print("Enter delay in milliseconds: ");
                        int millis = scanner.nextInt(); // Read the delay in milliseconds
                        System.out.println("Popped value after delay: " + calculator.delayPop(millis)); // Delay pop and
                                                                                                        // print the
                                                                                                        // value
                        break;
                    case 6:
                        System.out.println("Exiting..."); // Exit the application
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Try again."); // Handle invalid menu options
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString()); // Print any client exceptions
            e.printStackTrace(); // Print stack trace for the exception
        }
    }
}