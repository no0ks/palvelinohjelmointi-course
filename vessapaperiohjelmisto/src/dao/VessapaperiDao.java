package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.PaperinOstaja;

public class VessapaperiDao {
	
	private Connection connect() throws Exception {
		Connection connection = null;
		String JDBCDriver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/palvelinohjelmointi?useSSL=false";
    	String user = "nooks";
    	String password = "*****";
		try {
			Class.forName(JDBCDriver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Tietokantayhteyden muodostaminen epäonnistui");
		}
		return connection;
	}
	
	public ArrayList<PaperinOstaja> listaaOstajat() throws Exception {
		String sql = "SELECT ostajaId, ostajaNimi, ostettuMaara, paivitetty, "
		+ "(SELECT MAX(po.ostettuMaara) AS maxOstos FROM paperinOstajat AS po) - ostettuMaara AS ostettava "
		+ "FROM paperinOstajat ORDER BY ostajaId";
		Connection connection = null;
		ResultSet rs = null;
		ArrayList<PaperinOstaja> ostajat = new ArrayList<PaperinOstaja>();
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			rs = prep.executeQuery();
			while(rs!=null && rs.next()) {
				PaperinOstaja ostaja = new PaperinOstaja();
				ostaja.setOstajaId(rs.getInt("ostajaId"));
				ostaja.setOstajaNimi(rs.getString("ostajaNimi"));
				ostaja.setOstettuMaara(rs.getInt("ostettuMaara"));
				ostaja.setOstettava(rs.getInt("ostettava"));
				ostaja.setPaivitetty(rs.getTimestamp("paivitetty"));
				ostajat.add(ostaja);
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Ostajien haku epäonnistui");
		}
		return ostajat;
	}
	
	public PaperinOstaja etsiOstaja(String ostajaId) throws Exception {
		PaperinOstaja ostaja = null;
		String sql = "SELECT ostajaId, ostajaNimi, ostettuMaara, paivitetty, "
		+ "(SELECT MAX(po.ostettuMaara) AS maxOstos FROM paperinOstajat AS po) - ostettuMaara AS ostettava "
		+ "FROM paperinOstajat WHERE ostajaId = ?";
		Connection connection = null;
		ResultSet rs = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, ostajaId);
			rs = prep.executeQuery();
			if(rs!=null && rs.next()) {
				ostaja = new PaperinOstaja();
				ostaja.setOstajaId(rs.getInt("ostajaId"));
				ostaja.setOstajaNimi(rs.getString("ostajaNimi"));
				ostaja.setOstettuMaara(rs.getInt("ostettuMaara"));
				ostaja.setOstettava(rs.getInt("ostettava"));
				ostaja.setPaivitetty(rs.getTimestamp("paivitetty"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Ostajan haku epäonnistui");
		}
		return ostaja;
	}
	
	public void paivitaTilanne(PaperinOstaja ostaja) throws Exception {
		String sql = "UPDATE paperinOstajat SET ostettuMaara = ostettuMaara + ?, paivitetty = NOW() "
		+ "WHERE ostajaId = ?";
		Connection connection = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, ostaja.getOstettuMaara());
			prep.setInt(2, ostaja.getOstajaId());
			prep.executeUpdate();
			connection.close();
		}catch (Exception e) {
        	e.printStackTrace();
        	throw new Exception("Ostajan päivitys epäonnistui");
        }
	}
	
	public void nollaaTilanne() throws Exception {
		String sql = "UPDATE paperinOstajat SET ostettuMaara = 0, paivitetty = NOW()";
		Connection connection = null;
		try {
			connection = connect();
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.executeUpdate();
			connection.close();
		}catch (Exception e) {
			e.printStackTrace();
        	throw new Exception("Nollaus epäonnistui");
        }
	}
}
