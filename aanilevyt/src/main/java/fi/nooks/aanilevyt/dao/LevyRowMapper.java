package fi.nooks.aanilevyt.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.nooks.aanilevyt.bean.Aanilevy;


public class LevyRowMapper implements RowMapper<Aanilevy> {

	public Aanilevy mapRow(ResultSet rs, int rowNum) throws SQLException {
		Aanilevy levy = new Aanilevy();
		levy.setLevyId(rs.getInt("levyId"));
		levy.setLevyNimi(rs.getString("levyNimi"));
		levy.setArtisti(rs.getString("artisti"));
		levy.setJulkaisuvuosi(rs.getInt("julkaisuvuosi"));
		return levy;
	}

}
