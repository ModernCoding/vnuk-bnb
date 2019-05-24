package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2030CreateEquipments {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2030CreateEquipments(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE equipments ("
				+ "id INT NOT NULL AUTO_INCREMENT, "
				+ "label VARCHAR(255) NOT NULL, "
				+ "PRIMARY KEY (id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2030CreateEquipments started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'equipments\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2030CreateEquipments ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
