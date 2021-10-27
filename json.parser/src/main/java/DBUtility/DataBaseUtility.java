package DBUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataBaseUtility {

	Connection con = null;
	Statement stmt = null;

	public DataBaseUtility() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:file:testdb", "SA", "");
			stmt = con.createStatement();
			stmt.executeUpdate(
					"CREATE TEXT TABLE IF NOT EXISTS DATA_VALUES_TBL (event_id VARCHAR(20) NOT NULL, event_duration VARCHAR(50) NOT NULL,type VARCHAR(20),host VARCHAR(20),alert VARCHAR(20) NOT NULL,PRIMARY KEY (event_id));");

			String fileName = "DataFile" + new SimpleDateFormat("yyyyMMddHHmmss'.txt'").format(new Date());
			stmt.executeUpdate("SET TABLE DATA_VALUES_TBL SOURCE \"" + fileName + ";fs=|\"");
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public void addRow(String event_id, String event_duration, String type, String host,
			String alert) {
		try {
			stmt.executeUpdate("INSERT INTO DATA_VALUES_TBL VALUES ('" + event_id + "','" + event_duration + "', '" + type
					+ "','" + host + "','" + alert + "')");
			con.commit();
	      	} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
