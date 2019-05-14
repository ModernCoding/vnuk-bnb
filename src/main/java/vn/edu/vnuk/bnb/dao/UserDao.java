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
import vn.edu.vnuk.bnb.model.Countries;
import vn.edu.vnuk.bnb.model.IdentificationTypes;
import vn.edu.vnuk.bnb.model.UserTypes;
import vn.edu.vnuk.bnb.model.Users;
public class UserDao {
	
    private Connection connection;

    public UserDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public UserDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(Users task) throws SQLException{

        String sqlQuery = "insert into users (user_type_id, first_name, middle_name, last_name, address, email, phone, identification_number, create_at, update_at, identification_type_id, country_id) "
                        +	"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, task.getUserTypes().getId());
                statement.setString(2, task.getFirstName());
                statement.setString(3, task.getMiddleName());
                statement.setString(4, task.getLastName());
                statement.setString(5, task.getAddress());
                statement.setString(6, task.getEmail());
                statement.setString(7, task.getPhone());
                statement.setInt(8, task.getIdentificationNumber());
                statement.setDate(
                		9,
                		task.getCreateAt() == null ? null : new Date(task.getCreateAt().getTimeInMillis())
                	);
                statement.setDate(
                		10,
                		task.getUpdateAt() == null ? null : new Date(task.getUpdateAt().getTimeInMillis())
                	);
//                statement.setDate(10, task.getUpdateAt());
                statement.setLong(11, task.getIdentificationTypes().getId());
                statement.setLong(12, task.getCountry().getId());
                
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
    public List<Users> read() throws SQLException {

        String sqlQuery = "select * from rooms_equipments";
        PreparedStatement statement;
        List<Users> tasks = new ArrayList<Users>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	Users task = new Users();
                task.setId(results.getLong("id"));
                
                
                Long userTypeIdFromDB = results.getLong("user_type_id");
               
                UserTypesDao usertypesDao = new UserTypesDao();
                
                UserTypes usertypes = usertypesDao.read(userTypeIdFromDB);
                
                task.setUserTypes(usertypes);
                
                task.setFirstName(results.getString("first_name"));
                task.setMiddleName(results.getString("middle_name"));
                task.setLastName(results.getString("last_name"));
                task.setAddress(results.getString("address"));
                task.setEmail(results.getString("email"));
                task.setPhone(results.getString("phone"));
                
                Date dateOfCreate = results.getDate("create_at");
                
                if (dateOfCreate == null){
                    task.setCreateAt(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfCreate);
                    task.setCreateAt(date);
                }
                
                Date dateOfUpdate = results.getDate("update_at");
                
                if (dateOfUpdate == null){
                    task.setUpdateAt(null);;
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfUpdate);
                    task.setUpdateAt(date);;
                }
                
                Long identificationTypeIdFromDB = results.getLong("identification_type_id");
                
                IdentificationTypesDao identificationtypesDao = new IdentificationTypesDao();
                
                IdentificationTypes identificationtypes = identificationtypesDao.read(identificationTypeIdFromDB);
                
                task.setIdentificationTypes(identificationtypes);;
                
                Long countryIdFromDB = results.getLong("country_id");
                
                CountriesDao countriesDao = new CountriesDao();
                
                Countries countries = countriesDao.read(countryIdFromDB);
                
                task.setCountry(countries);
                
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
    public Users read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(Users task) throws SQLException {
        String sqlQuery = "update users set user_type_id=?, first_name=?, middle_name=?, last_name=?, address=?, email=?, phone=?, identification_number=?,create_at=?, update_at=?, identification_type_id=?, country_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
     
            statement.setLong(1, task.getUserTypes().getId());
            statement.setString(2, task.getFirstName());
            statement.setString(3, task.getMiddleName());
            statement.setString(4, task.getLastName());
            statement.setString(5, task.getAddress());
            statement.setString(6, task.getEmail());
            statement.setString(7, task.getPhone());
            statement.setInt(8, task.getIdentificationNumber());
            statement.setDate(
            		9,
            		task.getCreateAt() == null ? null : new Date(task.getCreateAt().getTimeInMillis())
            	);
            statement.setDate(
            		10,
            		task.getUpdateAt() == null ? null : new Date(task.getUpdateAt().getTimeInMillis())
            	);
            statement.setLong(11, task.getIdentificationTypes().getId());
            statement.setLong(12, task.getCountry().getId());
            statement.execute();
            statement.close();
            
            System.out.println("Users successfully modified.");
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
        String sqlQuery = "delete from users where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("Users successfully deleted.");

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
        
    	Users task = this.read(id, false);
//        task.setIsComplete(true);
        task.setCreateAt(Calendar.getInstance());
        task.setUpdateAt(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private Users read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from users where id=?";

        PreparedStatement statement;
        Users task = new Users();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                Long userTypeIdFromDB = results.getLong("user_type_id");
                
                UserTypesDao usertypesDao = new UserTypesDao();
                
                UserTypes usertypes = usertypesDao.read(userTypeIdFromDB);
                
                task.setUserTypes(usertypes);
                
                task.setFirstName(results.getString("first_name"));
                task.setMiddleName(results.getString("middle_name"));
                task.setLastName(results.getString("last_name"));
                task.setAddress(results.getString("address"));
                task.setEmail(results.getString("email"));
                task.setPhone(results.getString("phone"));
                
                Date dateOfCreate = results.getDate("create_at");
                
                if (dateOfCreate == null){
                    task.setCreateAt(null);
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfCreate);
                    task.setCreateAt(date);
                }
                
                Date dateOfUpdate = results.getDate("update_at");
                
                if (dateOfUpdate == null){
                    task.setUpdateAt(null);;
                }
                
                else{
                    Calendar date = Calendar.getInstance();
                    date.setTime(dateOfUpdate);
                    task.setUpdateAt(date);;
                }
                
                Long identificationTypeIdFromDB = results.getLong("identification_type_id");
                
                IdentificationTypesDao identificationtypesDao = new IdentificationTypesDao();
                
                IdentificationTypes identificationtypes = identificationtypesDao.read(identificationTypeIdFromDB);
                
                task.setIdentificationTypes(identificationtypes);;
                
                Long countryIdFromDB = results.getLong("country_id");
                
                CountriesDao countriesDao = new CountriesDao();
                
                Countries countries = countriesDao.read(countryIdFromDB);
                
                task.setCountry(countries);
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