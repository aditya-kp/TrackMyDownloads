package entities;

public class Tag {
	private int tagId;
	private String tagName;
	
	public Tag (){
		
	}
	public Tag (int id, String name){
		tagId= id;
		tagName = name;
	}
	
	public int getTagId(){
		return tagId;
	}
	public String getTagName(){
		return tagName;
	}

	public void setTagId(int arg){
		tagId=arg;
	}
	public void setTagName(String arg){
		tagName=arg;
	}
	
	@Override
	public String toString() {
		return tagName;
	}
}
