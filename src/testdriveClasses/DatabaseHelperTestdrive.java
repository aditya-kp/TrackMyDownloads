package testdriveClasses;


import java.sql.SQLException;

import entities.Tag;
import helpers.DatabaseHelper;
import helpers.PopulatorDatabaseHelper;

public class DatabaseHelperTestdrive {
	public static void main(String[] args) {
		PopulatorDatabaseHelper databaseHelper=new PopulatorDatabaseHelper();
		Tag tag=new Tag();
		tag.setTagName("songs");
		int temp=0;
		
		try{
			databaseHelper.initialize();
			temp=databaseHelper.getTagId("pdf");
			//if(temp==1){
				System.out.println(temp);
			//}
		}
		catch(SQLException se) {
			System.out.println("SQLException...");
		}
		catch(ClassNotFoundException e){
			System.out.println("ClassNotFoundException");
		}
		finally{
			try{
				databaseHelper.terminate();
			}
			catch(SQLException se) {
				System.out.println("SQLException while terminating...");
			}
		}		
	}
}
