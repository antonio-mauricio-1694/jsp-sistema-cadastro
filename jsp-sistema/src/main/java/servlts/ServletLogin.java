package servlts;

import java.io.IOException;

import dao.DaoLoginRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet //(urlPatterns = {"/principal/ServletLogin" , "/ServletLogin"})
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private DaoLoginRepository daoLoginRepository = new DaoLoginRepository();
    
    public ServletLogin() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); // invalida a sessao
			RequestDispatcher redirecionar = request.getRequestDispatcher("inex.jsp");
			redirecionar.forward(request, response);
		}else {
			doPost(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (daoLoginRepository.validarAutenticacao(modelLogin)) {
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msg", "informe a senha eo login corretamente");
					redirecionar.forward(request, response);
				}
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msg", "informe a senha eo login corretamente");
				redirecionar.forward(request, response);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erros.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
