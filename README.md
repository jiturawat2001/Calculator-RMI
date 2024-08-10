This is the README file that explians how to set up and run RMI based calculator service. This file provides instructions on how to compile and run the server and client, as well as an overview of the components.
RMI Calculator Service
Overview
This project implements a simple Calculator service using Java Remote Method Invocation (RMI). The service provides methods to perform operations on a stack of integers, such as pushing values, performing operations (min,max,lcm,gcd), and delayed popping. The project includes the following components :
1. **`Calculator.java`**: Defines the remote interface for the Calculator service.
2. **`CalculatorImplementation.java`**: Implements the remote Calculator service.
3. **`CalculatorClient.java`**: Provides a command-line interface for interacting with the Calculator service.
4. **`CalculatorServer.java`**: Starts the RMI server and binds the Calculator service to the RMI registry.

Building the Project

1. **Compile the Java Files**:
 Navigate to the directory containing the source files and compile them using `javac`:
   ```sh
   javac Calculator.java CalculatorImplementation.java CalculatorServer.java CalculatorClient.java
   ```
  This will generate the corresponding `.class` files.

2. **Start the RMI Registry** (if not automatically started):
   The RMI registry is typically started by the server, but if needed, you can start it manually using:
   ```sh
   rmiregistry
   ```
   Ensure the registry is running in the same directory where your compiled `.class` files are located, or set the classpath appropriately.

Running the Server

1. **Start the RMI Server**:
   Run the server to bind the Calculator service to the RMI registry:
   ```sh
   java CalculatorServer
   ```
   You should see the message: `Calculator Server is ready.`


## Running the Client

1. **Start the RMI Client**:
   Open a new terminal and run the client to interact with the Calculator service:
   ```sh
  java CalculatorClient
   ```
   The client will present a menu with the following options:
⦁	 Push Value
⦁	 Push Operation (min, max, lcm, gcd)
⦁	 Pop Value
⦁	 Check if Stack is Empty
⦁	 Delayed Pop
⦁	 Exit
   Follow the prompts to interact with the Calculator service.

## Example Usage

1. **Push a Value**:
   - Choose option `1`.
   - Enter an integer value to push onto the stack.

2. **Push an Operation**:
   - Choose option `2`.
   - Enter an operation (min, max, lcm, gcd) to apply to the values in the stack.

3. **Pop a Value**:
   - Choose option `3`.
   - The top value will be popped from the stack and displayed.

4. **Check if Stack is Empty**:
   - Choose option `4`.
   - The client will indicate whether the stack is empty.

5. **Delayed Pop**:
   - Choose option `5`.
   - Enter a delay in milliseconds. The client will wait and then pop the top value from the stack.

6. **Exit**:
   - Choose option `6` to exit the client application.





