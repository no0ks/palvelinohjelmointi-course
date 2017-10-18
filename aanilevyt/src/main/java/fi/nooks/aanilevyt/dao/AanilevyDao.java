package fi.nooks.aanilevyt.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import fi.nooks.aanilevyt.bean.Aanilevy;

public class AanilevyDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void lisaaLevy(Aanilevy levy) {
		String sql = "INSERT INTO aanilevyt (levyNimi, artisti, julkaisuvuosi) VALUES(?,?,?)";
		Object[] parameters = new Object[] { levy.getLevyNimi(), levy.getArtisti(), levy.getJulkaisuvuosi() };
		jdbcTemplate.update(sql, parameters);
	}
	
	public void poistaLevy(Aanilevy levy) {
		String sql = "DELETE FROM aanilevyt WHERE levyId = ?";
		Object[] parameters = new Object[] { levy.getLevyId() };
		jdbcTemplate.update(sql, parameters);
	}

	public Aanilevy etsiLevy(int levyId) {
		String sql = "SELECT levyId, levyNimi, artisti, julkaisuvuosi FROM aanilevyt WHERE levyId = ?";
		Object[] parameters = new Object[] { levyId };
		RowMapper<Aanilevy> mapper = new LevyRowMapper();
		Aanilevy levy = jdbcTemplate.queryForObject(sql, parameters, mapper);
		return levy;
	}

	public List<Aanilevy> listaaLevyt() {
		String sql = "SELECT levyId, levyNimi, artisti, julkaisuvuosi FROM aanilevyt";
		RowMapper<Aanilevy> mapper = new LevyRowMapper();
		List<Aanilevy> levyt = jdbcTemplate.query(sql, mapper);
		return levyt;
	}
}
