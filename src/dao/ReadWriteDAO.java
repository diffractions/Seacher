package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.concurrent.CopyOnWriteArraySet;

import item.Paper;

public class ReadWriteDAO {
	private final static String SEARCH_SQL = " CALL search(?);";
	private final static String ID_COL_NAME = "name";
	private static final String PROPERTY_COL_NAME = "property";
	private final static String SEARCH_COL_NAME = "search_word_sentence";
	private final static String DB_PATH = "mysql://127.0.0.1:3306";
	private final static String DB_NAME = "searcher";
	private final static String TABLE_NAME = "text";
	private final static String USER_NAME = "root";
	private final static String DB_PASSWORD = "si17st18";
	private final static String INSERT_SQL = "insert into " + TABLE_NAME + " values (?,?,?)";
	private static final String GET_ALL_SQL = "select "+ID_COL_NAME+", "+PROPERTY_COL_NAME+" from "+TABLE_NAME+";";

	{
		try {

			Class.forName("com.mysql.jdbc.Driver");
			Enumeration<Driver> dr = DriverManager.getDrivers();
			if (dr.hasMoreElements() == false)
				throw new NullPointerException("DRIVERS NOT FOUNDS");
			else {
				System.out.println("REGISTER DRIVER: " + dr.nextElement());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public CopyOnWriteArraySet<Paper> search(String str) {
		ResultSet rs = null;
		CopyOnWriteArraySet<Paper> ret = null;

		try (Connection conn = DriverManager.getConnection("jdbc:" + DB_PATH + "/" + DB_NAME, USER_NAME, DB_PASSWORD);
				CallableStatement stat = conn.prepareCall(SEARCH_SQL)) {

			ret = new CopyOnWriteArraySet<>();
			stat.setString(1, str);
			rs = stat.executeQuery();

			while (rs.next()) {
				ret.add(new Paper(rs.getString(ID_COL_NAME), rs.getString(SEARCH_COL_NAME)));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public int addPaperToBD(String fileName, String text) {
		try (Connection conn = DriverManager.getConnection("jdbc:" + DB_PATH + "/" + DB_NAME, USER_NAME, DB_PASSWORD);
				PreparedStatement stat = conn.prepareStatement(INSERT_SQL)) {

			stat.setString(1, fileName);
			stat.setString(2, text);
			stat.setString(3, null);

			return stat.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public CopyOnWriteArraySet<Paper> getAll() {
		ResultSet rs = null;
		CopyOnWriteArraySet<Paper> ret = null;

		try (Connection conn = DriverManager.getConnection("jdbc:" + DB_PATH + "/" + DB_NAME, USER_NAME, DB_PASSWORD);
				Statement stat = conn.createStatement()) {

			ret = new CopyOnWriteArraySet<>();
			rs = stat.executeQuery(GET_ALL_SQL);

			while (rs.next()) {
				ret.add(new Paper(rs.getString(ID_COL_NAME), rs.getString(PROPERTY_COL_NAME)));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}
}
