package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5220InsertIntoBookingsDishes {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5220InsertIntoBookingsDishes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO bookings_dishes ((booking_id, dishe_id) "
                +	"values (?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5220InsertIntoBookingsDishes started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setInt(1, 1);
            statement.setInt(2, 1);
            
            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'bookings_dishes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5220InsertIntoBookingsDishes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
