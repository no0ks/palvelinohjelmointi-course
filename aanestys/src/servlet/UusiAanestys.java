package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Aanestys;
import dao.AanestysDao;

/**
 * Servlet implementation class UusiAanestys
 */
@WebServlet("/UusiAanestys")
public class UusiAanestys extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UusiAanestys() {
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
		Aanestys aanestys = new Aanestys();
		String kysymys = request.getParameter("kysymys");
		Aanestys vanhaAanestys = aanestysDao.etsiAanestys(kysymys);
		if(vanhaAanestys==null && !kysymys.equals("")) {
			aanestys.setKysymys(kysymys);
			aanestys.setVastausKylla(0);
			aanestys.setVastausEi(0);
			aanestysDao.lisaaAanestys(aanestys);
		}
		response.sendRedirect("index.jsp");
	}

}
