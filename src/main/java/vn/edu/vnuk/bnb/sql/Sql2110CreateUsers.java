package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

public class Sql2110CreateUsers {
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql2110CreateUsers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "CREATE TABLE users ("
				+ 	"id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," 
				+	"user_type_id INT NOT NULL,"
				+ 	"first_name VARCHAR(255) NOT NULL, "
				+ 	"middle_name VARCHAR(255) NOT NULL, "
				+ 	"last_name VARCHAR(255) NOT NULL, "
				+ 	"address VARCHAR(255) NOT NULL, "
				+ 	"email VARCHAR(255) NOT NULL, "
				+ 	"phone VARCHAR(255) NOT NULL, "
				+	"identification_number INT NOT NULL,"
				+	"create_at DATE NOT NULL,"
				+	"update_at DATE NOT NULL,"
				+	"identification_type_id INT NOT NULL,"
				+	"country_id INT NOT NULL,"
				+ 	"CONSTRAINT fk_users_user_type_id FOREIGN KEY (user_type_id) REFERENCES user_types(id),"
				+ 	"CONSTRAINT fk_users_identification_type_id FOREIGN KEY (identification_type_id) REFERENCES identification_types(id),"
				+ 	"CONSTRAINT fk_users_country_id FOREIGN KEY (country_id) REFERENCES countries(id)"
				+ ") CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;"
			;
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("> Sql2110CreateUsers started");
		
		try {
	        connection.prepareStatement(sqlQuery).execute();
	        System.out.println("   TABLE \'users\' successfully created");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql2110CreateUsers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
