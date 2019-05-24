package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2150CreateBills {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2150CreateBills(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE bills ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ 	"booking_id INT NOT NULL,"  
				+	"user_id INT NOT NULL,"
				+ 	"total_price DOUBLE NOT NULL,"
				+	"created DATE ,"
				+	"updated DATE ,"
				+ 	"CONSTRAINT fk_bills_booking_id FOREIGN KEY (booking_id) REFERENCES bookings(id),"
				+ 	"CONSTRAINT fk_bills_user_id FOREIGN KEY (user_id) REFERENCES users(id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2150CreateBills started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'bills\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2150CreateBills ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
