package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5211InsertIntoDishes {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5211InsertIntoDishes(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO dishes (price, dish_type_id, label, description) "
                +	"values (?, ?, ?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5211InsertIntoDishes started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setDouble(1, 1000);
            statement.setInt(2, 1);
            statement.setString(3, "asd");
            statement.setString(4, "asd");
            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'dishes\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5211InsertIntoDishes ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
