package order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import order.Order;
import date.Date;
import customer.Customer;
import database.DataBase;
import order.OrderedItem;

public class Order implements Serializable{
	// data members ... 
	static HashMap<Integer,Order> orderOf=new HashMap<Integer,Order>();
	static int numberOfOrders=0;
	int id;
	Date date;
	String orderItemTable;
	ArrayList<OrderedItem> itemList=new ArrayList<OrderedItem>();
	Customer cust=null;
	double totalAmount=0.0;
    int totalQuantity=0;
	
	// constructor ... 
	public Order() {
//		generateId();
//		DataBase db=new DataBase();
//		this.setId(db.saveOrder());
	}
	public ArrayList<OrderedItem> getItemList(){
		return this.itemList;
	}
	// private methods ... 
	private void generateId() {
		numberOfOrders++;
		this.id=numberOfOrders;
        orderOf.put(this.id,this);
	}
	
	// getters and setters ...
	
    public int getTotalQuantity(){
        return this.totalQuantity;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id) {
    	this.id=id;
    }
    public Date getDate(){
        return this.date;
    }
    public void setDate(Date date){
        this.date=date;
    }
    public void setOrderItemTable(String orderItemTable) {
    	this.orderItemTable=orderItemTable;
    }
	public static Order getOrder(int id) {
		if(orderOf.containsKey(id)) {
			return orderOf.get(id);
		}
		return null;
	}
    public double getTotalAmount(){
        return this.totalAmount;
    }
    
    // methods ... 

    public void addOrderedItem(OrderedItem oitem){
        this.itemList.add(oitem);
        this.totalAmount+=oitem.getProduct().getPrice()*oitem.getQuantity();
        this.totalQuantity+=oitem.getQuantity();
    }
    
    // Overriding ... 
    @Override
    public String toString(){
        String orderString=new String("");
        orderString+="Order id : "+this.id+"\n";
        orderString+="Order date : "+this.date.toString()+"\n\n";
        int itemCount=0;
        for(OrderedItem orderedItem: this.itemList){
            orderString+="Item no: "+(itemCount+1);
            orderString+=orderedItem.toString();
            itemCount++;
            orderString+="\n\n";
        }
        orderString+="Total number of diffrent items : "+itemCount+"\n";
        orderString+="Total Quantity of all ordered items : "+this.totalQuantity+"\n";
        orderString+="Total Amount : "+this.totalAmount+"\n\n";
        return orderString;
    }
}