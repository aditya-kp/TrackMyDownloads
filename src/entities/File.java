package entities;

import java.sql.Date;
import java.util.Calendar;


public class File {
	private int fileId;
	private String fileName;
	private String path;
	private Calendar downDate;
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
	public Calendar getDownDate(){
		return downDate; 
	}
	public String getDownDateAsString(){
		String ret=null;
		try{
			ret=downDate.get(Calendar.YEAR)+"-"+
				downDate.get(Calendar.MONTH)+"-"+
				downDate.get(Calendar.DAY_OF_MONTH);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("ArrayOutOfBoundException");
		}
		return ret;
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
	public void setDownDate(Date arg){
		downDate=Calendar.getInstance();
		downDate.setTime(arg);
	}
	public void setDownDate(Calendar arg){
		downDate=arg;
	}
	public void setFrequency(int arg){
		frequency=arg;
	}
}
