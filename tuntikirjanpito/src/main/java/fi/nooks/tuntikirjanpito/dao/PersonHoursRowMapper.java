package fi.nooks.tuntikirjanpito.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.nooks.tuntikirjanpito.bean.WorkHours;

public class PersonHoursRowMapper implements RowMapper<WorkHours> {

	public WorkHours mapRow(ResultSet rs, int row) throws SQLException {
		WorkHours ph = new WorkHours();
		ph.setUsername(rs.getString("username"));
		ph.setHours(rs.getBigDecimal("sumHours"));
		ph.setFirstName(rs.getString("firstName"));
		ph.setLastName(rs.getString("lastName"));
		return ph;
	}
}
