package product;

import visitor.Visitor;

public abstract class Product {

	private double price;

	public Product(double price) {
		this.price = price;
	}

	public abstract double accept(Visitor visitor);

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}