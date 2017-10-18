package fi.nooks.pistekirjanpito.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import fi.nooks.pistekirjanpito.bean.DemoPiste;

public class PisteRowMapper implements RowMapper<DemoPiste>{

	public DemoPiste mapRow(ResultSet rs, int rowNo) throws SQLException {
		DemoPiste piste = new DemoPiste();
		piste.setOppilasnro(rs.getString("oppilasnro"));
		piste.setArvosana(rs.getInt("arvosana"));
		return piste;
	}

}
