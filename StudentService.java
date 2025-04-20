package service;

import db.DBConnection;
import model.Student;
import exceptions.InvalidPercentageException;
import exceptions.DuplicatePRNException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final Scanner sc = new Scanner(System.in);

    public void addStudent() {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.print("Enter PRN: ");
            String prn = sc.nextLine();
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine());

            if (marks < 0 || marks > 100)
                throw new InvalidPercentageException("Marks must be between 0 and 100.");

            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM students WHERE prn = ?");
            checkStmt.setString(1, prn);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next()) throw new DuplicatePRNException("Student with this PRN already exists.");

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO students VALUES (?, ?, ?)");
            stmt.setString(1, prn);
            stmt.setString(2, name);
            stmt.setDouble(3, marks);
            stmt.executeUpdate();

            System.out.println("Student added successfully.");
        } catch (SQLException | NumberFormatException | InvalidPercentageException | DuplicatePRNException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAll() {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM students");
            while (rs.next()) {
                System.out.println("PRN: " + rs.getString("prn") + ", Name: " + rs.getString("name") + ", Marks: " + rs.getDouble("marks"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchByPRN() {
        System.out.print("Enter PRN: ");
        String prn = sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE prn = ?");
            stmt.setString(1, prn);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Found: " + rs.getString("prn") + ", " + rs.getString("name") + ", " + rs.getDouble("marks"));
            } else {
                System.out.println("Student not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchByName() {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE name LIKE ?");
            stmt.setString(1, "%" + name + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Found: " + rs.getString("prn") + ", " + rs.getString("name") + ", " + rs.getDouble("marks"));
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateStudent() {
        System.out.print("Enter PRN to update: ");
        String prn = sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM students WHERE prn = ?");
            checkStmt.setString(1, prn);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Student not found.");
                return;
            }

            System.out.print("Enter new name: ");
            String name = sc.nextLine();
            System.out.print("Enter new marks: ");
            double marks = Double.parseDouble(sc.nextLine());

            if (marks < 0 || marks > 100)
                throw new InvalidPercentageException("Marks must be between 0 and 100.");

            PreparedStatement stmt = conn.prepareStatement("UPDATE students SET name=?, marks=? WHERE prn=?");
            stmt.setString(1, name);
            stmt.setDouble(2, marks);
            stmt.setString(3, prn);
            stmt.executeUpdate();

            System.out.println("Student updated successfully.");
        } catch (SQLException | InvalidPercentageException | NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteStudent() {
        System.out.print("Enter PRN to delete: ");
        String prn = sc.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE prn = ?");
            stmt.setString(1, prn);
            int affected = stmt.executeUpdate();

            if (affected > 0) System.out.println("Student deleted.");
            else System.out.println("Student not found.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
