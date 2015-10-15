package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entities.File;
import entities.Tag;

public class PopulatorDatabaseHelper extends DatabaseHelper {
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
