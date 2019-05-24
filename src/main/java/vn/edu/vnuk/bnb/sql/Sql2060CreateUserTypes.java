package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2060CreateUserTypes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2060CreateUserTypes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE user_types ("
				+ "id INT NOT NULL AUTO_INCREMENT, "
				+ "label VARCHAR(255) NOT NULL, "
				+ "PRIMARY KEY (id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2060CreateUserTypes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'use_types\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2060CreateUserTypes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
