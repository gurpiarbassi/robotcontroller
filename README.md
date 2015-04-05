# robotcontroller

Project to control robots around a grid.

Built using Maven 3.3.1

To build: mvn clean compile assembly:single This will produce a jar file in the target directory called Gurps-robot-controller.jar.

Compiler level mut be java 1.8

To run: java -jar Gurps-robot-controller.jar /path/to/file/file.txt
Where the path to the input file is the argument to the main class.

## Assumptions made

Robots may collide with one another but we assume they trample over each other and carry on.

Assume that the input is always file based.

Assuming that if an error occurs in validating the file, the system will exit

Assuming that if a robot moves outside of the defined grid area, this is an exception case which will cause the program to exit.
These robots are expensive and if we let them loose outside the grid NASA will expect us to pay for them!
