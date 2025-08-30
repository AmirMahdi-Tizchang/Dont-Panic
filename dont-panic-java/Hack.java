import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class Hack {
    public static void main(String[] args) throws Exception {
        // Create connection with database file
        Connection sqliteConnection = DriverManager.getConnection("jdbc:sqlite:dont-panic.db");

        // Prompt from the user for a new password
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the new password: ");
        String password = scanner.nextLine();

        // Create the qury for updating
        String query = """
            UPDATE "users"
            SET "password" = ?
            WHERE "username" = 'admin';
        """;

        // Create Prepared Statement
        PreparedStatement sqliteStatement = sqliteConnection.prepareStatement(query);

        // Fill out the ? (placeholder) by value of password variable
        sqliteStatement.setString(1, password);

        // Execute the qury and run the statement
        sqliteStatement.executeUpdate();

        // Done with the connection
        sqliteConnection.close();

        // Finshed the scanner
        scanner.close();
    }
}


// RUN
// -> javac Hacke.java
// -> java -cp .:sqlite-jdbc-3.43.0.0.jar Hack
