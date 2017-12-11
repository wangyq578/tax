package tax;

import java.math.BigDecimal;

//由于Java的double不能够精确地进行运算，这个类提供精确的浮点数运算
public class BigDecimalOp {
	// 提供精确的加法运算
	public static double add(double v1, double v2) {
		// 将double型转换为BigDecimal型
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		// 四舍五入保留两位小数并转换回double型
		return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// 提供精确的乘法运算
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}