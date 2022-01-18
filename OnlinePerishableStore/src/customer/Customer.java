package customer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import order.Order;
import address.Address;
import order.OrderComparator;
import database.DataBase;

public class Customer implements Serializable{
	// declaration ... 
	String name;
	String orderTable;
	int id;
	Address billingAddress=null;
	ArrayList<Order> orders=new ArrayList<Order>();
	static HashMap<Integer,Customer> customerOf=new HashMap<Integer,Customer>();
	static int customerCount;
	// initialization ...  
	static {
		customerCount=0;
	}
	
	// constructors .... 
	private void setOrderTable() {
		this.orderTable=((Integer)this.getId()).toString()+"__"+this.name+"__orders";
	}
	public Customer(String name) {
		this.name=name;
		DataBase db=new DataBase();
		this.setId(db.saveCustomer(name));
		this.setOrderTable();
		this.billingAddress=null;
		customerOf.put(this.id,this);
	}
	
	// methods ..... 
	public static Customer getCustomer(int id) {
		if(customerOf.containsKey(id)) {
			return customerOf.get(id);
		}
		return null;
	}
	
	private void setId() {
		customerCount++;
		this.id=customerCount;
		
	}
	private void setId(int id) {
		this.id=id;
	}
    public int getId(){
        return this.id;
    }
    public void addOrder(Order ord){
        
        DataBase db=new DataBase();
        ord.setId(db.addOrder(ord,orderTable));
        this.orders.add(ord);
    }
    public void setBillingAddress(Address billingAddress){
        this.billingAddress=billingAddress;
    }
    public void sortOrders(){
        Collections.sort(orders,new OrderComparator());
    }
    
    // toString overriding .... 
    
    public String toString(){
        String custString="";
        double totalAmount=0.0;
        
        custString+="\n-------------------------------------\n";
        custString += "Customer id : "+this.id+"\n";
        custString += "Customer name : "+this.name+"\n";
        custString+="-------------------------------------\n";
        for(Order ord: this.orders){
            custString+="-------------------------------------\n";
            if(ord.getTotalQuantity()>=100){
                custString+="__________LARGE ORDER___________\n";
            }
            custString+=ord.toString();
            totalAmount+=ord.getTotalAmount();
            custString+="-------------------------------------\n";
        }
        custString += "Total Amount Of All Orders : "+totalAmount+"\n";
        custString+="-------------------------------------\n\n";
        return custString;
    }
}
