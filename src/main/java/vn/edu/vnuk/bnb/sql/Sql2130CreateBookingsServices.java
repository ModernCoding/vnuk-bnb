package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2130CreateBookingsServices {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2130CreateBookingsServices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE bookings_services ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ 	"booking_id INT NOT NULL,"  
				+	"service_id INT NOT NULL,"
				+ 	"price DOUBLE NOT NULL , "
				+ 	"CONSTRAINT fk_bookings_services_booking_id FOREIGN KEY (booking_id) REFERENCES bookings(id),"
				+ 	"CONSTRAINT fk_bookings_services_service_id FOREIGN KEY (service_id) REFERENCES services(id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2120CreateBookingsServices started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'bookings_services\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2120CreateBookingsServices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
