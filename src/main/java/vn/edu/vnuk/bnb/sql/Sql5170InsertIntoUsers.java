package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5170InsertIntoUsers {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5170InsertIntoUsers(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO users (user_type_id, first_name, middle_name, last_name, address, email, phone, identification_number, create_at, update_at, identification_type_id, country_id) "
                +	"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5170InsertIntoUsers started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setInt(1, 1);
            statement.setString(2, "quang");
            statement.setString(3, "quang");
            statement.setString(4, "quang");
            statement.setString(5, "quang");
            statement.setString(6, "quang");
            statement.setString(7, "quang");
            statement.setInt(8, 200);
//            statement.setsetDate(9, 9/1/1999);
//            statement.setsetDate(9, 9/1/1999);
            statement.setInt(11, 1);
            statement.setInt(12, 1);
            
            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'rooms\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5170InsertIntoUsers ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
