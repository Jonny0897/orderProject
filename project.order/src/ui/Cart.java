package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import product.Product;
import visitor.Visitor;

public class Cart {

	private Collection<Product> cart = new ArrayList<>();
	private double total;

	public Cart() {
		this.total = 0;
	}

	public void add(Product product) {
		cart.add(product);
	}

	public void remove(Product product) {
		cart.remove(product);
	}

	public Iterator<Product> cartIterator() {
		return cart.iterator();
	}

	public double getTotal(Visitor visitor) {
		Iterator<Product> iter = cartIterator();
		while (iter.hasNext()) {
			total = total + iter.next().accept(visitor);
		}
		return total;
	}
}
