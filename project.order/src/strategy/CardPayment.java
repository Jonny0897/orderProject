package strategy;

public class CardPayment implements Payment {

	private String cardNumber;

	public CardPayment(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Override
	public boolean payment(double totPrice) {
		return cardNumber.startsWith("IT") && cardNumber.length() == 16;
	}
}
