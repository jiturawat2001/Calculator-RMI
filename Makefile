JAVAC = javac
JAVA = java
RMIREGISTRY = rmiregistry
SOURCES = Calculator.java CalculatorImplementation.java CalculatorServer.java CalculatorClient.java
CLASSES = Calculator.class CalculatorImplementation.class CalculatorServer.class CalculatorClient.class

all: compile

compile:
	$(JAVAC) $(SOURCES)

rmiregistry:
	@echo "Please start the RMI registry manually by running 'rmiregistry' in a separate terminal."

server:
	$(JAVA) CalculatorServer

client:
	$(JAVA) CalculatorClient

clean:
	rm -f $(CLASSES)
