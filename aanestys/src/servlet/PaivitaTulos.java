package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Aanestys;
import dao.AanestysDao;

/**
 * Servlet implementation class PaivitaTulos
 */
@WebServlet("/PaivitaTulos")
public class PaivitaTulos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaivitaTulos() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AanestysDao aanestysDao = new AanestysDao();
		String[] aanestysIdt = request.getParameterValues("aanestysId");
		String[] vastaukset = request.getParameterValues("vastaus");
		ArrayList<Aanestys> aanestykset = new ArrayList<Aanestys>();
		for(int i=0;i<aanestysIdt.length;i++) {
			Aanestys aanestys = aanestysDao.etsiAanestysIdlla(aanestysIdt[i]);
			if(aanestys!=null) {
				try {
					int vastaus = Integer.parseInt(vastaukset[i]);
					if(vastaus==1) {
						aanestys.setVastausKylla(aanestys.getVastausKylla()+1);
						aanestykset.add(aanestys);
					} else if(vastaus==2) {
						aanestys.setVastausEi(aanestys.getVastausEi()+1);
						aanestykset.add(aanestys);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(aanestykset.size()>0) {
			aanestysDao.paivitaTulos(aanestykset);
		}
		response.sendRedirect("aanestystulokset.jsp");
	}
	
}
