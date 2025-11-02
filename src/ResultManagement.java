import java.util.Scanner;

// Interface
interface IResult {
    void calculateResult();
    void displayResult();
}

// Custom Exception
class InvalidMarksException extends Exception {
    InvalidMarksException(String msg) {
        super(msg);
    }
}

// Base class
class Student implements IResult {
    String name;
    int rollNo;
    int[] marks = new int[3];
    int total = 0;
    double percentage;

    Student(String name, int rollNo, int[] marks) throws InvalidMarksException {
        this.name = name;
        this.rollNo = rollNo;
        for (int i = 0; i < 3; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException("Marks must be between 0 and 100");
            }
            this.marks[i] = marks[i];
        }
    }

    public void calculateResult() {
        for (int i = 0; i < 3; i++) total += marks[i];
        percentage = total / 3.0;
    }

    public void displayResult() {
        System.out.println("\n--- Student Result ---");
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
        System.out.println("Total Marks: " + total);
        System.out.println("Percentage: " + percentage + "%");
    }
}

// Derived class
class Undergraduate extends Student {
    String grade;

    Undergraduate(String name, int rollNo, int[] marks) throws InvalidMarksException {
        super(name, rollNo, marks);
    }

    void assignGrade() {
        if (percentage >= 75)
            grade = "Distinction";
        else if (percentage >= 60)
            grade = "First Class";
        else if (percentage >= 50)
            grade = "Second Class";
        else if (percentage >= 40)
            grade = "Pass";
        else
            grade = "Fail";
    }

    @Override
    public void displayResult() {
        super.displayResult();
        System.out.println("Grade: " + grade);
    }
}

// Main class
public class ResultManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Roll Number: ");
            int rollNo = sc.nextInt();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks of subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();
            }

            Undergraduate s1 = new Undergraduate(name, rollNo, marks);
            s1.calculateResult();
            s1.assignGrade();
            s1.displayResult();
        } 
        catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } 
        catch (Exception e) {
            System.out.println("Unexpected Error: " + e);
        }
    }
}
