package asktechforum.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import asktechforum.dominio.ResultConsultarPergunta;
import asktechforum.negocio.CadastroPerguntaBC;

/**
 * Servlet implementation class ServletConsultarPerguntaPorTag
 */
@WebServlet("/ServletConsultarPerguntaPorTag")
public class ServletConsultarPerguntaPorTag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String RESULTADO_CONSULTA = "consultaPerguntaPorTag.jsp";
	private static final String INDEX = "index.jsp";
	private CadastroPerguntaBC cadastro;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultarPerguntaPorTag() {
        super();
    }
    @Override
    protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
    		throws ServletException, IOException {
    	super.service(arg0, arg1);
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String tag = request.getParameter("tag");
		this.cadastro = new CadastroPerguntaBC();
		ArrayList<ResultConsultarPergunta> tags = cadastro.consultarPerguntaPorTag(tag);
		RequestDispatcher view ; 
		
		if(tag.equals("all")){
			view = request.getRequestDispatcher(INDEX);
		}else{
			view = request.getRequestDispatcher(RESULTADO_CONSULTA);
		}
	
		request.setAttribute("pergunta", tags);
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tag = request.getParameter("tag");
		this.cadastro = new CadastroPerguntaBC();
		ArrayList<ResultConsultarPergunta> tags = cadastro.consultarPerguntaPorTag(tag);
		RequestDispatcher view;
        HttpSession session = request.getSession();
		
		session.setAttribute("stop", false);
		
		if(tag.equals("all")){
			view = request.getRequestDispatcher(INDEX);
		}else{
			view = request.getRequestDispatcher(RESULTADO_CONSULTA);
		}
		
		if(tags.get(0).getIdPergunta() > 0) {
			request.setAttribute("pergunta", tags);
		}
		view.forward(request, response);
	}

}
