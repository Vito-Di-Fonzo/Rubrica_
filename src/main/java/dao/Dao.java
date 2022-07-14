package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Contatto;
import model.User;

public class Dao {

	private static String jdbcURL = "jdbc:mysql://localhost:3306/rubrica_utenti1";
	private static String username = "root";
	private static String password = "Difvit1997!";
	private static Connection connection = null;

	public static User autenticaUtente(String username, String password) {
		List<User> listaUser = new ArrayList();
		String sql = "select * from utente where Utente=? and Password=?";
		try {
			gestisciConnessione();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User utenteTrovato = new User();
				utenteTrovato.setId(rs.getInt("idUtente"));
				utenteTrovato.setUsername(rs.getString("Utente"));
				utenteTrovato.setPassword(rs.getString("Password"));

				listaUser.add(utenteTrovato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUser.isEmpty() ? null : listaUser.get(0);
	}

	public static List<Contatto> getListaContattiByUser(Integer userId) {
		List<Contatto> listaContatti = new ArrayList();
		String sql = "select c.* from contatti c, utente_contatto u where u.id_contatto = c.idCont and u.id_utente = "
				+ userId;
		try {
			gestisciConnessione();
			PreparedStatement ps = connection.prepareStatement(sql);
			// ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Contatto contattoTrovato = new Contatto();
				contattoTrovato.setId(rs.getInt("idCont"));
				contattoTrovato.setNome(rs.getString("nome"));
				contattoTrovato.setCognome(rs.getString("cognome"));
				contattoTrovato.setNumeroTelefono(rs.getString("telefono"));

				listaContatti.add(contattoTrovato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaContatti;
	}

	private static void gestisciConnessione() {
		if (null == connection) {
			try {
				connection = DriverManager.getConnection(jdbcURL, username, password);
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	public static User inserimentoDati(String nome, String cognome, String telefono, int idUtente) { // ritorna int
																										// // id

		try {

			Connection connection = DriverManager.getConnection(jdbcURL, username, password); // connesione al server

			String sql = "INSERT INTO Contatti ( nome, cognome, telefono)" // inserimento dei
																			// parametri nel db
					// INSERT
					+ "VALUES (? , ? , ? )";

			PreparedStatement statement = connection.prepareStatement(sql); // AGGIUNTA MACRO
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, telefono);

			int rows = statement.executeUpdate();

			String sqlCerca = "SELECT * FROM CONTATTI WHERE NOME = ? AND COGNOME = ? AND TELEFONO = ?";

			statement = connection.prepareStatement(sqlCerca); // AGGIUNTA MACRO
			statement.setString(1, nome);
			statement.setString(2, cognome);
			statement.setString(3, telefono);

			ResultSet rs = statement.executeQuery();
			Integer idContatto = 0;
			while (rs.next()) {
				idContatto = rs.getInt("idCont");
			}

			String sqlInsert = "INSERT INTO utente_contatto ( id_contatto, id_utente) VALUES (? , ?)";

			statement = connection.prepareStatement(sqlInsert); // AGGIUNTA MACRO
			statement.setInt(1, idContatto);
			statement.setInt(2, idUtente);
			rows = statement.executeUpdate();
			connection.close(); // chiusura connessione al server

		} catch (SQLException ex) { // gestione degli errori
			ex.printStackTrace();
		}
		return null;

	}

	public static User inserimentoDatiUtente_Contatti(int id_contatto, int id_utente) {

		try {

			Connection connection = DriverManager.getConnection(jdbcURL, username, password); // connesione al server

			String sql = "INSERT INTO utente_contatto ( id_contatto, id_utente)" // inserimento dei
					// parametri nel db
					// INSERT
					+ "VALUES (? , ? )";

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, id_contatto);
			statement.setLong(2, id_utente);

			int rows = statement.executeUpdate();

			if (rows > 0) { // controllo che i dati siano stati inseriti
				System.out.println("Contatto inserito correttamente");

			}
			connection.close(); // chiusura connessione al server

		} catch (SQLException ex) { // gestione degli errori
			ex.printStackTrace();
		}
		return null;

	}

	public static void eliminaDatiContatto(int id) {

		// Open a connection
		try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
				Statement stmt = conn.createStatement();) {
			String sql = "DELETE FROM contatti WHERE idCont =" + id;


			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void eliminaDatiUtente_Contatto(int id) {

		// Open a connection
		try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
				Statement stmt = conn.createStatement();) {
			String sql = "DELETE FROM utente_contatto WHERE id_contatto =" + id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static User cercaUtente(Integer id) {
		List<User> listaUser = new ArrayList();
		String sql = "select * from utente where idUtente = ?";
		try {
			gestisciConnessione();
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User utenteTrovato = new User();
				utenteTrovato.setId(rs.getInt("idUtente"));
				utenteTrovato.setUsername(rs.getString("Utente"));
				utenteTrovato.setPassword(rs.getString("Password"));

				listaUser.add(utenteTrovato);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaUser.isEmpty() ? null : listaUser.get(0);
	}
}
