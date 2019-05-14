package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.IdentificationTypes;

public class IdentificationTypesDao {
	
    private Connection connection;

    public IdentificationTypesDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public IdentificationTypesDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(IdentificationTypes task) throws SQLException{

        String sqlQuery = "insert into identification_types (label) "
                        +	"values (?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setString(1, task.getLabel());

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
    public List<IdentificationTypes> read() throws SQLException {

        String sqlQuery = "select * from identification_types";
        PreparedStatement statement;
        List<IdentificationTypes> tasks = new ArrayList<IdentificationTypes>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	IdentificationTypes task = new IdentificationTypes();
                task.setId(results.getLong("id"));
                task.setLabel(results.getString("label"));

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
    public IdentificationTypes read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(IdentificationTypes task) throws SQLException {
        String sqlQuery = "update identification_types set label=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, task.getLabel());
            
            statement.setLong(2, task.getId());
            statement.execute();
            statement.close();
            
            System.out.println("IdentificationTypes successfully modified.");
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
        String sqlQuery = "delete from identification_types where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("IdentificationTypes successfully deleted.");

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
        
    	IdentificationTypes task = this.read(id, false);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private IdentificationTypes read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from identification_types where id=?";

        PreparedStatement statement;
        IdentificationTypes task = new IdentificationTypes();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                task.setLabel(results.getString("label"));

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