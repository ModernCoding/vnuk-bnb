package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2090CreateRoomsEquipments {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2090CreateRoomsEquipments(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE rooms_equipments ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ 	"room_id INT NOT NULL,"  
				+	"equipment_id INT,"
				+ 	"CONSTRAINT fk_rooms_equipments_room_id FOREIGN KEY (room_id) REFERENCES rooms(id),"
				+ 	"CONSTRAINT fk_rooms_equipments_equipment_id FOREIGN KEY (equipment_id) REFERENCES equipments(id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2090CreateRoomEquipments started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'rooms_requipments\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2090CreateRoomEquipments ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
