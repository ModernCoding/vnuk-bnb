package vn.edu.vnuk.bnb.sql;

import java.sql.Connection;
import java.sql.SQLException;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;

public class Sql0000RunAllQueries {

	public static void main(String[] args) throws SQLException {
		
		Connection connectionDb = new ConnectionFactory()
				.getConnection("jdbc:mysql://localhost/");
		
		
		//	Create database
		
		new Sql1000DropDatabase(connectionDb).run();
		new Sql1010CreateDatabase(connectionDb).run();

		connectionDb.close();

		
		//	Create tables 
		
		Connection connectionTable = new ConnectionFactory()
				.getConnection();
		
		new Sql2010CreateRoomTypes(connectionTable).run();
		new Sql2020CreateRooms(connectionTable).run();
		new Sql2030CreateEquipments(connectionTable).run();
		new Sql2040CreateServices(connectionTable).run();
		new Sql2050CreateDishTypes(connectionTable).run();
		new Sql2060CreateUserTypes(connectionTable).run();
		new Sql2070CreateCountries(connectionTable).run();
		new Sql2080CreateIdentificationTypes(connectionTable).run();
		new Sql2090CreateRoomsEquipments(connectionTable).run();
		new Sql2100CreateDishes(connectionTable).run();
		new Sql2110CreateUsers(connectionTable).run();
		new Sql2120CreateBookings(connectionTable).run();
		new Sql2130CreateBookingsServices(connectionTable).run();
		
		new Sql2140CreateBookingsDishes(connectionTable).run();
		new Sql2150CreateBills(connectionTable).run();
		
		
		//	Insert data into tables
		new Sql5100InsertIntoRoomTypes(connectionTable).run();
		new Sql5110InsertIntoEquipment(connectionTable).run();
		new Sql5120InsertIntoServices(connectionTable).run();
		new Sql5130InsertIntoDishTypes(connectionTable).run();
		new Sql5140InsertIntoUserTypes(connectionTable).run();
		new Sql5150InsertIntoCountries(connectionTable).run();
		new Sql5160InsertIntoIdentificationTypes(connectionTable).run();
		new Sql5170InsertIntoRooms(connectionTable).run();
		new Sql5180InsertIntoRoomsEquipments(connectionTable).run();
		new Sql5190InsertIntoUsers(connectionTable).run();
		new Sql5200InsertIntoBookings(connectionTable).run();
		new Sql5210InsertIntoBookingsServices(connectionTable).run();
		new Sql5211InsertIntoDishes(connectionTable).run();
		new Sql5220InsertIntoBookingsDishes(connectionTable).run();
		new Sql5230InsertIntoBills(connectionTable).run();
		
		//new Sql5010InsertIntoTasks(connectionTable).run();
		
		
		connectionTable.close();
		
	}

}
