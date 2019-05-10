package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2120CreateBookings {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2120CreateBookings(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE bookings ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ 	"room_id INT NOT NULL,"  
				+	"user_id INT NOT NULL,"
				+	"check_in DATE NOT NULL,"
				+	"check_out DATE NOT NULL,"
				+	"quanlity_of_people INT NOT NULL,"
				+ 	"CONSTRAINT fk_bookings_room_id FOREIGN KEY (room_id) REFERENCES rooms(id),"
				+ 	"CONSTRAINT fk_bookings_user_id FOREIGN KEY (user_id) REFERENCES users(id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2110CreateBookings started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'bookings\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2110CreateBookings ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
