package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.Bookings;
import vn.edu.vnuk.bnb.model.Dishes;
import vn.edu.vnuk.bnb.model.BookingsDishes;
public class BookingsDishesDao {
	
    private Connection connection;

    public BookingsDishesDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public BookingsDishesDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(BookingsDishes task) throws SQLException{

        String sqlQuery = "insert into bookings_dishes (room_id, equipment_id) "
                        +	"values (?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, task.getBookings().getId());
                statement.setLong(2, task.getDishes().getId());


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
    public List<BookingsDishes> read() throws SQLException {

        String sqlQuery = "select * from bookings_dishes";
        PreparedStatement statement;
        List<BookingsDishes> tasks = new ArrayList<BookingsDishes>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	BookingsDishes task = new BookingsDishes();
                task.setId(results.getLong("id"));
                
                Long bookingsIdFromDB = results.getLong("booking_id");
                Long dishesIdFromDB = results.getLong("dishe_id");
                
                
                BookingsDao bookingsDao = new BookingsDao();
                DishesDao dishesDao = new DishesDao();
                
                Bookings bookings = bookingsDao.read(bookingsIdFromDB);
                Dishes dishes =dishesDao.read(dishesIdFromDB);
                
                task.setBookings(bookings);
                task.setDishes(dishes);
                
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
    public BookingsDishes read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(BookingsDishes task) throws SQLException {
        String sqlQuery = "update bookings_dishes set rooms_id=?, equipments_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
     
            statement.setLong(1, task.getBookings().getId());
            statement.setLong(2, task.getDishes().getId());

            
            statement.execute();
            statement.close();
            
            System.out.println("BookingsDishes successfully modified.");
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
        String sqlQuery = "delete from bookings_dishes where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("BookingsDishes successfully deleted.");

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
        
    	BookingsDishes task = this.read(id, false);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private BookingsDishes read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from bookings_dishes where id=?";

        PreparedStatement statement;
        BookingsDishes task = new BookingsDishes();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                Long bookingsIdFromDB = results.getLong("booking_id");
                Long dishesIdFromDB = results.getLong("dishe_id");
                
                
                BookingsDao bookingsDao = new BookingsDao();
                DishesDao dishesDao = new DishesDao();
                
                Bookings bookings = bookingsDao.read(bookingsIdFromDB);
                Dishes dishes =dishesDao.read(dishesIdFromDB);
                
                task.setBookings(bookings);
                task.setDishes(dishes);
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