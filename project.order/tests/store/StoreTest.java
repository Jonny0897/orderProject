package store;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import observer.SendOrder;
import product.ProductByPiece;
import product.ProductByWeight;
import strategy.CardPayment;
import strategy.MoneyPayment;
import ui.Cart;
import ui.Order;
import ui.Store;
import visitor.CartTotal;

public class StoreTest {

	private Store store;
	private Order order51255;
	private Order order15723;
	private Order order98732;

	private SendOrder send51255;
	private SendOrder send98732;

	@Before
	public void setup() {
		store = new Store();
		order51255 = new Order(51255, new Cart(), new MoneyPayment(100));
		order15723 = new Order(15723, new Cart(), new CardPayment("IT12345678987625"));
		order98732 = new Order(98732, new Cart(), new MoneyPayment(60));
		send51255 = new SendOrder(order51255);
		send98732 = new SendOrder(order98732);
	}

	@Test
	public void testTakeOrder() {
		order51255.addProduct(new ProductByPiece(4.40, 3));
		order51255.proceedOrder(store, new CartTotal());

		order15723.addProduct(new ProductByWeight(3.80, 5.10));
		order15723.proceedOrder(store, new CartTotal());

		order98732.addProduct(new ProductByWeight(5.6, 4.3));
		order98732.proceedOrder(store, new CartTotal());

		order51255.attach(send51255);
		order51255.setShipping(true);

		order98732.attach(send98732);
		order98732.setShipping(true);

		assertThat(store.orderIterator()).toIterable().containsExactlyInAnyOrder(order51255, order15723, order98732);

		store.takeOrder();
		store.takeOrder();

		assertThat(store.orderIterator()).toIterable().containsExactlyInAnyOrder(order15723);

	}
}
