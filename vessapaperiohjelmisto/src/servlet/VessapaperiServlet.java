package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PaperinOstaja;
import dao.VessapaperiDao;

/**
 * Servlet implementation class VessapaperiServlet
 */
@WebServlet("/VessapaperiServlet")
public class VessapaperiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public VessapaperiServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VessapaperiDao vDao = new VessapaperiDao();
		String[] ostajaIdt = request.getParameterValues("ostajaId");
		String[] maarat = request.getParameterValues("uusiOstos");
		String nollaa = request.getParameter("nollaa");
		String viesti = "";
		if(nollaa!=null && !"".equals(nollaa)) {
			try {
				vDao.nollaaTilanne();
			} catch (Exception e) {
				e.printStackTrace();
				viesti = e.getMessage();
			}
		} else {
			for(int i=0;i<ostajaIdt.length;i++) {
				if(ostajaIdt[i]!=null && !"".equals(ostajaIdt[i]) && maarat[i]!=null && !"".equals(maarat[i])) {
					try {
						PaperinOstaja ostaja = vDao.etsiOstaja(ostajaIdt[i]);
						int uusiOstos = Integer.parseInt(maarat[i]);
						ostaja.setOstettuMaara(uusiOstos);
						vDao.paivitaTilanne(ostaja);
					} catch (Exception e) {
						e.printStackTrace();
						viesti = e.getMessage();
					}
				}
			}
		}
		request.setAttribute("viesti", viesti);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
