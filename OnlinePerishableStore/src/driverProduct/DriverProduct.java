package driverProduct;

import java.io.IOException;
import customer.Customer;
import database.DataBase;
import date.Date;
import order.Order;
import order.OrderedItem;
import product.Food;
import product.Fruit;
import product.Product;
import product.Vegetable;

public class DriverProduct{
	DataBase DB=null;
	DriverProduct(){
		DB=new DataBase();
	}
	public void drive() throws ClassNotFoundException, IOException{
		Product []products= {
				new Vegetable("Potato","potato"),
				new Food("Pizza","pizza"),
				new Fruit("Mango","mango")
		};
        OrderedItem []items= {
        		new OrderedItem(products[0],3),
        		new OrderedItem(products[1],98),
        		new OrderedItem(products[2],5)
        };
        Order []orders= {
        		new Order(),
        		new Order()
        };
        orders[0].addOrderedItem(items[0]);   
        orders[0].setDate(new Date(30,12,2021));
        orders[1].addOrderedItem(items[1]);
        orders[1].addOrderedItem(items[2]);
        orders[1].setDate(new Date(25,12,2021));

        // System.out.println(orderDuplicate);
        Customer []customers= {
        		new Customer("kohli"),
        		new Customer("dhoni")
        };
        
        // saving customers ... 
        customers[0].addOrder(orders[0]);
        customers[0].addOrder(orders[1]);
        customers[0].sortOrders();
        customers[1].addOrder(orders[1]);
        
        // serializing object ... 
        DB.saveCustomer(customers[0]);
        DB.saveCustomer(customers[1]);
        System.out.print(customers[0]);
        // reading serialized object ... 
        Customer loadedCustomer = null;
//    	loadedCustomer = DB.loadCustomer(customers[0].getId());
//        System.out.print(loadedCustomer);
	}
}