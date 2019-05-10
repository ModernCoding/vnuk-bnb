package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2140CreateBookingsDishes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2140CreateBookingsDishes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE bookings_dishes ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ 	"booking_id INT NOT NULL,"  
				+	"dishe_id INT,"
				+ 	"CONSTRAINT fk_bookings_dishes_booking_id FOREIGN KEY (booking_id) REFERENCES bookings(id),"
				+ 	"CONSTRAINT fk_bookings_dishes_dishe_id FOREIGN KEY (dishe_id) REFERENCES dishes(id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2140CreateBookingsDishes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'bookings_dishes\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2140CreateBookingsDishes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
