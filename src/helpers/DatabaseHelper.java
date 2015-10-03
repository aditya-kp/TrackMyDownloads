package helpers;

import java.sql.*;
import java.util.ArrayList;

import entities.*;

/**
 *class that provides database funtionalities
 *
 */

public class DatabaseHelper {


	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost/FILE_TRACKER";

	//database credentials
	static final String USER="root";
	static final String PASS="asdf1234";

	Connection connection= null;

	public void initialize() throws SQLException, ClassNotFoundException {
		try{
			Class.forName(JDBC_DRIVER);

			System.out.println("connecting to database..");
			connection= DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("connection established...");
		}

		catch(SQLException se) {
			throw se;
		}
		catch(ClassNotFoundException e) {
			throw e;
		}
	}

	public void terminate() throws SQLException {
		try{
			if(connection!=null){
				System.out.println("closing connection...");
				connection.close();
				System.out.println("connection closed...");
			}
		}
		catch(SQLException se){
			throw se;
		}
	}

	//may not be required
	public int insertTag(String tagName){
		Statement statement=null;
		int ret=0;

		try{
			statement=connection.createStatement();
			String query="INSERT INTO tag(tagname)"+
					" value(\""+tagName+"\");";
			ret=statement.executeUpdate(query);
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
		}
		finally{
			try{
				if(statement!=null){
					statement.close();
				}
			}
			catch (SQLException se) {
				System.out.println("SQLException in finally block...");
			}
		}
		return ret;
	}

	public int insertTag(Tag tag){
		Statement statement=null;
		int ret=0;

		try{
			statement=connection.createStatement();
			String query="INSERT INTO tag(tagname)"+
					"value(\""+ tag.getTagName() +"\");";
			ret=statement.executeUpdate(query);
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
		}
		finally{
			try{
				if(statement!=null){
					statement.close();
				}
			}
			catch (SQLException se) {
				System.out.println("SQLException in finally block...");
			}
		}
		return ret;
	}

	//yet to be tested
	public int getLastFileId(){
		Statement statement=null;
		int ret=0;
		ResultSet resultSet=null;

		try{
			statement=connection.createStatement();
			String query="SELECT MAX(fileid) AS maxid"+
					"FROM file";
			resultSet=statement.executeQuery(query);
			resultSet.next();
			ret=resultSet.getInt("maxid");
			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
		}
		finally{
			try{
				if(statement!=null){
					statement.close();
				}
				if(resultSet!=null){
					resultSet.close();
				}
			}
			catch (SQLException se) {
				System.out.println("SQLException in finally block...");
			}
		}
		return ret;
	}

	//yet to be tested
	public int insertFile(File file){
		Statement statement=null;
		int ret=0;

		try{
			statement=connection.createStatement();
			String query="INSERT INTO file(filename, path, downdate, frequency)"+
					"value(\""+file.getFileName()+"\",\""+file.getPath()+"\",DATE\""+
					file.getDownDateAsString()+"\","+file.getFrequency()+");";
			ret=statement.executeUpdate(query);
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
		}
		finally{
			try{
				if(statement!=null){
					statement.close();
				}
			}
			catch (SQLException se) {
				System.out.println("SQLException in finally block...");
			}
		}
		return ret;
	}

	//yet to be tested
	public ArrayList<Tag> getTagList(){
		ArrayList<Tag> tagList=new ArrayList<Tag>();
		Tag tempTag=null;
		Statement statement=null;
		ResultSet resultSet=null;

		try{
			statement=connection.createStatement();
			String query="SELECT tagid, tagname FROM tag";
			resultSet=statement.executeQuery(query);

			while(resultSet.next()){
				tempTag=new Tag();
				tempTag.setTagId(resultSet.getInt("tagid"));
				tempTag.setTagName(resultSet.getString("tagname"));
				tagList.add(tempTag);
			}

			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
		}
		finally{
			try{
				if(statement!=null){
					statement.close();
				}
				if(resultSet!=null){
					resultSet.close();
				}
			}
			catch (SQLException se) {
				System.out.println("SQLException in finally block...");
			}
		}

		return tagList;
	}
}
