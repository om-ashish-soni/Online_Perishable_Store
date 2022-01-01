package product;

import java.io.Serializable;

public 
class Vegetable extends Product implements Serializable{
	
	public Vegetable(){
	}
	public Vegetable(String name,String description){
		super(name,description,"Vegetable");
	}
	public String getType(){
		return new String("Vegetable");
	}
	public String toString(){
		String str=new String();
		str="\ntype = "+this.getType()+" ; \nid = "+this.getId()+" ; \n"+"name = "+this.getName()+" ; \n"+"description = "+this.getDescription()+"\n"+"price = "+this.getPrice()+"\n";
		return str;
	}
}