package testdriveClasses;

import java.sql.SQLException;
import java.util.ArrayList;

import entities.Tag;
import gui.SearchGUI;
import helpers.DatabaseHelper;
import helpers.PopulatorDatabaseHelper;

//public class SearchGUITest {
//
//	static boolean runOnlyOnce = true;
//	static PopulatorDatabaseHelper databaseHelper;
//	static ArrayList<Tag> tagArray = new ArrayList<Tag>();
//	
//	public static void main(String [] argument) {
//		
//		databaseHelper = new PopulatorDatabaseHelper();
//		SearchGUI searchGUI = new SearchGUI();
//		
//		
//		try {
//			databaseHelper.initialize();
//			populateTag();
//			searchGUI.setTagComboBox(tagArray);
//		} catch (ClassNotFoundException |SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public static void populateTag () {
//		
//		if (runOnlyOnce){
//			String tag[] = { "Games","Music","Files","pdf","documents"};
//			for (int i = 1;i<6;i++){
//				Tag tagObj = new Tag ();
//				tagObj.setTagName(tag[i-1]);
//				//databaseHelper.insertTag(tagObj);
//				tagArray.add(tagObj);
//		}
//			//runOnlyOnce = false;
//			
//		}
//	}
//	
//}
