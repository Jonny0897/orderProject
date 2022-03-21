package strategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import product.ProductByPiece;
import product.ProductByWeight;
import ui.Cart;
import ui.Order;
import ui.Store;
import visitor.CartTotal;

public class PaymentTest {

	private Payment moneyPayment;
	private Payment CardPayment;
	private Order order;

	@Before
	public void setup() {
		moneyPayment = new MoneyPayment(50);
		CardPayment = new CardPayment("IT12345678987625");
		order = new Order(1234, new Cart(), moneyPayment);
	}

	@Test
	public void testPaymentNotSuccessful() {
		order.addProduct(new ProductByWeight(4.99, 3.5));
		assertThat(new MoneyPayment(5).payment(order.getTotal(new CartTotal()))).isFalse();
		assertThat(new CardPayment("IT10986").payment(order.getTotal(new CartTotal())));
	}

	@Test
	public void testPayment() {
		order.addProduct(new ProductByPiece(4.80, 3));
		order.proceedOrder(new Store(), new CartTotal());
		assertThat(moneyPayment.payment(order.getTotal(new CartTotal()))).isTrue();
		assertThat(CardPayment.payment(order.getTotal(new CartTotal()))).isTrue();
	}

}
