package testdriveClasses;

import java.sql.SQLException;
import java.util.ArrayList;

import org.omg.CORBA.Principal;

import entities.File;
import entities.Tag;
import gui.PopulatorGUI;
import gui.SearchGUI;
import helpers.PopulatorDatabaseHelper;

public class Program {
	
	/**** Main Program Class 
	 * Use The static databaseHelper object here through out the program 
	 * to ensure there is only one connection to the database
	 * ****/
	SearchGUI searchGUI;
	PopulatorGUI populatorGUI;
	ArrayList<Tag> tagList;
	public static PopulatorDatabaseHelper databaseHelper;
	
	
	public Program () {
		
		try {
			databaseHelper = new PopulatorDatabaseHelper();
			databaseHelper.initialize();
			tagList = databaseHelper.getTagList();
			Tag tag=new Tag();
			tag.setTagName("pdf");
			ArrayList<File> fList = databaseHelper.getFile("File", tag);
			for(File f: fList){
				System.out.println(f.getFileid()+" "+f.getFileName()+" "+f.getPath()+" "+f.getFrequency());
			}
			databaseHelper.terminate();
		} catch (ClassNotFoundException | SQLException e ) {
			e.printStackTrace();
			System.err.println("Error Initialising the database at Program Class");
		}
		
		
		
		//this.searchGUI = new SearchGUI();
		//this.populatorGUI = new PopulatorGUI();
		//populatorGUI.initialise();
	}
	
	
	public static void main (String [] arg ) {
		
		Program program = new Program();	
		
	}
	

}
