package fi.nooks.pistekirjanpito.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fi.nooks.pistekirjanpito.bean.DemoPiste;

public class PisteDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void lisaaPiste(DemoPiste piste) {
		String sql = "INSERT INTO demoPisteet (oppilasnro, arvosana) VALUES(?,?)";
		Object[] parameters = new Object[] { piste.getOppilasnro(), piste.getArvosana() };
		jdbcTemplate.update(sql, parameters);
	}
	
	public void paivitaPiste(DemoPiste piste) {
		String sql = "UPDATE demoPisteet SET arvosana = ? WHERE oppilasnro = ?";
		Object[] parameters = new Object[] { piste.getArvosana(), piste.getOppilasnro() };
		jdbcTemplate.update(sql, parameters);
	}

	public DemoPiste etsiPiste(String oppilasnro) {
		String sql = "SELECT oppilasnro, arvosana FROM demoPisteet WHERE oppilasnro = ?";
		Object[] parameters = new Object[] { oppilasnro };
		RowMapper<DemoPiste> mapper = new PisteRowMapper();
		DemoPiste piste = jdbcTemplate.queryForObject(sql, parameters, mapper);
		return piste;
	}

	public List<DemoPiste> listaaKaikki() {
		String sql = "SELECT oppilasnro, arvosana FROM demoPisteet";
		RowMapper<DemoPiste> mapper = new PisteRowMapper();
		List<DemoPiste> pisteet = jdbcTemplate.query(sql, mapper);
		return pisteet;
	}
}
