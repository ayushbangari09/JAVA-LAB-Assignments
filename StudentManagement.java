import java.util.*;

// Abstract Class Person
abstract class Person {
    protected String name;
    protected String email;

    Person() {}

    Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // abstract method
    public abstract void displayInfo();
}


// Method Overloading + Overriding
class Student extends Person {
    private int rollNo;
    private String course;
    private double marks;
    private char grade;

    // Default Constructor
    Student() {}

    // Main Constructor
    Student(int rollNo, String name, String email, String course, double marks) {
        super(name, email);
        this.rollNo = rollNo;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    // OVERLOADED METHOD
    public void displayInfo(String researchArea) {
        displayInfo();
        System.out.println("Research Area: " + researchArea);
    }

    // OVERRIDDEN METHOD
    @Override
    public void displayInfo() {
        System.out.println("\nStudent Info:");
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Course: " + course);
        System.out.println("Marks: " + marks);
        System.out.println("Grade: " + grade);
    }

    // Grade Calculation
    private void calculateGrade() {
        if (marks >= 90) grade = 'A';
        else if (marks >= 75) grade = 'B';
        else if (marks >= 60) grade = 'C';
        else grade = 'D';
    }

    public int getRollNo() {
        return rollNo;
    }
}

// Interface
interface RecordActions {
    void addStudent(Student s);
    void deleteStudent(int rollNo);
    void updateStudent(int rollNo, Student updatedStudent);
    Student searchStudent(int rollNo);
    void viewAllStudents();
}


// StudentManager Class
class StudentManager implements RecordActions {

    private Map<Integer, Student> map = new HashMap<>();

    @Override
    public void addStudent(Student s) {
        if (map.containsKey(s.getRollNo())) {
            System.out.println("❌ Roll number already exists!");
        } else {
            map.put(s.getRollNo(), s);
            System.out.println("✔ Student added successfully!");
        }
    }

    @Override
    public void deleteStudent(int rollNo) {
        if (map.remove(rollNo) != null)
            System.out.println("✔ Student deleted.");
        else
            System.out.println("❌ Student not found.");
    }

    @Override
    public void updateStudent(int rollNo, Student updated) {
        if (map.containsKey(rollNo)) {
            map.put(rollNo, updated);
            System.out.println("✔ Student updated.");
        } else {
            System.out.println("❌ Student not found.");
        }
    }

    @Override
    public Student searchStudent(int rollNo) {
        return map.get(rollNo);
    }

    @Override
    public void viewAllStudents() {
        if (map.isEmpty()) {
            System.out.println("❌ No records found.");
            return;
        }
        for (Student s : map.values()) {
            s.displayInfo();
        }
    }
}


// Main Application
public class StudentManagement {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n===== Student Management Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. Search Student");
            System.out.println("5. View All Students");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                
                case 1:
                    System.out.print("Roll No: ");
                    int r = sc.nextInt(); sc.nextLine();

                    System.out.print("Name: ");
                    String n = sc.nextLine();

                    System.out.print("Email: ");
                    String e = sc.nextLine();

                    System.out.print("Course: ");
                    String c = sc.nextLine();

                    System.out.print("Marks: ");
                    double m = sc.nextDouble();

                    Student s = new Student(r, n, e, c, m);
                    manager.addStudent(s);
                    break;

                case 2:
                    System.out.print("Enter Roll No: ");
                    manager.deleteStudent(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter Roll No to Update: ");
                    int rr = sc.nextInt(); sc.nextLine();

                    System.out.print("New Name: ");
                    String nn = sc.nextLine();

                    System.out.print("New Email: ");
                    String ne = sc.nextLine();

                    System.out.print("New Course: ");
                    String nc = sc.nextLine();

                    System.out.print("New Marks: ");
                    double nm = sc.nextDouble();

                    Student updated = new Student(rr, nn, ne, nc, nm);
                    manager.updateStudent(rr, updated);
                    break;

                case 4:
                    System.out.print("Enter Roll No: ");
                    Student found = manager.searchStudent(sc.nextInt());
                    if (found != null) found.displayInfo();
                    else System.out.println("❌ Student not found.");
                    break;

                case 5:
                    manager.viewAllStudents();
                    break;

                case 6:
                    System.out.println("✔ Exiting...");
                    return;

                default:
                    System.out.println("❌ Invalid Option!");
            }
        }
    }
}
