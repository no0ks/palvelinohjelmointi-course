package fi.nooks.tuntikirjanpito.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fi.nooks.tuntikirjanpito.bean.WorkHours;

@Repository
public class WorkHoursDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public List<WorkHours> findWorkHours(String username) {
		String sql = "SELECT h.entryId, h.username, h.hours, h.workDate, w.firstName, w.lastName FROM workHours h "
				+ "INNER JOIN webuser w ON h.username = w.username WHERE h.username=? ORDER BY h.workDate";
		Object[] parameters = new Object[] {username};
		RowMapper<WorkHours> mapper = new WorkHoursRowMapper();
		List<WorkHours> allWorkHours;
		try {
			allWorkHours = jdbcTemplate.query(sql, parameters, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new WorkHoursException(e);
		}
		return allWorkHours;
	}
	
	public WorkHours findWorkHours(WorkHours workHours) {
		String sql = "SELECT h.entryId, h.username, h.hours, h.workDate, w.firstName, w.lastName FROM workHours h "
				+ "INNER JOIN webuser w ON h.username = w.username WHERE h.username=? AND h.workDate=?";
		String username = workHours.getUsername();
		final Calendar calendar = workHours.getWorkDate();
		final java.sql.Date workDate = new java.sql.Date(calendar.getTime().getTime());
		Object[] parameters = new Object[] {username, workDate};
		RowMapper<WorkHours> mapper = new WorkHoursRowMapper();
		try {
			workHours = jdbcTemplate.queryForObject(sql, parameters, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new WorkHoursException(e);
		}
		return workHours;
	}
	
	public List<WorkHours> listAllWorkHours() {
		String sql = "SELECT h.entryId, h.username, h.hours, h.workDate, w.firstName, w.lastName FROM workHours h "
				+ "INNER JOIN webuser w ON h.username = w.username ORDER BY h.workDate";
		RowMapper<WorkHours> mapper = new WorkHoursRowMapper();
		List<WorkHours> allWorkHours;
		try {
			allWorkHours = jdbcTemplate.query(sql, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new WorkHoursException(e);
		}
		return allWorkHours;
	}
	
	public void addWorkHours(final WorkHours workHours) {
		final String sql = "INSERT INTO workHours (username, hours, workDate) VALUES (?,?,?)";
		final Calendar calendar = workHours.getWorkDate();
		final java.sql.Date workDate = new java.sql.Date(calendar.getTime().getTime());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setString(1, workHours.getUsername());
				ps.setBigDecimal(2, workHours.getHours());
				ps.setDate(3, workDate);
				return ps;
			}
		}, keyHolder);
		workHours.setEntryId(keyHolder.getKey().intValue());
	}
	
	public WorkHours listPersonHours(String username) {
		String sql = "SELECT h.username, SUM(h.hours) AS sumHours, w.firstName, w.lastName FROM workHours h "
				+ "INNER JOIN webuser w ON h.username = w.username WHERE h.username=?";
		Object[] parameters = new Object[] {username};
		RowMapper<WorkHours> mapper = new PersonHoursRowMapper();
		WorkHours personHours;
		try {
			personHours = jdbcTemplate.queryForObject(sql, parameters, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new WorkHoursException(e);
		}
		return personHours;
	}
	
	public List<WorkHours> listAllPersonHours() {
		String sql = "SELECT h.username, SUM(h.hours) AS sumHours, w.firstName, w.lastName FROM workHours h "
				+ "INNER JOIN webuser w ON h.username = w.username GROUP BY h.username ORDER BY h.username";
		RowMapper<WorkHours> mapper = new PersonHoursRowMapper();
		List<WorkHours> peopleHours;
		try {
			peopleHours = jdbcTemplate.query(sql, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new WorkHoursException(e);
		}
		return peopleHours;
	}
	
}
