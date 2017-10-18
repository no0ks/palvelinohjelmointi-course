package fi.nooks.pistekirjanpito.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fi.nooks.pistekirjanpito.bean.DemoPiste;
import fi.nooks.pistekirjanpito.dao.PisteDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, String> kayttajat;
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
       
    public LoginServlet() {
        super();
    }
    
    private void initUsers() {
		kayttajat = new HashMap<String, String>();
        kayttajat.put("opettaja", "opepass");
        List<DemoPiste> pisteet = dao.listaaKaikki();
        for(int i=0;i<pisteet.size();i++) {
        	kayttajat.put(pisteet.get(i).getOppilasnro(), "oppilaspass");
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initUsers();
		request.setAttribute("viesti", viesti);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		initUsers();
		String kayttajatunnus = request.getParameter("kayttajatunnus");
		String salasana = request.getParameter("salasana");
		if(kayttajatunnus==null) {
			kayttajatunnus = "";
		}
		if(salasana==null) {
			salasana = "";
		}
		if(salasana.equals(kayttajat.get(kayttajatunnus))) {
			request.getSession().invalidate();
			HttpSession mySession = request.getSession();
			mySession.setAttribute("loginStatus", "Y");
			mySession.setAttribute("kayttajatunnus", kayttajatunnus);
			if(kayttajatunnus.equals("opettaja")) {
				mySession.setAttribute("admin", "Y");
			} else {
				mySession.setAttribute("admin", "N");
			}
			response.sendRedirect("pisteet");
			return;
		} else {
			viesti = "Tarkista käyttäjätunnus ja salasana.";
			request.setAttribute("viesti", viesti);
			request.setAttribute("prev_login_username", kayttajatunnus);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

}
