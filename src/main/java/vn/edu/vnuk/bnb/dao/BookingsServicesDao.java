package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.Services;
import vn.edu.vnuk.bnb.model.Bookings;
import vn.edu.vnuk.bnb.model.BookingsServices;
public class BookingsServicesDao {
	
    private Connection connection;

    public BookingsServicesDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public BookingsServicesDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(BookingsServices task) throws SQLException{

        String sqlQuery = "insert into bookings_services (room_id, user_id, price) "
                        +	"values (?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
       
                statement.setLong(1, task.getBookings().getId());
                statement.setLong(2, task.getServices().getId());
                statement.setDouble(3, task.getPrice());
                
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
    public List<BookingsServices> read() throws SQLException {

        String sqlQuery = "select * from bookings_services";
        PreparedStatement statement;
        List<BookingsServices> tasks = new ArrayList<BookingsServices>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	BookingsServices task = new BookingsServices();
                task.setId(results.getLong("id"));
                
                Long bookingsIdFromDB = results.getLong("booking_id");
                Long servicesIdFromDB = results.getLong("service_id");
                
                BookingsDao bookingsDao = new BookingsDao();
                ServicesDao servicesDao = new ServicesDao();
                
                Bookings bookings = bookingsDao.read(bookingsIdFromDB);
                Services services = servicesDao.read(servicesIdFromDB);
                
                task.setBookings(bookings);
                task.setServices(services);
                
                task.setPrice(results.getDouble("price"));
                
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
    public BookingsServices read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(BookingsServices task) throws SQLException {
        String sqlQuery = "update bookings_services set room_id=?, user_id=?, check_in=?, check_out=?, quanlity_of_people where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            
            statement.setLong(1, task.getBookings().getId());
            statement.setLong(2, task.getServices().getId());
            statement.setDouble(3, task.getPrice());
            
            statement.execute();
            statement.close();
            
            System.out.println("BookingsServices successfully modified.");
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
        String sqlQuery = "delete from bookings_services where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("BookingsServices successfully deleted.");

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
        
    	BookingsServices task = this.read(id, false);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private BookingsServices read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from bookings_services where id=?";

        PreparedStatement statement;
        BookingsServices task = new BookingsServices();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                Long bookingsIdFromDB = results.getLong("booking_id");
                Long servicesIdFromDB = results.getLong("service_id");
                
                BookingsDao bookingsDao = new BookingsDao();
                ServicesDao servicesDao = new ServicesDao();
                
                Bookings bookings = bookingsDao.read(bookingsIdFromDB);
                Services services = servicesDao.read(servicesIdFromDB);
                
                task.setBookings(bookings);
                task.setServices(services);
                
                task.setPrice(results.getDouble("price"));
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