package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import observer.IObserver;
import product.Product;
import strategy.Payment;
import visitor.Visitor;

public class Order {

	private Collection<IObserver> observers = new ArrayList<>();
	private final int id;
	private Cart cart;
	private Payment payment;
	private boolean shipping;

	public Order(int id, Cart cart, Payment payment) {
		this.id = id;
		this.cart = cart;
		this.payment = payment;
		this.shipping = false;
	}

	public void proceedOrder(Store store, Visitor visitor) {
		if (payment.payment(getTotal(visitor))) {
			store.addOrder(this);
		} else
			throw new IllegalArgumentException("Impossible to proceed with the order");
	}

	public double getTotal(Visitor visitor) {
		return cart.getTotal(visitor);
	}

	public int getId() {
		return id;
	}

	public void addProduct(Product product) {
		cart.add(product);
	}

	public void removeProduct(Product product) {
		cart.remove(product);
	}

	public Iterator<Product> cartIterator() {
		return cart.cartIterator();
	}

	public void attach(IObserver ob) {
		observers.add(ob);
	}

	public void detach(IObserver ob) {
		observers.remove(ob);
	}

	public void notifyObserver() {
		observers.forEach(IObserver::update);
	}

	public boolean isShipping() {
		return shipping;
	}

	public void setShipping(boolean shipping) {
		this.shipping = shipping;
		notifyObserver();
	}

}
