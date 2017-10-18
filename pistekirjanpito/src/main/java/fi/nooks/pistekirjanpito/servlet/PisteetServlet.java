package fi.nooks.pistekirjanpito.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fi.nooks.pistekirjanpito.bean.DemoPiste;
import fi.nooks.pistekirjanpito.dao.PisteDao;

@WebServlet("/pisteet")
public class PisteetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String viesti = "";
	
	@Autowired
	private PisteDao dao;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
    public PisteDao getDao() {
		return dao;
	}

	public void setDao(PisteDao dao) {
		this.dao = dao;
	}

	public PisteetServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		drawTable(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oppilasnro = request.getParameter("oppilasnro");
		
		DemoPiste piste;
		if(oppilasnro!=null && !"".equals(oppilasnro)) {
			int arvosana = -1;
			try {
				arvosana = Integer.parseInt(request.getParameter("arvosana"));
				dao.etsiPiste(oppilasnro);
				viesti = "Oppilaalla on jo arvosana.";
			} catch (EmptyResultDataAccessException e) {
				piste = new DemoPiste(oppilasnro, arvosana);
				dao.lisaaPiste(piste);
				viesti = "Arvosana lisätty.";
			} catch (Exception e) {
				viesti = "Lisääminen epäonnistui.";
				e.printStackTrace();
			}
		} else {
			viesti = "Syötä oppilasnro.";
		}
		request.setAttribute("viesti", viesti);
		drawTable(request, response);
	}
	
	private void drawTable(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<DemoPiste> pisteet;
		String adminFlag = (String) request.getSession().getAttribute("admin");
		if(adminFlag!=null && "Y".equals(adminFlag)) {
			pisteet = dao.listaaKaikki();
		} else {
			pisteet = new ArrayList<DemoPiste>();
			pisteet.add(dao.etsiPiste((String) request.getSession().getAttribute("kayttajatunnus")));
		}
		request.setAttribute("pisteet", pisteet);
		request.getRequestDispatcher("WEB-INF/pisteet.jsp").forward(request, response);
	}

}
