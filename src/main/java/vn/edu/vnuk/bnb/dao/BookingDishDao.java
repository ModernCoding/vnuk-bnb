package vn.edu.vnuk.bnb.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import vn.edu.vnuk.bnb.model.BookingDish;
import vn.edu.vnuk.bnb.rowmapper.BookingsDishesRowMapper;

@Repository
public class BookingDishDao {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public BookingDishDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// CREATE
	public void create(BookingDish task) throws SQLException {

		String sqlQuery = "INSERT INTO bookings_dishes (booking_id, dishe_id) VALUES (?, ?)";

		try {
			System.out.println(String.format("%s new record in DB!",

					this.jdbcTemplate.update(sqlQuery, new Object[] { task.getBookingId(), task.getDishId() })));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	// READ (List of Tasks)
	public List<BookingDish> read(String bookingId, String dishId) throws SQLException {

		String sqlQuery = "select t01.id" + "     , t02.id as booking_id" + "     , t02.check_in "
				+ "     , t02.check_out " + "     , t02.quanlity_of_people " + "     , t03.id as dish_id"
				+ "     , t03.label " + "  from bookings_dishes t01, bookings t02, dishes t03"
				// + "join rooms_equipments t01 on t02.id = t01.room_id"
				+ " where t02.id = t01.booking_id" + " and t03.id = t01.dish_id";

		if (bookingId != null && dishId != null) {
			sqlQuery += String.format("   and t02.id = %s", bookingId, "   and t03.id = %s", dishId);
			sqlQuery += " order by t01.id asc;";
		}

		else {
			sqlQuery += " order by t03.id asc, t02.id asc, t01.id asc;";
		}

		try {

			return new BookingsDishesRowMapper().mapRows(this.jdbcTemplate.queryForList(sqlQuery));

		} catch (Exception e) {

			e.printStackTrace();

		}

		return null;

	}

	// READ (Single Task)
	public BookingDish read(Long id) throws SQLException {

		String sqlQuery = "select t01.id" + "     , t02.id as booking_id" + "     , t02.check_in "
				+ "     , t02.check_out " + "     , t02.quanlity_of_people " + "     , t03.id as dish_id"
				+ "     , t03.label " + "  from bookings_dishes t01, bookings t02, dishes t03" + "	 where t01.id = ?"
				+ "	 and t02.id = t01.booking_id" + "  and t03.id = t01.dish_id"
				+ " order by t03.id asc, t02.id asc, t01.id asc" + ";";

		return this.jdbcTemplate.queryForObject(sqlQuery, new Object[] { id }, new BookingsDishesRowMapper());

	}

	// UPDATE
	public void update(BookingDish task) throws SQLException {
		String sqlQuery = "update bookings_dishes set booking_id=?, dishe_id=? where id=?";

		try {
			this.jdbcTemplate.update(sqlQuery,

					new Object[] { task.getBookingId(), task.getDishId(), task.getId() });

			System.out.println("BookingsDishes successfully modified.");
		}

		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// DELETE
	public void delete(Long id) throws SQLException {
		String sqlQuery = "delete from bookings_dishes where id=?";

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

		BookingDish task = this.read(id);
//       task.setSmoking(true);
//        task.setDateOfCompletion(new Date(System.currentTimeMillis()));

		this.update(task);

	}

}