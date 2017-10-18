package fi.nooks.korttiohjelmisto.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fi.nooks.korttiohjelmisto.dao.KorttiDao;

public class DatabaseTest {
	
	private KorttiDao kDao;

	@Before
	public void createDao() throws Exception {
		this.kDao = new KorttiDao();
	}
	@Test
	public void testEtsiPostitmp() {
		String actual = "";
		String expected = "Espoo";
		try {
			actual = kDao.etsiPostitmp("02320");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
	@Test
	public void testEtsiOsoite() {
		String actual = "";
		String expected = "Laura";
		try {
			actual = kDao.listaaOsoitteet().get(0).getEtunimi();
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(expected, actual);
	}
	
}
