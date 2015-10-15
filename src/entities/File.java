package entities;

import java.sql.Date;
import java.util.Calendar;


public class File {
	private int fileId;
	private String fileName;
	private String path;
	private int frequency;
	
	//public getters
	public int getFileid(){
		return fileId;
	}
	public String getFileName(){
		return fileName;
	}
	public String getPath(){
		return path;
	}

	public int getFrequency(){
		return frequency;
	}
	
	//public setters
	public void setFileId(int arg){
		fileId=arg;
	}
	public void setFileName(String arg){
		fileName=arg;
	}
	public void setPath(String arg){
		path=arg;
	}

	public void setFrequency(int arg){
		frequency=arg;
	}
	
}
