package driverProduct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc {
	public void drive() {
		System.out.println("hello");
		String url = "jdbc:mysql://localhost:3306/temp_db";
		try (Connection con = DriverManager.getConnection(url, "root", "")) {
			try (Statement stmt = con.createStatement()) {
				if (stmt.executeUpdate("insert into users values('John',22)") != 1) {
					System.out.println("Error in insertion");
					System.exit(-1);
				}
				
				try (ResultSet rs = stmt.executeQuery("select * from users")) {
					while (rs.next()) {
						System.out.print("Name " + rs.getString(1));
						System.out.println("\tAge " + rs.getInt(2));
					}
				}
			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
	}
}

