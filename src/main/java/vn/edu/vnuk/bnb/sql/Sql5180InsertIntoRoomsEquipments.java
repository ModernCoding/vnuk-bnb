package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Sql5180InsertIntoRoomsEquipments {
	
	private final Connection connection;
	private final String sqlQuery;
	
	public Sql5180InsertIntoRoomsEquipments(Connection connection) {
		this.connection = connection;
		
		this.sqlQuery = "INSERT INTO rooms_equipments (room_id, equipment_id) "
                +	"values (?, ?)";
	}
	
	public void run() throws SQLException {

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(">  Sql5130InsertIntoRoomsEquipments started");
		
		PreparedStatement statement;
		
		try {
			statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values

            statement.setInt(1, 1);
            statement.setInt(2, 1);

            // 	Executing statement
			statement.execute();
			statement.close();
	        System.out.println("   DATA successfully loaded in \'rooms_equipments\'");
		
		}
		
		catch (Exception e) {
	        e.printStackTrace();
	        connection.close();
		}
		
		finally {
			System.out.println("<  Sql5130InsertIntoRoomsEquipments ended");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("");
		}
			
	}
}
