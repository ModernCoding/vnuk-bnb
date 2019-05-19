package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5230InsertIntoBills {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5230InsertIntoBills(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO bills (booking_id, user_id, total_price, created, updated) "
                +	"values (?, ?, ?, ?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5230InsertIntoBills started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setInt(1, 1);
            statement.setInt(2, 1);
            statement.setDouble(3, 1000);
            statement.setDate(4, null);
            statement.setDate(5, null);
            
            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'bills\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5230InsertIntoBills ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
