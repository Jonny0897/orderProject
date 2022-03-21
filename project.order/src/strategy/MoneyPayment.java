package strategy;

public class MoneyPayment implements Payment {

	private double money;

	public MoneyPayment(double money) {
		this.money = money;
	}

	@Override
	public boolean payment(double totPrice) {
		return money >= totPrice;
	}

}
