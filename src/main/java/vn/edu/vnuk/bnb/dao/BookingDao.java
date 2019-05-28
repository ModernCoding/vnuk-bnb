package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.Booking;
import vn.edu.vnuk.bnb.rowmapper.BookingsRowMapper;

@Repository
public class BookingDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookingDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// CREATE
	public void create(Booking task) throws SQLException {

		String sqlQuery = "INSERT INTO bookings (room_id, user_id, check_in, check_out, quanlity_of_people) VALUES (?, ?, ?, ?, ?)";

		try {
			System.out.println(String.format("%s new record in DB!",

					this.jdbcTemplate.update(sqlQuery, new Object[] { task.getRoomId(), task.getUserId(),
							task.getCheckIn(), task.getCheckOut(), task.getQuanlity() })));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// READ (List of Tasks)
	public List<Booking> read(String roomId, String userId) throws SQLException {

		String sqlQuery = "select t01.id" + "     , t02.id as user_id" + "     , t02.first_name "
				+ "     , t02.middle_name " + "     , t02.last_name " + "     , t02.address " + "     , t02.email "
				+ "     , t02.phone " + "     , t02.identification_number " + "     , t02.create_at "
				+ "     , t02.update_at " + "     , t03.id as room_id" + "     , t03.price" + "     , t03.beds"
				+ "     , t03.room_number" + "     , t03.is_smoking" + "     , t01.check_in " + "     , t01.check_out "
				+ "     , t01.quanlity_of_people " + "  from bookings t01, users t02, rooms t03"
				// + "join rooms_equipments t01 on t02.id = t01.room_id"
				+ " where t02.id = t01.user_id" + " and t03.id = t01.room_id";

		if (roomId != null && userId != null) {
			sqlQuery += String.format("   and t02.id = %s", userId, "   and t03.id = %s", roomId);
			sqlQuery += " order by t01.id asc;";
		}

		else {
			sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
		}

		try {

			return new BookingsRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	// READ (Single Task)
	public Booking read(Long id) throws SQLException {

		String sqlQuery = "select t01.id" + "     , t02.id as user_id" + "     , t02.first_name "
				+ "     , t02.middle_name " + "     , t02.last_name " + "     , t02.address " + "     , t02.email "
				+ "     , t02.phone " + "     , t02.identification_number " + "     , t02.create_at "
				+ "     , t02.update_at " + "     , t03.id as room_id" + "     , t03.price" + "     , t03.beds"
				+ "     , t03.room_number" + "     , t03.is_smoking" + "     , t01.check_in " + "     , t01.check_out "
				+ "     , t01.quanlity_of_people " + "  from bookings t01, users t02, rooms t03"
				+ "	 where t01.id = ?" + "  and t02.id = t01.user_id" + "  and t03.id = t01.room_id"
				+ " order by t03.id asc, t02.id asc, t01.id asc" + ";";

		return this.jdbcTemplate.queryForObject(sqlQuery, new Object[] { id }, new BookingsRowMapper());

	}

	// UPDATE
	public void update(Booking task) throws SQLException {
		String sqlQuery = "update bookings set room_id=?, user_id=?, check_in=?, check_out=?, quanlity_of_people=? where id=?";

		try {
			this.jdbcTemplate.update(sqlQuery,

					new Object[] { task.getRoomId(), task.getUserId(), task.getCheckIn(), task.getCheckOut(),
							task.getQuanlity(), task.getId() });

			System.out.println("Bookings successfully modified.");
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// DELETE
	public void delete(Long id) throws SQLException {
		String sqlQuery = "delete from bookings where id=?";

		try {

			System.out.println(String.format("%s record successfully removed from DB!",

					this.jdbcTemplate.update(sqlQuery, new Object[] { id })));

		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// OTHERS

	public void complete(Long id) throws SQLException {

		Booking task = this.read(id);
//        task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));

		this.update(task);

	}

}