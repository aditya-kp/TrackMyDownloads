package helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.fabric.xmlrpc.base.Array;
import java.sql.PreparedStatement;

import entities.File;
import entities.Tag;

public class PopulatorDatabaseHelper extends DatabaseHelper {

	public int insertTag(String tagName){
		Statement statement=null;
		int ret=0;

		try{
			statement=connection.createStatement();
			String query="INSERT INTO tag(tagname) "+
					     "VALUE(\""+tagName+"\");";
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
			String query="INSERT INTO tag(tagname) "+
					     "VALUE(\""+ tag.getTagName() +"\");";
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

	public int deleteTag(Tag arg){
		Statement statement=null;
		int ret=0;

		try{
			statement=connection.createStatement();
			String query="DELETE FROM tag "+
					     "WHERE tagid="+arg.getTagId()+";";
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

	public int getLastFileId(){
		Statement statement=null;
		int ret=0;
		ResultSet resultSet=null;

		try{
			statement=connection.createStatement();
			String query="SELECT MAX(fileid) AS maxid "+
					     "FROM file;";
			resultSet=statement.executeQuery(query);
			resultSet.next();
			ret=resultSet.getInt("maxid");
			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			se.printStackTrace();
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

	public int getTagId(String argName){
		Statement statement=null;
		int ret=0;
		ResultSet resultSet=null;

		try{
			statement=connection.createStatement();
			String query="SELECT tagid "+
					"FROM tag "+
					"WHERE tagname=\'"+argName+"\'";
			resultSet=statement.executeQuery(query);
			resultSet.next();
			ret=resultSet.getInt("tagid");
			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			se.printStackTrace();
			System.out.println("Error geting the tagid");
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

	public int insertFile(File file){
		String path=file.getPath();
		int ret=0;
		PreparedStatement preparedStatement=null;

		try{
			String query="INSERT INTO file(filename, path, frequency) "+"VALUE(?,?,?)";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, file.getFileName());
			preparedStatement.setString(2, file.getPath());
			preparedStatement.setInt(3, file.getFrequency());
			preparedStatement.execute();
			preparedStatement.close();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		finally{
			try{
				if(preparedStatement!=null){
					preparedStatement.close();
				}
			}
			catch (SQLException se) {
				System.out.println("SQLException in finally block...");
			}
		}
		return ret;
	}

	public int insertTagging(int fileId, int tagId){
		Statement statement=null;
		int ret=0;

		try{
			statement=connection.createStatement();
			String query="INSERT INTO tagged_to(tagid, fileid) "+
					     "VALUE("+tagId+","+ fileId+");";
			ret=statement.executeUpdate(query);
			statement.close();
		}
		catch(SQLException se){
			se.printStackTrace();
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

	public ArrayList<File> getFile (String fileName){
		ArrayList<File> fileList = new ArrayList<File>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		File tempFile=null;
		String query=	"SELECT fileid, filename, path, frequency "+
				 		"FROM file "+
				 		"WHERE filename LIKE ?";

		try{
			statement=connection.prepareStatement(query);
			statement.setString(1, "%"+fileName+"%");
			resultSet=statement.executeQuery();

			while(resultSet.next()){
				tempFile=new File();
				tempFile.setFileId(resultSet.getInt("fileid"));
				tempFile.setFileName(resultSet.getString("filename"));
				tempFile.setPath(resultSet.getString("path"));
				tempFile.setFrequency(resultSet.getInt("frequency"));
				fileList.add(tempFile);
			}

			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
			se.printStackTrace();
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
		return fileList;
	}

	public ArrayList<File> getFile (String fileName, Tag tag){
		ArrayList<File> fileList = new ArrayList<File>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		File tempFile=null;
		String query=	"SELECT fileid, filename, path, frequency "+
						"FROM file "+
						"WHERE filename LIKE ? AND fileid IN "+
							"(SELECT fileid "+
							"FROM tagged_to "+
								"WHERE tagid = "+
								"(SELECT tagid "+
								"FROM tag "+
								"WHERE tagname = ?))";

		try{
			System.out.println("PopulatorDBH :== TagName:"+tag.getTagName());
			/*debugger skips from here to finally */
			statement=connection.prepareStatement(query);
			statement.setString(1, "%"+fileName+"%");
			statement.setString(2, tag.getTagName());
			resultSet=statement.executeQuery();

			while(resultSet.next()){
				tempFile=new File();
				tempFile.setFileId(resultSet.getInt("fileid"));
				tempFile.setFileName(resultSet.getString("filename"));
				tempFile.setPath(resultSet.getString("path"));
				tempFile.setFrequency(resultSet.getInt("frequency"));
				fileList.add(tempFile);
			}

			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
			se.printStackTrace();
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
		return fileList;
	}
	

	public ArrayList<File> getFile (Tag tag){
		ArrayList<File> fileList = new ArrayList<File>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		File tempFile=null;
		String query=	"SELECT fileid, filename, path, frequency "+
						"FROM file "+
						"WHERE fileid IN "+
							"(SELECT fileid "+
							"FROM tagged_to "+
							"WHERE tagid = ?)";
								
		try{
			statement=connection.prepareStatement(query);
			statement.setInt(1, tag.getTagId());
			resultSet=statement.executeQuery();

			while(resultSet.next()){
				tempFile=new File();
				tempFile.setFileId(resultSet.getInt("fileid"));
				tempFile.setFileName(resultSet.getString("filename"));
				tempFile.setPath(resultSet.getString("path"));
				tempFile.setFrequency(resultSet.getInt("frequency"));
				fileList.add(tempFile);
			}

			resultSet.close();
			statement.close();
		}
		catch(SQLException se){
			System.out.println("SQLException");
			se.printStackTrace();
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
		return fileList;
	}

}
