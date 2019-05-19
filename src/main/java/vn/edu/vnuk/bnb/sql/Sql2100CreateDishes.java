package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2100CreateDishes {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2100CreateDishes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE dishes ("
				+ "id INT NOT NULL AUTO_INCREMENT, "
				+ "price DOUBLE NOT NULL , "
				+ "dish_type_id INT NOT NULL , "
				+ "label VARCHAR(255) NOT NULL, "
				+ "description VARCHAR(255) NOT NULL, "
				+ "CONSTRAINT fk_dishes_dish_type_id FOREIGN KEY (dish_type_id) REFERENCES dish_types(id) ,"
				+ "PRIMARY KEY (id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql2100CreateDishes started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'dishes\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2100CreateDishes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
