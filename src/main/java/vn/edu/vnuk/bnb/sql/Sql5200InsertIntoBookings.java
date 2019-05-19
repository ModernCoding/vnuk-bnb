package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5200InsertIntoBookings {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5200InsertIntoBookings(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO bookings (room_id, user_id, check_in, check_out, quanlity_of_people) "
                +	"values (?, ?, ?, ?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5200InsertIntoBookings started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values

            statement.setInt(1, 1);
            statement.setInt(2, 1);
            statement.setDate(3, null);
            statement.setDate(4, null);
            statement.setInt(5, 100);
            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'bookings\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5200InsertIntoBookings ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
