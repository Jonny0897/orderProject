package product;

import visitor.Visitor;

public class ProductByWeight extends Product {

	private double weight;

	public ProductByWeight(double price, double weight) {
		super(price);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public double accept(Visitor visitor) {
		return visitor.visitProductByWeight(this);
	}

}
