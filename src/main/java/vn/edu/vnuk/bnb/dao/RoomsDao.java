package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.Rooms;
import vn.edu.vnuk.bnb.model.RoomTypes;
public class RoomsDao {
	
    private Connection connection;

    public RoomsDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public RoomsDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Rooms task) throws SQLException{

        String sqlQuery = "insert into rooms (price, beds, room_type_id, room_number, is_smoking) "
                        +	"values (?, ?, ?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setDouble(1, task.getPrice());
                statement.setInt(2, task.getBeds());
                statement.setLong(3, task.getRoomTypes().getId());
                statement.setInt(4, task.getRoomNumber());
                statement.setBoolean(5, task.isSmoking());

                // 	Executing statement
                statement.execute();

                System.out.println("New record in DB !");

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                System.out.println("Done !");
                connection.close();
        }

    }
    
    
    //  READ (List of Tasks)
    @SuppressWarnings("finally")
    public List<Rooms> read() throws SQLException {

        String sqlQuery = "select * from rooms";
        PreparedStatement statement;
        List<Rooms> tasks = new ArrayList<Rooms>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Rooms task = new Rooms();
                task.setId(results.getLong("id"));
                task.setPrice(results.getDouble("price"));
                task.setBeds(results.getInt("beds"));
                
                Long roomTypesIdFromDB = results.getLong("room_type_id");
                
                
                task.setRoomNumber(results.getInt("room_number"));
                task.setSmoking(results.getBoolean("is_smoking"));
                
                RoomTypesDao roomTypesDao = new RoomTypesDao();
                
                RoomTypes roomTypes = roomTypesDao.read(roomTypesIdFromDB);
                
                task.setRoomTypes(roomTypes);
                tasks.add(task);

            }

            results.close();
            statement.close();


        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
                connection.close();
                return tasks;
        }


    }


    //  READ (Single Task)
    public Rooms read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Rooms task) throws SQLException {
        String sqlQuery = "update rooms set price=?, beds=?, room_type_id=?, room_number=?, is_smoking=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDouble(1, task.getPrice());
            statement.setInt(2, task.getBeds());
            statement.setLong(3, task.getRoomTypes().getId());
            statement.setInt(4, task.getRoomNumber());
            statement.setBoolean(5, task.isSmoking());
            
            statement.execute();
            statement.close();
            
            System.out.println("Rooms successfully modified.");
        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }
        
    }
    
    
    //  DELETE
    public void delete(Long id) throws SQLException {
        String sqlQuery = "delete from rooms where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Rooms successfully deleted.");

        } 

        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        finally {
            connection.close();
        }

    }
    
    
    //  OTHERS
    
    public void complete(Long id) throws SQLException{
        
    	Rooms task = this.read(id, false);
        task.setSmoking(true);;
//        task.setDateOfCompletion(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Rooms read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from rooms where id=?";

        PreparedStatement statement;
        Rooms task = new Rooms();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                task.setPrice(results.getDouble("price"));
                task.setBeds(results.getInt("beds"));
                
                Long roomTypesIdFromDB = results.getLong("room_type_id");
                
                
                task.setRoomNumber(results.getInt("room_number"));
                task.setSmoking(results.getBoolean("is_smoking"));
                
                RoomTypesDao roomTypesDao = new RoomTypesDao();
                
                RoomTypes roomTypes = roomTypesDao.read(roomTypesIdFromDB);
                
                task.setRoomTypes(roomTypes);

            }

            statement.close();

        } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } finally {
            
            if (closeAfterUse) {
                connection.close();
    
            }
            
            return task;
        }

    }

}