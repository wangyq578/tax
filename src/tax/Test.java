package tax;

import java.util.*;
import tax.Consume;
import tax.BigDecimalOp;

public class Test {

	public static void main(String[] args) {

		double totalTax = 0.00;// 总税
		double total = 0.00;// 总额
		List<Consume> list = new ArrayList<Consume>();// list用于保存对象
		System.out.println("Please input the goods with price and end up with letter n:");
		Scanner input = new Scanner(System.in);

		while (input.hasNext()) {

			boolean isFoodMed = false;//是否为食品或药品
			boolean isImported = false;// 是否为进口商品
			String name = "";
			String nextString = input.next();
			name += nextString;
			// 以n作为输入结束
			if (nextString.equals("n")) {
				// 从list中依次取出商品输出税后价格，计算总税和总额
				for (Consume consume : list) {
					// 使用精确的BigDecimal运算
					totalTax = BigDecimalOp.add(totalTax, consume.getTax());
					total = BigDecimalOp.add(total, consume.getPWithTax());
					System.out.println(consume.getName() + ":" + consume.getPWithTax());
				}
				System.out.println("TAX:" + totalTax);
				System.out.println("TOTAL:" + total);
				// 将相关变量清空，准备读入下一组测试数据
				totalTax = 0.00;
				total = 0.00;
				list.clear();
				continue;
			}
			nextString = input.next();
			// at之前为商品名字
			while (!nextString.equals("at")) {
				// 含有imported表示商品为进口
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

			Consume.price = input.nextDouble();// 商品税前价格
			ConsumeFactory factory=new ConsumeFactory();//初始化一个工厂对象
			Consume consume = factory.createConsume("goods");//使用工厂创建对象
			//如果是进口,用ImportTax装饰它
			if (isImported)
				consume = factory.createConsume("imported",consume);//通过传入消费类型使用工厂创建对象
			//如果不是药品或食品，用NotFoodTax装饰它
			if (!isFoodMed)
				consume = factory.createConsume("notfood",consume);
			consume.setName(name);
			consume.setTax(consume.tax());

			consume.setPWithTax(consume.cost());
			list.add(consume);// 将对象加入list集合

		}

	}

}