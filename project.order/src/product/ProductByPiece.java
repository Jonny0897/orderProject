package product;

import visitor.Visitor;

public class ProductByPiece extends Product {

	private final int piece;

	public ProductByPiece(double price, int piece) {
		super(price);
		this.piece = piece;
	}

	public int getPiece() {
		return piece;
	}

	@Override
	public double accept(Visitor visitor) {
		return visitor.visitProductByPiece(this);
	}

}
