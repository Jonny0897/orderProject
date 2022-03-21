package visitor;

import product.ProductByPiece;
import product.ProductByWeight;

public interface Visitor {

	double visitProductByPiece(ProductByPiece product);

	double visitProductByWeight(ProductByWeight product);

}
