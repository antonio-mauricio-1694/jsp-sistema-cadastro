package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String banco = "jdbc:postgresql://localhost:5432/jsp-base?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "isluc";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static{
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	private static void conectar() {
		try {
			
			if(connection == null) {
				
				Class.forName("org.postgresql.Driver");// carrega os Driver de conecxao do banco
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); // para nao efetuar alteraçoes no banco sem meu comendo
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
