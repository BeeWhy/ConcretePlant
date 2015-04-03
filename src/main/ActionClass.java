package main;

import exceptions.EmptyOrderException;
import model.ConcretePlant;
import model.Customer;
import model.ICustomer;

class ActionClass {

	public static void main(String[] args) {
		ConcretePlant plant = new ConcretePlant();
		ICustomer Alice = new Customer("Alice");
		plant.produceAnotherProduct();
		plant.produceAnotherProduct();
		try {
			Alice.makeOrder(plant, 5);
		} catch (EmptyOrderException e) {
			System.out.println("You can not make an order for 0 items!");
			e.printStackTrace();
		}
		
		plant.produceAnotherProduct();
		plant.produceAnotherProduct();
		plant.produceAnotherProduct();
		
		

	}

}
