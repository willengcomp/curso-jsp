package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
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


@WebFilter(urlPatterns = {"/principal/*"})/*Intercepta todas as requisicoes que vierem do projeto ou mapeamento*/
public class FilterAutenticacao extends HttpFilter {
   
	private static Connection connection;
	
    public FilterAutenticacao() {
    }
    
    /*Encerra os processos quando o servidor eh parado*/
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*Intercepta as requisicoes e dah as respostas no sistema*/
	//validacao de autenticacao no banco
	//dar commit e rollback de transacoes
	//validar e fazer redirecionamentos de paginas
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			
			HttpServletRequest req = (HttpServletRequest) request;	/*Pegando a requisicao HTTP*/
			HttpSession session  = req.getSession();/*Pegando a sessao da requisicao HTTP*/
			
			String usuarioLogado = (String) session.getAttribute("usuario");/*Pegado o atributo usario logado = modelLogin.getLogin()*/
			
			String urlParaAutentica = req.getServletPath();/*URL que esta sendo acessada*/
			
			/*Validar se esta logado caso nao redirecionar para a tela de login*/
			if ((usuarioLogado == null || usuarioLogado.equals("null")) && 
					!urlParaAutentica.equalsIgnoreCase("/principal/ServletLogin")) {/*Nao esta logado*/
				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url="+ urlParaAutentica);
				request.setAttribute("msg", "Por favor realize o login!");
				redireciona.forward(request, response);
				return;/*Para a conexao e redireciona para o login*/
			} else {
				chain.doFilter(request, response);
			}
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/*Inicia os processos quando o servidor sobe o projeto ex: inicia a conexao com o banco*/
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
