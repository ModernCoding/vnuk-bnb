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
import vn.edu.vnuk.bnb.model.Bills;
public class BillsDao {
	
    private Connection connection;

    public BillsDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public BillsDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Bills task) throws SQLException{

        String sqlQuery = "insert into bills (booking_id, user_name, total_price, created_at, updated_at) "
                        +	"values (?, ?, ?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, task.getBooking().getId());
                statement.setLong(2, task.getUser().getId());
                statement.setDouble(3, task.getTotalPrice());
                statement.setDate(
                		4,
                		task.getCreated() == null ? null : new Date(task.getCreated().getTimeInMillis())
                	);
                statement.setDate(
                		5,
                		task.getUpdated() == null ? null : new Date(task.getUpdated().getTimeInMillis())
                	);
//                statement.setDate(10, task.getUpdateAt());
              
                
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
    public List<Bills> read() throws SQLException {

        String sqlQuery = "select * from bills";
        PreparedStatement statement;
        List<Bills> tasks = new ArrayList<Bills>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Bills task = new Bills();
                task.setId(results.getLong("id"));
                
                
                Long bookingsIdFromDB = results.getLong("booking_id");
                Long usersIdFromDB = results.getLong("user_id");
                
                BookingsDao bookingsDao = new BookingsDao();
                UserDao usersDao = new UserDao();
                
                Bookings bookings = bookingsDao.read(bookingsIdFromDB);
                Users users = usersDao.read(usersIdFromDB);
                
                task.setBooking(bookings);;
                task.setUser(users);
                
                Date dateOfCreate = results.getDate("create_at");
                
                if (dateOfCreate == null){
                    task.setCreated(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfCreate);
                    task.setCreated(date);
                }
                
                Date dateOfUpdate = results.getDate("update_at");
                
                if (dateOfUpdate == null){
                    task.setUpdated(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfUpdate);
                    task.setUpdated(date);
                }
                
                
                
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
    public Bills read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Bills task) throws SQLException {
        String sqlQuery = "update bills set user_type_id=?, first_name=?, middle_name=?, last_name=?, address=?, email=?, phone=?, identification_number=?,create_at=?, update_at=?, identification_type_id=?, country_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
     
            statement.setLong(1, task.getBooking().getId());
            statement.setLong(2, task.getUser().getId());
            statement.setDouble(3, task.getTotalPrice());
            statement.setDate(
            		4,
            		task.getCreated() == null ? null : new Date(task.getCreated().getTimeInMillis())
            	);
            statement.setDate(
            		5,
            		task.getUpdated() == null ? null : new Date(task.getUpdated().getTimeInMillis())
            	);
            
            statement.execute();
            statement.close();
            
            System.out.println("Bills successfully modified.");
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
        String sqlQuery = "delete from bills where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Bills successfully deleted.");

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
        
    	Bills task = this.read(id, false);
//        task.setIsComplete(true);
        task.setCreated(Calendar.getInstance());
        task.setUpdated(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Bills read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from bills where id=?";

        PreparedStatement statement;
        Bills task = new Bills();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                Long bookingsIdFromDB = results.getLong("booking_id");
                Long usersIdFromDB = results.getLong("user_id");
                
                BookingsDao bookingsDao = new BookingsDao();
                UserDao usersDao = new UserDao();
                
                Bookings bookings = bookingsDao.read(bookingsIdFromDB);
                Users users = usersDao.read(usersIdFromDB);
                
                task.setBooking(bookings);;
                task.setUser(users);
                
                Date dateOfCreate = results.getDate("create_at");
                
                if (dateOfCreate == null){
                    task.setCreated(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfCreate);
                    task.setCreated(date);
                }
                
                Date dateOfUpdate = results.getDate("update_at");
                
                if (dateOfUpdate == null){
                    task.setUpdated(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfUpdate);
                    task.setUpdated(date);
                }
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