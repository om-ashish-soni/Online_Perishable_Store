package driverProduct;

import java.io.IOException;

public class DriverBusinessDomainProduct{
	public static void main(String[] args) throws ClassNotFoundException, IOException{
		DriverProduct driverProduct=new DriverProduct();
//		new jdbc().drive();
		driverProduct.drive();
	}
}


