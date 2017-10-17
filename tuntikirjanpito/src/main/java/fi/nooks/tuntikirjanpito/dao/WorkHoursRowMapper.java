package fi.nooks.tuntikirjanpito.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.springframework.jdbc.core.RowMapper;

import fi.nooks.tuntikirjanpito.bean.WorkHours;

public class WorkHoursRowMapper implements RowMapper<WorkHours> {

	public WorkHours mapRow(ResultSet rs, int row) throws SQLException {
		WorkHours wh = new WorkHours();
		wh.setEntryId(rs.getInt("entryId"));
		wh.setUsername(rs.getString("username"));
		wh.setHours(rs.getBigDecimal("hours"));
		Calendar workDate = Calendar.getInstance();
        workDate.setTime(rs.getDate("workDate"));
		wh.setWorkDate(workDate);
		wh.setFirstName(rs.getString("firstName"));
		wh.setLastName(rs.getString("lastName"));
		return wh;
	}

}