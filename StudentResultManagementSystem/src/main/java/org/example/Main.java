package org.example;

import org.example.dao.StudentDAO;
import org.example.dao.ResultDAO;
import org.example.dao.impl.StudentDAOImpl;
import org.example.dao.impl.ResultDAOImpl;
import org.example.model.Student;
import org.example.model.Result;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final StudentDAO studentDao = new StudentDAOImpl();
    private static final ResultDAO resultDao = new ResultDAOImpl();

    public static void main(String[] args) {

        System.out.println("=== Student Management System ===");

        while (true) {
            showMenu();
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1": addStudent(); break;
                    case "2": updateStudent(); break;
                    case "3": listStudents(); break;
                    case "4": addResult(); break;
                    case "5": listResults(); break;
                    case "0":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n1) Add Student | 2) Update Student | 3) View Students | 4) Add Result | 5) View Results | 0) Exit");
        System.out.print("Choose option: ");
    }

    // ----------------- STUDENT METHODS -----------------

    private static void addStudent() throws Exception {
        Student s = new Student();

        System.out.print("Enter Name: ");
        s.setName(sc.nextLine());

        System.out.print("Enter Course: ");
        s.setCourse(sc.nextLine());

        System.out.print("Enter Email: ");
        s.setEmail(sc.nextLine());

        System.out.print("Enter Phone: ");
        s.setPhone(sc.nextLine());

        int id = studentDao.addStudent(s);
        System.out.println("Student added successfully with ID: " + id);
    }

    private static void updateStudent() throws Exception {
        System.out.print("Enter Student ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = new Student();
        s.setStudentId(id);

        System.out.print("New Name: ");
        s.setName(sc.nextLine());

        System.out.print("New Course: ");
        s.setCourse(sc.nextLine());

        System.out.print("New Email: ");
        s.setEmail(sc.nextLine());

        System.out.print("New Phone: ");
        s.setPhone(sc.nextLine());

        boolean updated = studentDao.updateStudent(s);
        System.out.println(updated ? "Student Updated Successfully." : "Update Failed.");
    }

    private static void listStudents() throws Exception {
        List<Student> students = studentDao.getAllStudents();

        System.out.println("\n--- Student List ---");
        System.out.println("ID | Name | Course | Email | Phone");
        for (Student s : students) {
            System.out.printf("%d | %s | %s | %s | %s%n",
                    s.getStudentId(), s.getName(), s.getCourse(), s.getEmail(), s.getPhone());
        }
    }

    // ----------------- RESULT METHODS -----------------

    private static void addResult() throws Exception {
        System.out.print("Enter Student ID: ");
        int studentId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Marks (0–100): ");
        int marks = Integer.parseInt(sc.nextLine());

        String grade = calculateGrade(marks);

        Result r = new Result(studentId, marks, grade);
        int resultId = resultDao.addResult(r);

        if (resultId != -1) {
            System.out.println("Result added successfully with Result ID: " + resultId +
                    " (Grade: " + grade + ")");
        } else {
            System.out.println("Failed to add result.");
        }
    }

    private static void listResults() throws Exception {
        List<Result> results = resultDao.getAllResults();

        System.out.println("\n--- Results List ---");
        System.out.println("ResultId | StudentId | Marks | Grade");
        for (Result r : results) {
            System.out.printf("%d | %d | %d | %s%n",
                    r.getResultId(), r.getStudentId(), r.getMarks(), r.getGrade());
        }
    }

    // ----------------- HELPER -----------------

    private static String calculateGrade(int marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }
}