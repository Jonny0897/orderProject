package order;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import product.Product;
import product.ProductByPiece;
import product.ProductByWeight;
import strategy.CardPayment;
import strategy.MoneyPayment;
import ui.Cart;
import ui.Order;
import ui.Store;
import visitor.CartTotal;

public class OrderTest {

	private Store store;
	private Cart cart;
	private Order order;
	private Product productByPiece;
	private Product productByWeight;
	private Order newOrder;

	@Before
	public void setup() {
		store = new Store();

		cart = new Cart();
		order = new Order(1234, cart, new MoneyPayment(100));
		newOrder = new Order(3467, new Cart(), new CardPayment("IT123456"));

		productByPiece = new ProductByPiece(5.5, 2);
		productByWeight = new ProductByWeight(3.9, 5);
	}

	@Test
	public void testProduct() {
		assertThat(productByPiece.getPrice()).isEqualTo(5.5);
		assertThat(productByWeight.getPrice()).isEqualTo(3.9);
	}

	@Test
	public void testImpossibleToProceedOrder() {
		assertThatThrownBy(() -> newOrder.proceedOrder(store, new CartTotal()))
				.isInstanceOf(IllegalArgumentException.class).hasMessage("Impossible to proceed with the order");
	}

	@Test
	public void testProceedOrder() {
		order.addProduct(productByPiece);
		order.addProduct(productByWeight);
		order.proceedOrder(store, new CartTotal());
		assertThat(store.orderIterator()).toIterable().containsExactly(order);
	}

	@Test
	public void testGetTotalPrice() {
		assertThat(order.cartIterator()).toIterable().isEmpty();
		assertThat(order.getTotal(new CartTotal())).isEqualTo(0);

		order.addProduct(productByPiece);
		order.addProduct(productByWeight);
		assertThat(order.getTotal(new CartTotal())).isEqualTo(30.5);
		assertThat(order.cartIterator()).toIterable().containsExactlyInAnyOrder(productByPiece, productByWeight);
	}
}