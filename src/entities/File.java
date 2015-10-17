package entities;

import java.text.DecimalFormat;


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
	
	public String getSizeInString () {
		java.io.File f = new java.io.File(path);
		String sizeInString;
		DecimalFormat format = new DecimalFormat("#.##");
		Double size = (double) (f.length()/1024);
		if (size > 1024){
			size /= 1024;
			size = Double.valueOf(format.format(size));
			sizeInString = size + " Mb";
			return sizeInString;
		}
		size = Double.valueOf(format.format(size));
		sizeInString = size +" Kb";
		return sizeInString;		
	}
	
	public String getFolderPath (){
		int fileNameLength = fileName.length();
		int pathLength = path.length();
		int folderPathLength = pathLength - fileNameLength;
		String folderPath = path.substring(0, folderPathLength);
		return folderPath;
		
	}
	
}
