package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import customer.Customer;


public class DataBase implements Serializable{
	String location="D:\\JAVA_THE_ETERNAL_LANG\\PROJECTS_OF_JAVA\\ONLINE_PERISHSABLE_STORE/";
	String ext=".ser";
	String filename;
	public DataBase(){
		
	}
	public void saveCustomer(Customer cust1) {
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
	public void updateCustomer(Customer cust1) {
		this.saveCustomer(cust1);
	}
	public Customer loadCustomer (int customerId) throws IOException, ClassNotFoundException {
		filename=location+customerId+ext;
		FileInputStream fis=new FileInputStream(filename);
		ObjectInputStream ois=new ObjectInputStream(fis);
		return (Customer)ois.readObject();
	}
}
