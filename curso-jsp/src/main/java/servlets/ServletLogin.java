package servlets;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

/*O chamado Controller sao as servlets*/
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletLogin() {
    }

    /*Recebe os dados pela url em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
	
	/*Recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {/*Verificando se os parametros login e senha foram passados*/
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			if (modelLogin.getLogin().equalsIgnoreCase("admin") && modelLogin.getSenha().equalsIgnoreCase("admin")) {/*simulando login*/
				RequestDispatcher redirecionar = request.getRequestDispatcher("principal/principal.jsp");/*RequestDispatcher redireciona para uma tela, neste caso para a mesma tela de login*/
				redirecionar.forward(request, response);/*aqui estamos encaminhado a resquisicao e a resposta que queremos após os passo acima*/
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");/*RequestDispatcher redireciona para uma tela, neste caso para a mesma tela de login*/
				request.setAttribute("msg", "Informe o login e a senha corretamente");/*o msg é o atributo que será enviado para a tela de login para exibir a mensagem a ele atribuida*/
				redirecionar.forward(request, response);/*aqui estamos encaminhado a resquisicao e a resposta que queremos após os passo acima*/
			}
			
		} else {
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");/*RequestDispatcher redireciona para uma tela, neste caso para a mesma tela de login*/
			request.setAttribute("msg", "Informe o login e a senha corretamente");/*o msg é o atributo que será enviado para a tela de login para exibir a mensagem a ele atribuida*/
			redirecionar.forward(request, response);/*aqui estamos encaminhado a resquisicao e a resposta que queremos após os passo acima*/
		}
	}

}
