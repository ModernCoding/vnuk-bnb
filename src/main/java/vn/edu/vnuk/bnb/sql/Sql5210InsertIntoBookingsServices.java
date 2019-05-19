package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5210InsertIntoBookingsServices {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5210InsertIntoBookingsServices(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO bookings_services (booking_id, service_id,price) "
                +	"values (?, ?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5210InsertIntoBookingsServices started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setInt(1, 1);
            statement.setInt(2, 1);
            statement.setDouble(3, 1000);
            
            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'bookings_services\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5210InsertIntoBookingsServices ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
