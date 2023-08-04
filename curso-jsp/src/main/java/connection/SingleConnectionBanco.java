package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {/*Padrão singleton*/
	
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() {/*Quando tiver uma instancia vai conectar*/
		conectar();
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	private static void conectar() {
		try {
			if (connection == null) {
				Class.forName("org.postgresql.Driver");/*Carrega o driver do banco*/
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);/*Não executa altercao no banco sem nossa autorizacao*/
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
				
}
