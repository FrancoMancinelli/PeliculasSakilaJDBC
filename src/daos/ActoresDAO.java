package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase que extiende de AbstractDAO para conectarse a la base de datos y la
 * cual contiene métodos para obtener información de la misma
 * 
 * @author anonymus
 *
 */
public class ActoresDAO extends AbstractDAO {

	/**
	 * Realiza una consulta en la tabla film_actor para obtener los ID de los
	 * actores que hayan participado en una determinada pelicula
	 * 
	 * @param idPelicula El id de la pelicula a la cual buscar los actores
	 * @return Un arrayList con los id de los actores participes
	 */
	public ArrayList<Integer> getActorsID(int idPelicula) {

		ArrayList<Integer> actoresID = new ArrayList<Integer>();

		try {
			final String QUERY = "SELECT actor_id FROM film_actor WHERE film_id = '" + idPelicula + "';";

			ResultSet result = stmt.executeQuery(QUERY);

			while (result.next()) {
				actoresID.add(result.getInt("actor_id"));
			}
			return actoresID;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Realiza una consulta en la tabla actor para obtener el nombre y apellido de
	 * los actores según un determinado ID. Los cuales son extraidos de un array
	 * 
	 * @param array Array con los ID de los actores de los cuales se quiere conocer
	 *              el nombre y apellido
	 * @return Un String[][] con los nombres y apellidos de los actores
	 */
	public String[][] getActorsName(ArrayList<Integer> array) {

		String[][] resultado = new String[array.size()][2];

		for (int i = 0; i < array.size(); i++) {
			final String QUERY = "SELECT first_name, last_name FROM actor" + " WHERE actor_id = '"
					+ array.get(i) + "';";
			try {
				ResultSet rs = stmt.executeQuery(QUERY);
				while (rs.next()) {
					String nombre = rs.getString("first_name");
					String apellido = rs.getString("last_name");
					
					resultado[i][0] = nombre;
					resultado[i][1] = apellido;
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return resultado;

	}
}
