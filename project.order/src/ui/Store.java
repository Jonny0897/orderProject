package ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Store {

	private Collection<Order> orders = new ArrayList<>();

	public void addOrder(Order order) {
		orders.add(order);
	}

	public void removeOrder(Order order) {
		orders.remove(order);
	}

	public void takeOrder() {
		Order order = getOrder();
		removeOrder(order);
	}

	public Iterator<Order> orderIterator() {
		return orders.iterator();
	}

	private Order getOrder() {
		return orders.stream().filter(o -> o.isShipping() == true).findAny().get();
	}
}
