package fi.nooks.korttiohjelmisto.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.nooks.korttiohjelmisto.bean.Osoite;
import fi.nooks.korttiohjelmisto.dao.KorttiDao;

@WebServlet("/osoitteet")
public class KorttiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public KorttiServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Osoite> osoitteet = new ArrayList<Osoite>();
		KorttiDao kDao;
		String viesti = "";
		try {
			kDao = new KorttiDao();
			osoitteet = kDao.listaaOsoitteet();
		} catch (Exception e) {
			e.printStackTrace();
			viesti = e.getMessage();
		}
		if(viesti!=null && !"".equals(viesti)) {
			request.setAttribute("viesti", viesti);
		}
		request.setAttribute("osoitteet", osoitteet);
		request.getRequestDispatcher("osoitteet.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Osoite> osoitteet = new ArrayList<Osoite>();
		Osoite osoite;
		KorttiDao kDao;
		String viesti = "";
		String id = request.getParameter("poista");
		try {
			kDao = new KorttiDao();
			if(id==null || id.equals("")) {
				String etunimi = request.getParameter("etunimi");
				String sukunimi = request.getParameter("sukunimi");
				String katuosoite = request.getParameter("katuosoite");
				String postinro = request.getParameter("postinro");
				String postitmp = request.getParameter("postitmp");
				String postitoim = kDao.etsiPostitmp(postinro);
				if("".equals(etunimi) || "".equals(sukunimi) || "".equals(katuosoite) || "".equals(postinro) 
				|| "".equals(postitmp)) {
					viesti = "Täytä kaikki kentät.";
				} else {
					osoite = new Osoite();
					osoite.setEtunimi(etunimi);
					osoite.setSukunimi(sukunimi);
					osoite.setKatuosoite(katuosoite);
					osoite.setPostinro(postinro);
					if("".equals(postitoim)) {	
						kDao.lisaaPostitmp(postinro, postitmp);
						osoite.setPostitmp(postitmp);
					} else {
						osoite.setPostitmp(postitoim);
					}
					kDao.lisaaOsoite(osoite);
					viesti = "Uusi osoite lisätty kantaan.";
				}
			} else {
				osoite = kDao.etsiOsoite(id);
				kDao.poistaOsoite(osoite);
				viesti = "Henkilön " + osoite.getEtunimi() + " " + osoite.getSukunimi() + " osoitetiedot poistettu.";
			}
			osoitteet = kDao.listaaOsoitteet();
		} catch (Exception e) {
			e.printStackTrace();
			viesti = e.getMessage();
		}
		request.setAttribute("osoitteet", osoitteet);
		request.setAttribute("viesti", viesti);
		request.getRequestDispatcher("osoitteet.jsp").forward(request, response);
	}

}
