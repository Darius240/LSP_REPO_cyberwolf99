package org.howard.edu.lsp.midterm.question2;

public class Main {
  public static void main(String[] args) {
    // Required exact output lines:
    System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
    System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
    System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
    System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

    // Exception demonstration (invoke with invalid dimension)
    System.out.println(); //  spacing
    try {
      // This should throw IllegalArgumentException
      AreaCalculator.area(0.0);
      System.out.println("ERROR: Exception was not thrown for invalid input!");
    } catch (IllegalArgumentException e) {
      // Print an error message to System.out (any message is fine)
      System.out.println("Caught expected exception: " + e.getMessage());
    }
  }
}
