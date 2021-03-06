package order;

import java.io.Serializable;
import product.Product;

public class OrderedItem implements Serializable{
	// declarations ... 
	Product product=null;
	int quantity;
	
    // constructor ... 
	public OrderedItem(Product product,int quantity) {
		this.product=product;
		this.quantity=quantity;
	}
	// getters and setters ... 
    public int getQuantity(){
        return this.quantity;
    }
    public Product getProduct() {
    	return this.product;
    }
    @Override
    public String toString(){
        return new String(this.product.toString()+"Quantity = "+this.quantity);
    }
}