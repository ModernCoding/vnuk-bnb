package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.Users;
import vn.edu.vnuk.bnb.model.Bookings;
import vn.edu.vnuk.bnb.model.Rooms;
public class BookingsDao {
	
    private Connection connection;

    public BookingsDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public BookingsDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Bookings task) throws SQLException{

        String sqlQuery = "insert into bookings (room_id, user_id, check_in, check_out, quanlity_of_people) "
                        +	"values (?, ?, ?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
       
                statement.setLong(1, task.getRoom().getId());
                statement.setLong(2, task.getUser().getId());
                statement.setDate(
                		3,
                		task.getCheckIn() == null ? null : new Date(task.getCheckIn().getTimeInMillis())
                	);
                statement.setDate(
                		4,
                		task.getCheckOut() == null ? null : new Date(task.getCheckOut().getTimeInMillis())
                	);
                statement.setInt(5, task.getQuanlity());
                
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
    public List<Bookings> read() throws SQLException {

        String sqlQuery = "select * from bookings";
        PreparedStatement statement;
        List<Bookings> tasks = new ArrayList<Bookings>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Bookings task = new Bookings();
                task.setId(results.getLong("id"));
                
                Long roomsIdFromDB = results.getLong("room_id");
                Long usersIdFromDB = results.getLong("user_id");
                
                RoomsDao roomsDao = new RoomsDao();
                UserDao usersDao = new UserDao();
                
                Rooms rooms = roomsDao.read(roomsIdFromDB);
                Users users = usersDao.read(usersIdFromDB);
                
                task.setRoom(rooms);
                task.setUser(users);
                
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
    public Bookings read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Bookings task) throws SQLException {
        String sqlQuery = "update bookings set room_id=?, user_id=?, check_in=?, check_out=?, quanlity_of_people where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            
            statement.setLong(1, task.getRoom().getId());
            statement.setLong(2, task.getUser().getId());
            statement.setDate(
            		3,
            		task.getCheckIn() == null ? null : new Date(task.getCheckIn().getTimeInMillis())
            	);
            statement.setDate(
            		4,
            		task.getCheckOut() == null ? null : new Date(task.getCheckOut().getTimeInMillis())
            	);
            statement.setInt(5, task.getQuanlity());
            
            statement.execute();
            statement.close();
            
            System.out.println("Bookings successfully modified.");
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
        String sqlQuery = "delete from bookings where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Bookings successfully deleted.");

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
        
    	Bookings task = this.read(id, false);
//        task.setIsComplete(true);
        task.setCheckIn(Calendar.getInstance());
        task.setCheckOut(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Bookings read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from bookings where id=?";

        PreparedStatement statement;
        Bookings task = new Bookings();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                Long roomsIdFromDB = results.getLong("room_id");
                Long usersIdFromDB = results.getLong("user_id");
                
                RoomsDao roomsDao = new RoomsDao();
                UserDao usersDao = new UserDao();
                
                Rooms rooms = roomsDao.read(roomsIdFromDB);
                Users users = usersDao.read(usersIdFromDB);
                
                task.setRoom(rooms);
                task.setUser(users);
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