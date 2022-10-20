package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase abstracta para realizar la conexión a base de datos
 * @author anonymus
 *
 */
public class AbstractDAO {
	private static final String URL = "jdbc:mysql://localhost/";
	private static final String BBDD = "sakila";
	private static final String PARAMETROS = "?serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "root";

	protected Connection conn;
	protected Statement stmt;

	public AbstractDAO() {
		try {
			this.conn = DriverManager.getConnection(URL+BBDD+PARAMETROS, USER, PASS);
			this.stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Fallo en la conexión");
			
		}
	}
}