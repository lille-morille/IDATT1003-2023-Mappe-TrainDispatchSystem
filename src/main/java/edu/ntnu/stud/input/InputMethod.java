package edu.ntnu.stud.input;

@FunctionalInterface
interface InputMethod<T> {
  T execute() throws InvalidInputException;
}
