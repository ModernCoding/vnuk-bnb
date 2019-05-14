package vn.edu.vnuk.bnb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import vn.edu.vnuk.bnb.jdbc.ConnectionFactory;
import vn.edu.vnuk.bnb.model.Rooms;
import vn.edu.vnuk.bnb.model.RoomsEquipments;
import vn.edu.vnuk.bnb.model.Equipment;
public class RoomsEquipmentsDao {
	
    private Connection connection;

    public RoomsEquipmentsDao(){
        this.connection = new ConnectionFactory().getConnection();
    }

    public RoomsEquipmentsDao(Connection connection){
        this.connection = connection;
    }


    //  CREATE
    public void create(RoomsEquipments task) throws SQLException{

        String sqlQuery = "insert into rooms_equipments (room_id, equipment_id) "
                        +	"values (?, ?)";

        PreparedStatement statement;

        try {
                statement = connection.prepareStatement(sqlQuery);

                //	Replacing "?" through values
                statement.setLong(1, task.getRoom().getId());
                statement.setLong(2, task.getEquipment().getId());


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
    public List<RoomsEquipments> read() throws SQLException {

        String sqlQuery = "select * from rooms_equipments";
        PreparedStatement statement;
        List<RoomsEquipments> tasks = new ArrayList<RoomsEquipments>();

        try {

            statement = connection.prepareStatement(sqlQuery);

            // 	Executing statement
            ResultSet results = statement.executeQuery();
            
            while(results.next()){

            	RoomsEquipments task = new RoomsEquipments();
                task.setId(results.getLong("id"));
                
                Long roomsIdFromDB = results.getLong("room_id");
                Long equipmentsIdFromDB = results.getLong("equipment_id");
                
                
                RoomsDao roomsDao = new RoomsDao();
                EquipmentDao equipmentsDao = new EquipmentDao();
                
                Rooms rooms = roomsDao.read(roomsIdFromDB);
                Equipment equipments =equipmentsDao.read(equipmentsIdFromDB);
                
                task.setRoom(rooms);
                task.setEquipment(equipments);
                
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
    public RoomsEquipments read(Long id) throws SQLException{
        return this.read(id, true);
    }  

    
    //  UPDATE
    public void update(RoomsEquipments task) throws SQLException {
        String sqlQuery = "update rooms_equipments set rooms_id=?, equipments_id=? where id=?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
     
            statement.setLong(1, task.getRoom().getId());
            statement.setLong(2, task.getEquipment().getId());

            
            statement.execute();
            statement.close();
            
            System.out.println("RoomsEquipments successfully modified.");
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
        String sqlQuery = "delete from rooms_equipments where id=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            
            System.out.println("RoomsEquipments successfully deleted.");

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
        
    	RoomsEquipments task = this.read(id, false);
//        task.setIsComplete(true);
//        task.setDateOfCompletion(Calendar.getInstance());
        
        this.update(task);
        
    }
  
    
    //  PRIVATE
    
    @SuppressWarnings("finally")
    private RoomsEquipments read(Long id, boolean closeAfterUse) throws SQLException{

        String sqlQuery = "select * from rooms_equipments where id=?";

        PreparedStatement statement;
        RoomsEquipments task = new RoomsEquipments();

        try {
            statement = connection.prepareStatement(sqlQuery);

            //	Replacing "?" through values
            statement.setLong(1, id);

            // 	Executing statement
            ResultSet results = statement.executeQuery();

            if(results.next()){

                task.setId(results.getLong("id"));
                Long roomsIdFromDB = results.getLong("room_id");
                Long equipmentsIdFromDB = results.getLong("equipment_id");
                
                
                RoomsDao roomsDao = new RoomsDao();
                EquipmentDao equipmentsDao = new EquipmentDao();
                
                Rooms rooms = roomsDao.read(roomsIdFromDB);
                Equipment equipments =equipmentsDao.read(equipmentsIdFromDB);
                
                task.setRoom(rooms);
                task.setEquipment(equipments);
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