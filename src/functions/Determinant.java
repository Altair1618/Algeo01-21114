package functions;
import dataStructure.*;




public class Determinant {
	static final double UNDEF_DET = -9999.99;

	public static double detExCof(Matrix m, int size) {
		if (m.isSquare()) {
			if (size==1) {
				return (m.getElement(1, 1));
			}
			else if (size==2) {
				return (m.getElement(1, 1)*m.getElement(2, 2))-(m.getElement(2,1)*m.getElement(1, 2));
			}
			
			else {
				double result =0;
				for (int k=1; k<=size; k++) {
					result += m.getElement(k, 1)*cofactor(m, k, 1, size);
				}
				return result;
			}
		}
		
		else {
			return UNDEF_DET;
		}
		
	}
	
	static Matrix minor(Matrix m, int brs, int klm, int size) {
		Matrix result = new Matrix();
		result.createMatrix(size-1, size-1);
		for (int i=1; i<=size-1; i++) {
			for (int j=1; j<=size-1; j++) {
				int k=i, l=j;
				if (i>=brs) {
					k++;
				}
				if (j>=klm) {
					l++;
				}
				result.setElement(m.getElement(k, l), i, j);
			}
		}
		return result;
	}
	
	static double cofactor(Matrix m, int brs, int klm, int size) {
		return Math.pow(-1, brs+klm)*detExCof(minor(m, brs, klm, size), size-1);
	}
	
}



