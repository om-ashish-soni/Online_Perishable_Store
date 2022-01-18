package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import customer.Customer;
import order.Order;
import order.OrderedItem;
import product.Product;

import java.sql.*;
import java.util.ArrayList;

public class DataBase implements Serializable{
	String location="D:\\JAVA_THE_ETERNAL_LANG\\PROJECTS_OF_JAVA\\ONLINE_PERISHSABLE_STORE/";
	String ext=".ser";
	String filename;
	String DATABASE="online_perishable_store";
	String URL="jdbc:mysql://localhost:3306/";
	public DataBase(){
		
	}
	private void serializeCustomer(Customer cust1) {
		filename=location+cust1.getId()+ext;
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(filename);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(cust1);
		}catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	private Customer loadSerializedCustomer(int customerId) throws IOException, ClassNotFoundException {
		filename=location+customerId+ext;
		FileInputStream fis=new FileInputStream(filename);
		ObjectInputStream ois=new ObjectInputStream(fis);
		return (Customer)ois.readObject();
	}
	public void saveCustomer(Customer cust1) {
		this.serializeCustomer(cust1);
		
	}
	public int saveCustomer(String custName) {
		try (Connection con = DriverManager.getConnection(URL+DATABASE, "root", "")) {
			try (Statement stmt = con.createStatement()) {
				if (stmt.executeUpdate("insert into customers (name) values('"+custName+"')") != 1) {
					System.out.println("Error in insertion");
					System.exit(-1);
				}
				try (ResultSet rs = stmt.executeQuery("select * from customers where name='"+custName+"'")) {
					while (rs.next()) {
						Integer custId=rs.getInt(1);
						String query="CREATE TABLE "+custId.toString()+"__"+custName+"__orders ( id int NOT NULL AUTO_INCREMENT, items varchar(255) DEFAULT NULL, orddt varchar(255) DEFAULT NULL, PRIMARY KEY(id) )";
						try{
							stmt.executeUpdate(query);
						}catch(Exception e) {
							e.printStackTrace();
						}
						return custId;
					}
				}
			}
		} catch (SQLException ex) {
			System.err.println("SQLException: " + ex.getMessage());
		}
		return -1;
	}
	public void updateCustomer(Customer cust1) {
		this.saveCustomer(cust1);
	}
	public Customer loadCustomer(int customerId) throws ClassNotFoundException, IOException {
		return this.loadSerializedCustomer(customerId);
	}
	public int addOrder(Order order,String orderTable) {
//		String orderTable=order.getOrderTable();
		try (Connection con = DriverManager.getConnection(URL+DATABASE, "root", "")) {
			try (Statement stmt = con.createStatement()) {
				String dt="2022/08/24";
				String orderItemTable="";
				if (stmt.executeUpdate("insert into "+orderTable+" (orddt) values('"+dt+"')") != 1) {
					System.out.println("Error in insertion");
					System.exit(-1);
				}
				try (ResultSet rs = stmt.executeQuery("select * from "+orderTable+" where orddt='"+dt+"'")) {
					Integer orderId=-1;
					while (rs.next()) {
						orderId=rs.getInt(1);
					}
					orderItemTable=orderId.toString()+"__"+orderTable+"__items";
					order.setOrderItemTable(orderItemTable);
					String updateQuery="update "+orderTable+" set items='"+orderItemTable+"'";
					stmt.executeUpdate(updateQuery);
					String query="CREATE TABLE "+ orderItemTable+" (quantity int(10),name varchar(255),type varchar(255),price int(10),description varchar(255) )";
					stmt.executeUpdate(query);
					ArrayList<OrderedItem> itemList=order.getItemList();
					for(OrderedItem item:itemList) {
						Product prod=item.getProduct();
						int quantity=item.getQuantity();
						String name=prod.getName();
						String type=prod.getType();
						int price=(int)prod.getPrice();
						String description=prod.getDescription();
						query="insert into "+orderItemTable+" values('"+quantity+"','"+name+"','"+type+"','"+price+"','"+description+"')";
						stmt.executeUpdate(query);
					}
					return orderId;
				}
			}catch(SQLException ex) {
				System.err.println("SQL Exception : "+ex.getMessage());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
//	public int saveOrder() {
}
