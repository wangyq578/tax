package tax;

import java.util.*;
import tax.Consume;
import tax.BigDecimalOp;

public class Test {

	public static void main(String[] args) {

		double totalTax = 0.00;// ��˰
		double total = 0.00;// �ܶ�
		List<Consume> list = new ArrayList<Consume>();// list���ڱ������
		System.out.println("Please input the goods with price and end up with letter n:");
		Scanner input = new Scanner(System.in);

		while (input.hasNext()) {

			boolean isFoodMed = false;//�Ƿ�ΪʳƷ��ҩƷ
			boolean isImported = false;// �Ƿ�Ϊ������Ʒ
			String name = "";
			String nextString = input.next();
			name += nextString;
			// ��n��Ϊ�������
			if (nextString.equals("n")) {
				// ��list������ȡ����Ʒ���˰��۸񣬼�����˰���ܶ�
				for (Consume consume : list) {
					// ʹ�þ�ȷ��BigDecimal����
					totalTax = BigDecimalOp.add(totalTax, consume.getTax());
					total = BigDecimalOp.add(total, consume.getPWithTax());
					System.out.println(consume.getName() + ":" + consume.getPWithTax());
				}
				System.out.println("TAX:" + totalTax);
				System.out.println("TOTAL:" + total);
				// ����ر�����գ�׼��������һ���������
				totalTax = 0.00;
				total = 0.00;
				list.clear();
				continue;
			}
			nextString = input.next();
			// at֮ǰΪ��Ʒ����
			while (!nextString.equals("at")) {
				// ����imported��ʾ��ƷΪ����
				if (nextString.equals("imported")) {
					isImported = true;
				}
				name = name + " " + nextString;
				List<String> tempList = Arrays.asList(Consume.foodMed);

				if (tempList.contains(name)) {
					isFoodMed = true;

				}
				nextString = input.next();
			}

			Consume.price = input.nextDouble();// ��Ʒ˰ǰ�۸�
			ConsumeFactory factory=new ConsumeFactory();//��ʼ��һ����������
			Consume consume = factory.createConsume("goods");//ʹ�ù�����������
			//����ǽ���,��ImportTaxװ����
			if (isImported)
				consume = factory.createConsume("imported",consume);//ͨ��������������ʹ�ù�����������
			//�������ҩƷ��ʳƷ����NotFoodTaxװ����
			if (!isFoodMed)
				consume = factory.createConsume("notfood",consume);
			consume.setName(name);
			consume.setTax(consume.tax());

			consume.setPWithTax(consume.cost());
			list.add(consume);// ���������list����

		}

	}

}