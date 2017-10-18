package fi.nooks.korttiohjelmisto.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import fi.nooks.korttiohjelmisto.bean.Osoite;

public class KorttiDao {

	public KorttiDao() throws Exception {
		try {
			Class.forName(DBConnectionProperties.getInstance().getProperty("db.driver")).newInstance();
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Tietokannan ajurin lataus epäonnistui.", e);
		}
	}
	
	private Connection connect() throws Exception {
		try {
			return DriverManager.getConnection(
					DBConnectionProperties.getInstance().getProperty("db.url"), 
					DBConnectionProperties.getInstance().getProperty("db.username"),
					DBConnectionProperties.getInstance().getProperty("db.password"));
		} catch (Exception e) {
			throw new Exception("Tietokantayhteyden avaaminen epäonnistui.", e);
		}
	}
	
	public ArrayList<Osoite> listaaOsoitteet() throws Exception {
		String sql = "SELECT henkiloId, etunimi, sukunimi, katuosoite, postinro, postitmp "
		+ "FROM osoitteet NATURAL JOIN posti ORDER by sukunimi";
		Connection connection = null;
		ResultSet rs = null;
		ArrayList<Osoite> osoitteet = new ArrayList<Osoite>();
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			rs = prep.executeQuery();
			while(rs!=null && rs.next()) {
				Osoite osoite = new Osoite();
				osoite.setHenkiloId(rs.getInt("henkiloId"));
				osoite.setEtunimi(rs.getString("etunimi"));
				osoite.setSukunimi(rs.getString("sukunimi"));
				osoite.setKatuosoite(rs.getString("katuosoite"));
				osoite.setPostinro(rs.getString("postinro"));
				osoite.setPostitmp(rs.getString("postitmp"));
				osoitteet.add(osoite);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Osoitteiden haku epäonnistui.");
		}
		return osoitteet;
	}
	
	public Osoite etsiOsoite(String henkiloId) throws Exception {
		Osoite osoite = null;
		String sql = "SELECT henkiloId, etunimi, sukunimi, katuosoite, postinro, postitmp "
		+ "FROM osoitteet NATURAL JOIN posti WHERE henkiloId = ?";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, henkiloId);
			rs = prep.executeQuery();
			if(rs!=null && rs.next()) {
				osoite = new Osoite();
				osoite.setHenkiloId(rs.getInt("henkiloId"));
				osoite.setEtunimi(rs.getString("etunimi"));
				osoite.setSukunimi(rs.getString("sukunimi"));
				osoite.setKatuosoite(rs.getString("katuosoite"));
				osoite.setPostinro(rs.getString("postinro"));
				osoite.setPostitmp(rs.getString("postitmp"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Osoitteen haku epäonnistui.");
		}
		return osoite;
	}
	
	public String etsiPostitmp(String postinro) throws Exception {
		String postitmp = "";
		String sql = "SELECT postitmp FROM posti WHERE postinro = ?";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, postinro);
			rs = prep.executeQuery();
			if(rs!=null && rs.next()) {
				postitmp = rs.getString("postitmp");
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Postitoimipaikan haku epäonnistui.");
		}
		return postitmp;
	}
	
	public void lisaaOsoite(Osoite osoite) throws Exception {
		String sql = "INSERT INTO osoitteet(etunimi, sukunimi, katuosoite, postinro) "
		+ "VALUES(?,?,?,?)";
		Connection connection = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, osoite.getEtunimi());
			prep.setString(2, osoite.getSukunimi());
			prep.setString(3, osoite.getKatuosoite());
			prep.setString(4, osoite.getPostinro());
			prep.executeUpdate();
			connection.close();
		}catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Osoitteen lisäys epäonnistui.");
        }
	}
	
	public void lisaaPostitmp(String postinro, String postitmp) throws Exception {
		String sql = "INSERT INTO posti(postinro, postitmp) "
		+ "VALUES(?,?)";
		Connection connection = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, postinro);
			prep.setString(2, postitmp);
			prep.executeUpdate();
			connection.close();
		}catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Postitoimipaikan lisäys epäonnistui");
        }
	}
	
	public void poistaOsoite(Osoite osoite) throws Exception {
		String sql = "DELETE FROM osoitteet WHERE henkiloId = ?";
		Connection connection = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, osoite.getHenkiloId());
			prep.executeUpdate();
			connection.close();
		}catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Osoitteen poistaminen epäonnistui.");
        }
	}
	
}
