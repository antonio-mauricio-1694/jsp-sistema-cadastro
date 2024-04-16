package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter //(urlPatterns = {"/principal/*"})
public class FilterAutenticacao extends HttpFilter implements Filter {
       
	private static Connection connection;
	private static final long serialVersionUID = 1L;


	public FilterAutenticacao() {
        super();
        
    }

	
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
	try {
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession(); // uerl esta sendo acessada
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			String urlParaAutenticar = req.getServletPath();
			
			if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("ServletLogin")) { // não esta logado
				
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?=" + urlParaAutenticar);
				request.setAttribute("msg", "por favor realise o login");
				redireciona.forward(req, response);
				
				return; //para a execução e redirecionar para o login
			}else {
				chain.doFilter(request, response);
			}
			
			connection.commit(); // deu tudo certo commita a alterçoes no banco
	} catch (Exception e) {
		e.printStackTrace();
		
		RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
		request.setAttribute("msg", "informe a senha eo login corretamente");
		redirecionar.forward(request, response);
		try {
			connection.rollback();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
	}
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
