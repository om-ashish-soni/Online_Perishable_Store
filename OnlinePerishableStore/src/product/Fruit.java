package product;

import java.io.Serializable;


public class Fruit extends Product implements Serializable{
	
	public Fruit(){
	}
	public Fruit(String name,String description){
		super(name,description,"Fruit");
	}
	public String getType(){
		return new String("Fruit");
	}
	public String toString(){
		String str=new String();
		str="\ntype = "+this.getType()+" ; \nid = "+this.getId()+" ; \n"+"name = "+this.getName()+" ; \n"+"description = "+this.getDescription()+"\n"+"price = "+this.getPrice()+"\n";
		return str;
	}
}