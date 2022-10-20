package daos;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.Pelicula;

/**
 * Clase que extiende de AbstractDAO para conectarse a la base de datos y la
 * cual contiene métodos para obtener información de la misma
 * 
 * @author anonymus
 *
 */
public class PeliculasDAO extends AbstractDAO {

	/**
	 * Realiza una consulta para obtener todas las peliculas ordenadas
	 * 
	 * @return Un ResultSet el cual contiene todo el listado de peliculas
	 */
	public ResultSet getPeliculas() {
		final String QUERY = "SELECT film_id, title, description, release_year, length " + " FROM film"
				+ " GROUP BY film_id" + " ORDER BY film_id;";
		try {
			ResultSet rs = stmt.executeQuery(QUERY);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene la primer pelicula y la devuelve
	 * 
	 * @param rs ResultSet que contiene el listado de peliculas consultadas
	 * @return La Pelicula en primera posición
	 */
	public Pelicula getFirst(ResultSet rs) {
		try {
			rs.first();

			int id = rs.getInt("film_id");
			String title = rs.getString("title");
			String descri = rs.getString("description");
			int year = rs.getInt("release_year");
			int length = rs.getInt("length");

			Pelicula p = new Pelicula(id, length, year, title, descri);
			return p;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene la última pelicula y la devuelve
	 * 
	 * @param rs ResultSet que contiene el listado de peliculas consultadas
	 * @return La Pelicula en última posición
	 */
	public Pelicula getLast(ResultSet rs) {
		try {
			rs.last();

			int id = rs.getInt("film_id");
			String title = rs.getString("title");
			String descri = rs.getString("description");
			int year = rs.getInt("release_year");
			int length = rs.getInt("length");

			Pelicula p = new Pelicula(id, length, year, title, descri);
			return p;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Obtiene la siguiente pelicula a la actual del ResultSet y la devuelve
	 * 
	 * @param rs ResultSet que contiene el listado de peliculas consultadas
	 * @return La Pelicula en siguiente posición
	 */
	public Pelicula getNext(ResultSet rs) {
		try {
			if (!rs.isLast()) {
				while (rs.next()) {
					int id = rs.getInt("film_id");
					String title = rs.getString("title");
					String descri = rs.getString("description");
					int year = rs.getInt("release_year");
					int length = rs.getInt("length");

					Pelicula p = new Pelicula(id, length, year, title, descri);
					return p;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Obtiene la pelicula previa a la actual del ResultSet y la devuelve
	 * 
	 * @param rs ResultSet que contiene el listado de peliculas consultadas
	 * @return La Pelicula en previa posición
	 */
	public Pelicula getBack(ResultSet rs) {
		try {
			if (!rs.isFirst()) {
				while (rs.previous()) {
					int id = rs.getInt("film_id");
					String title = rs.getString("title");
					String descri = rs.getString("description");
					int year = rs.getInt("release_year");
					int length = rs.getInt("length");

					Pelicula p = new Pelicula(id, length, year, title, descri);
					return p;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Guarda una pelicula nueva en la base de datos
	 * 
	 * @param p Pelicula a guardar
	 */
	public void saveNewMovie(Pelicula p) {
		final String INSERT = "INSERT INTO film (title, description, release_year, language_id, length)" + " VALUES "
				+ " ('" + p.getName() + "', '" + p.getDescription() + "', " + p.getYear() + ", 1," + p.getDuration()
				+ ");";
		try {
			stmt.executeUpdate(INSERT);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
