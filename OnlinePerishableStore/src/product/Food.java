package product;

import java.io.Serializable;


public class Food extends Product implements Serializable{
	
	public Food(){
	}
	public Food(String name,String description){
		super(name,description,"Food");
	}
	public String getType(){
		return new String("Food");
	}
	public String toString(){
		String str=new String();
		str="\ntype = "+this.getType()+" ; \nid = "+this.getId()+" ; \n"+"name = "+this.getName()+" ; \n"+"description = "+this.getDescription()+"\n"+"price = "+this.getPrice()+"\n";
		return str;
	}
}