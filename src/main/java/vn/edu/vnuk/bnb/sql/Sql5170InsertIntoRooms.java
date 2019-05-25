package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5170InsertIntoRooms {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5170InsertIntoRooms(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO rooms (price, beds, room_type_id, room_number, is_smoking) "
                +	"values (?, ?, ?, ?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5100InsertIntoRooms started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setDouble(1, 30);
            statement.setInt(2, 3);
            statement.setLong(3, 1);
            statement.setInt(4, 1);
            statement.setBoolean(5, false);
            
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
			System.out.println("<  Sql5100InsertIntoRooms ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
