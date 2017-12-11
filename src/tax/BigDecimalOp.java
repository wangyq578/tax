package tax;

import java.math.BigDecimal;

//����Java��double���ܹ���ȷ�ؽ������㣬������ṩ��ȷ�ĸ���������
public class BigDecimalOp {
	// �ṩ��ȷ�ļӷ�����
	public static double add(double v1, double v2) {
		// ��double��ת��ΪBigDecimal��
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		// �������뱣����λС����ת����double��
		return b1.add(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	// �ṩ��ȷ�ĳ˷�����
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}