package visitor;

import product.ProductByPiece;
import product.ProductByWeight;

public class CartTotal implements Visitor {

	@Override
	public double visitProductByPiece(ProductByPiece product) {
		return product.getPrice() * product.getPiece();
	}

	@Override
	public double visitProductByWeight(ProductByWeight product) {
		return product.getPrice() * product.getWeight();
	};

}
