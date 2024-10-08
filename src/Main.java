import java.sql.*;
import java.util.Scanner;

import static java.sql.DriverManager.*;

class HospitalManagement {
    static final String DB_URL = "jdbc:mysql://localhost:3306/hospital_db";
    static final String USER = "root";
    static final String PASS = "saket_18";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection conn = getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            while (true) {
                System.out.println("\nHospital Management System");
                System.out.println("1. Add Doctor  2. Delete Doctor  3. Update Doctor");
                System.out.println("4. Add Patient 5. Delete Patient 6. Update Patient");
                System.out.println("7. Display Doctors 8. Display Patients 9. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Doctor Name: ");
                        String dname = sc.next();
                        stmt.executeUpdate("INSERT INTO doctors (name) VALUES ('" + dname + "')");
                        System.out.println("Doctor added.");
                        break;
                    case 2:
                        System.out.print("Enter Doctor ID to delete: ");
                        int did = sc.nextInt();
                        stmt.executeUpdate("DELETE FROM doctors WHERE id=" + did);
                        System.out.println("Doctor deleted.");
                        break;
                    case 3:
                        System.out.print("Enter Doctor ID to update: ");
                        int udid = sc.nextInt();
                        System.out.print("Enter new Doctor Name: ");
                        String newDname = sc.next();
                        stmt.executeUpdate("UPDATE doctors SET name='" + newDname + "' WHERE id=" + udid);
                        System.out.println("Doctor updated.");
                        break;
                    case 4:
                        System.out.print("Enter Patient Name: ");
                        String pname = sc.next();
                        stmt.executeUpdate("INSERT INTO patients (name) VALUES ('" + pname + "')");
                        System.out.println("Patient added.");
                        break;
                    case 5:
                        System.out.print("Enter Patient ID to delete: ");
                        int pid = sc.nextInt();
                        stmt.executeUpdate("DELETE FROM patients WHERE id=" + pid);
                        System.out.println("Patient deleted.");
                        break;
                    case 6:
                        System.out.print("Enter Patient ID to update: ");
                        int upid = sc.nextInt();
                        System.out.print("Enter new Patient Name: ");
                        String newPname = sc.next();
                        stmt.executeUpdate("UPDATE patients SET name='" + newPname + "' WHERE id=" + upid);
                        System.out.println("Patient updated.");
                        break;
                    case 7:
                        ResultSet rs1 = stmt.executeQuery("SELECT * FROM doctors");
                        System.out.println("Doctors List:");
                        while (rs1.next()) {
                            System.out.println("ID: " + rs1.getInt("id") + ", Name: " + rs1.getString("name"));
                        }
                        break;
                    case 8:
                        ResultSet rs2 = stmt.executeQuery("SELECT * FROM patients");
                        System.out.println("Patients List:");
                        while (rs2.next()) {
                            System.out.println("ID: " + rs2.getInt("id") + ", Name: " + rs2.getString("name"));
                        }
                        break;
                    case 9:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
