# Portfolio project IDATT1003 - 2023

STUDENT NAME = Anders Morille  
STUDENT ID = 111708

## Project description

This project is a portfolio project for the course IDATT1003 - Programming 2 at NTNU. The project is a Java application that can be used to manage a database of students and courses. The application can be used to add, remove, edit and view train departures.

## Project structure

The project is divided into a set of packages, each with a specific purpose. The packages are as follows:

- commands: Contains the classes that implement the commands that can be executed by the application.
- input: Contains the classes that handle the input from the user.
- models: Contains the classes that represent the data that is stored
- utils: Contains utility classes that are used by the application.
- TrainDispatchApp: Contains the main class of the application.

## Link to repository

https://github.com/lille-morille/IDATT1003-2023-Mappe-TrainDispatchSystem

## How to run the project

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)

To run this project, simply run the main method in the TrainDispatchApp class. The main method will start the application and display a menu in the terminal. The user can then enter a command to execute, either by name, or index that you see on the left hand side. This menu will be displayed after each command execution. To exit the application, enter the command `exit` or `8`.

## How to run the tests

- Enter the command `mvn clean test` in the terminal to run all unit tests for the project. Make sure you are in the root directory in the terminal when doing this.
- Or if you're using IntelliJ IDEA, select "Test Project" from the run configurations

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
