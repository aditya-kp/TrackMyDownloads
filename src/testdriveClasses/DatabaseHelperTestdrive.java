package testdriveClasses;


import java.sql.SQLException;

import entities.Tag;
import helpers.DatabaseHelper;

public class DatabaseHelperTestdrive {
	public static void main(String[] args) {
		DatabaseHelper databaseHelper=new DatabaseHelper();
		Tag tag=new Tag();
		tag.setTagName("songs");
		int temp=0;
		
		try{
			databaseHelper.initialize();
			//temp=databaseHelper.insertTag(tag);
			if(temp==1){
				System.out.println("one tag inserted successfully..");
			}
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
