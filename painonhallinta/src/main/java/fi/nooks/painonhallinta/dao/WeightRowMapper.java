package fi.nooks.painonhallinta.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.nooks.painonhallinta.bean.Weight;

public class WeightRowMapper implements RowMapper<Weight> {

	public Weight mapRow(ResultSet rs, int row) throws SQLException {
		Weight w = new Weight();
		w.setEntryId(rs.getInt("entryId"));
		w.setKg(rs.getDouble("kg"));
		w.setEntryDate(rs.getTimestamp("entryDate"));
		return w;
	}

}