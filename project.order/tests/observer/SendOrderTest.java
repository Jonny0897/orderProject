package observer;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import product.ProductByPiece;
import product.ProductByWeight;
import strategy.CardPayment;
import strategy.MoneyPayment;
import ui.Cart;
import ui.Order;

public class SendOrderTest {

	private Order order;
	private Order anotherOrder;
	private SendOrder sendOrder;
	private SendOrder anotherSendOrder;

	@Before
	public void setup() {
		order = new Order(51255, new Cart(), new MoneyPayment(100));
		sendOrder = new SendOrder(order);
		anotherOrder = new Order(15723, new Cart(), new CardPayment("IT12345678987625"));
		anotherSendOrder = new SendOrder(anotherOrder);
	}

	@Test
	public void testUpdate() {
		order.addProduct(new ProductByPiece(5.9, 2));
		anotherOrder.addProduct(new ProductByWeight(3.6, 4.5));

		assertThat(order.isShipping()).isEqualTo(sendOrder.getOrderShipping());
		order.attach(sendOrder);
		order.setShipping(true);
		assertThat(order.isShipping()).isEqualTo(sendOrder.getOrderShipping());

		anotherOrder.setShipping(true);
		assertThat(anotherOrder.isShipping()).isNotEqualTo(anotherSendOrder.getOrderShipping());

	}

}
