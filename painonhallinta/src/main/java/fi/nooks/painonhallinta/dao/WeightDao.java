package fi.nooks.painonhallinta.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fi.nooks.painonhallinta.bean.Weight;

@Repository
public class WeightDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Weight findEntry(int entryId) {
		String sql = "SELECT entryId, kg, entryDate FROM weightDiary WHERE entryId = ?";
		Object[] parameters = new Object[] {entryId};
		RowMapper<Weight> mapper = new WeightRowMapper();
		Weight w;
		try {
			w = jdbcTemplate.queryForObject(sql, parameters, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new WeightNotFoundException(e);
		}
		return w;
	}
	
	public List<Weight> getAllEntries() {
		String sql = "SELECT entryId, kg, entryDate FROM weightDiary";
		RowMapper<Weight> mapper = new WeightRowMapper();
		List<Weight> entries = jdbcTemplate.query(sql, mapper);
		return entries;
	}
	
	public void addEntry(Weight w) {
		final String sql = "INSERT INTO weightDiary (kg, entryDate) VALUES(?,NOW())";
		final double kg = w.getKg();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "id" });
				ps.setDouble(1, kg);
				return ps;
			}
		}, keyHolder);
		w.setEntryId(keyHolder.getKey().intValue());
	}
}
