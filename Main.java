// Name: Akhil Chivukula
// PRN: 23070126009
// Batch: AIML A1

package ui;

import service.StudentService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Student Database Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search by PRN");
            System.out.println("4. Search by Name");
            System.out.println("5. Update Student");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> service.addStudent();
                case 2 -> service.displayAll();
                case 3 -> service.searchByPRN();
                case 4 -> service.searchByName();
                case 5 -> service.updateStudent();
                case 6 -> service.deleteStudent();
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
