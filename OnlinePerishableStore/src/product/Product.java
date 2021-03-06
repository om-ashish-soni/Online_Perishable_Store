package product;

import java.io.Serializable;
import java.util.HashMap;


public abstract class Product implements Serializable{
	static int numberOfProducts;
	static HashMap<String,Double> priceOf=new HashMap<String,Double>();
	static HashMap<String,Integer> availableQuantityOf=new HashMap<String,Integer>();
	String name,description,type;
	int id;
	double price;
	static{
		numberOfProducts=0;
		priceOf.put("mango",60.00);
		priceOf.put("pizza", 90.00);
		priceOf.put("potato", 40.00);
		availableQuantityOf.put("mango",50);
		availableQuantityOf.put("pizza", 40);
		availableQuantityOf.put("potato", 100);
	}
	
	public static int getNumberOfProducts(){
		return numberOfProducts;
	}
	private void generateId() {
		this.id=numberOfProducts;
	}
	private void setPrice() {
		if(priceOf.containsKey(this.getName().toLowerCase())) {
			this.price=priceOf.get(this.getName().toLowerCase());
		}
	}
	public void setName(String name){
		this.name=name;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public int getId(){
		return this.id;
	}
	public String getName(){
		return this.name;
	}
	public String getDescription(){
		return this.description;
	}
	public String getType(){
		return new String("Default Product");
	}
	public double getPrice() {
		return this.price;
	}
	public Product(){
		Product.numberOfProducts++;
	}
	public Product(String name,String description,String type){
		Product.numberOfProducts++;
		generateId();
		this.name=name;
		this.description=description;
		this.type=type;
		setPrice();
	}
	public String toString(){
		String str=new String();
		str="\nid = "+id+" ; \n"+"name = "+name+" ; \n"+"description = "+description+"\n"+"price = "+price+"\n";
		return str;
	}
}