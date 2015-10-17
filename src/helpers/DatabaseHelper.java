package helpers;

import java.sql.*;

/**
 *class that provides database funtionalities
 *
 */

public class DatabaseHelper {


	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/FILE_TRACKER";
	//static final String DB_URL="jdbc:mysql://localhost:3305/file_tracker";
	
	//database credentials
	static final String USER="root";
	static final String PASS="asdf1234";

	protected Connection connection= null;
	
	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */

	public void initialize() throws SQLException, ClassNotFoundException {

		Class.forName(JDBC_DRIVER);
		System.out.println("connecting to database..");
		connection= DriverManager.getConnection(DB_URL, USER, PASS);
		System.out.println("connection established...");


	}

	public void terminate() throws SQLException {

		if(connection!=null){
			System.out.println("closing connection...");
			connection.close();
			System.out.println("connection closed...");
		}

	}

}
