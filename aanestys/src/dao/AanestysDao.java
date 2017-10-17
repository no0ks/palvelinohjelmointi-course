package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Aanestys;

public class AanestysDao {

	private Connection connect() {
		Connection con = null;
		String JDBCDriver = "com.mysql.jdbc.Driver";
    	String url = "jdbc:mysql://localhost:3306/palvelinohjelmointi?useSSL=false";
		
		try {
			Class.forName(JDBCDriver);
			con = DriverManager.getConnection(url, "nooks", "*****");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public Aanestys etsiAanestys(String kysymys) {
		String sql = "SELECT * FROM aanestykset WHERE kysymys = ?";
		Connection con = null;
		ResultSet rs = null;
		Aanestys aanestys = null;
		try {
			con = connect();
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, kysymys);
			rs = prep.executeQuery();
			if(rs != null && rs.next()) {
				aanestys = new Aanestys();
				aanestys.setAanestysId(rs.getInt("aanestysId"));
				aanestys.setKysymys(rs.getString("kysymys"));
				aanestys.setVastausKylla(rs.getInt("vastausKylla"));
				aanestys.setVastausEi(rs.getInt("vastausEi"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aanestys;
	}
	
	public Aanestys etsiAanestysIdlla(String aanestysId) {
		String sql = "SELECT * FROM aanestykset WHERE aanestysId = ?";
		Connection con = null;
		ResultSet rs = null;
		Aanestys aanestys = null;
		try {
			con = connect();
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, aanestysId);
			rs = prep.executeQuery();
			if(rs != null && rs.next()) {
				aanestys = new Aanestys();
				aanestys.setAanestysId(rs.getInt("aanestysId"));
				aanestys.setKysymys(rs.getString("kysymys"));
				aanestys.setVastausKylla(rs.getInt("vastausKylla"));
				aanestys.setVastausEi(rs.getInt("vastausEi"));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aanestys;
	}
	
	public ArrayList<Aanestys> listaaAanestykset() {
		String sql = "SELECT * FROM aanestykset ORDER BY AanestysId";
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Aanestys> aanestykset = new ArrayList<Aanestys>();
		try {
			con = connect();
			PreparedStatement prep = con.prepareStatement(sql);
			rs = prep.executeQuery();
			while(rs != null && rs.next()) {
				Aanestys aanestys = new Aanestys();
				aanestys.setAanestysId(rs.getInt("aanestysId"));
				aanestys.setKysymys(rs.getString("kysymys"));
				aanestys.setVastausKylla(rs.getInt("vastausKylla"));
				aanestys.setVastausEi(rs.getInt("vastausEi"));
				aanestykset.add(aanestys);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aanestykset;
	}
	
	public void lisaaAanestys(Aanestys aanestys) {
		String sql = "INSERT INTO aanestykset(kysymys, vastausKylla, vastausEi) "
				+ " VALUES(?,?,?)";
		Connection con = null;
		try {
			con = connect();
			PreparedStatement prep = con.prepareStatement(sql);
			prep.setString(1, aanestys.getKysymys());
			prep.setInt(2, 0);
			prep.setInt(3, 0);
			prep.executeUpdate();
			con.close();
		}catch (Exception e) {
        	System.out.println(e.getMessage());
        }
	}
	
	public void paivitaTulos(ArrayList<Aanestys> aanestykset) {
		String sql = "UPDATE aanestykset SET vastausKylla = ?, vastausEi = ? WHERE aanestysId = ?";
		Connection con=null;
		try {
			con = connect();
			PreparedStatement prep = con.prepareStatement(sql);
			for(int i=0;i<aanestykset.size();i++) {
				prep.setInt(1, aanestykset.get(i).getVastausKylla());
				prep.setInt(2, aanestykset.get(i).getVastausEi());
				prep.setInt(3, aanestykset.get(i).getAanestysId());
				prep.executeUpdate();
			}
			con.close();
		}catch (Exception e) {
        	System.out.println(e.getMessage());
        }
	}
	
}
