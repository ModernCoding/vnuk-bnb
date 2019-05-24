package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2020CreateRooms {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2020CreateRooms(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE rooms ("
				+ "id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, "
				+ "price DOUBLE NOT NULL , "
				+ "beds INT NOT NULL , "
				+ "room_type_id INT NOT NULL , "
				+ "room_number INT NOT NULL , "
				+ "is_smoking BOOLEAN NOT NULL , "
				+ "CONSTRAINT fk_rooms_room_type_id FOREIGN KEY (room_type_id) REFERENCES room_types(id) "
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2020CreateRooms started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'rooms\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2020CreateRooms ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
