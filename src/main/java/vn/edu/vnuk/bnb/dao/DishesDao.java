package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.Dishes;
import vn.edu.vnuk.bnb.model.DishTypes;
public class DishesDao {
	
    private Connection connection;

    public DishesDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public DishesDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Dishes task) throws SQLException{

        String sqlQuery = "insert into dishes (price, dish_type_id, label, description) "
                        +	"values (?, ?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setDouble(1, task.getPrice());
                statement.setLong(2, task.getDishType().getId());
                statement.setString(3, task.getLabel());
                statement.setString(4, task.getDescription());

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
    public List<Dishes> read() throws SQLException {

        String sqlQuery = "select * from dishes";
        PreparedStatement statement;
        List<Dishes> tasks = new ArrayList<Dishes>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Dishes task = new Dishes();
                task.setId(results.getLong("id"));
                task.setPrice(results.getDouble("price"));
                
                Long dishTypesIdFromDB = results.getLong("dish_type_id");
                task.setLabel(results.getString("label"));
                task.setDescription(results.getString("description"));
                
                DishTypesDao dishTypesDao = new DishTypesDao();
                
                DishTypes dishTypes = dishTypesDao.read(dishTypesIdFromDB);
                
                task.setDishType(dishTypes);
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
    public Dishes read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Dishes task) throws SQLException {
        String sqlQuery = "update dishes set pice=?, dish_type_id=?, label=?, description=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setDouble(1, task.getPrice());
            statement.setLong(2, task.getDishType().getId());
            statement.setString(3, task.getLabel());
            statement.setString(4, task.getDescription());
            
            statement.execute();
            statement.close();
            
            System.out.println("Dishes successfully modified.");
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
        String sqlQuery = "delete from dishes where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Dishes successfully deleted.");

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
        
    	Dishes task = this.read(id, false);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Dishes read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from dishes where id=?";

        PreparedStatement statement;
        Dishes task = new Dishes();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                task.setPrice(results.getDouble("price"));
                
                Long dishTypesIdFromDB = results.getLong("dish_type_id");
                task.setLabel(results.getString("label"));
                task.setDescription(results.getString("description"));
                
                DishTypesDao dishTypesDao = new DishTypesDao();
                
                DishTypes dishTypes = dishTypesDao.read(dishTypesIdFromDB);
                
                task.setDishType(dishTypes);
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