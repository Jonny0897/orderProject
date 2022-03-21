package observer;

import ui.Order;

public class SendOrder implements IObserver {

	private Order order;
	private boolean orderShipping;

	public SendOrder(Order order) {
		this.order = order;
		orderShipping = order.isShipping();
	}

	@Override
	public void update() {
		orderShipping = this.order.isShipping();
	}

	public boolean getOrderShipping() {
		return orderShipping;
	}
}
