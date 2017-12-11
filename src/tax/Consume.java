package tax;

import java.util.*;

import javafx.scene.Parent;
import tax.BigDecimalOp;
//使用装饰者和工厂设计模式的tax程序
public abstract class Consume {
	public abstract double cost();//计算商品消费额

	public abstract double tax();//计算商品税额

	public static String[] foodMed = { "1 chocolate bar", "1 imported box of chocolates",
			"1 packet of headache pills" }; // 食物与药品集合
	private String name = ""; // 商品名称
	public static double price = 0.00; // 商品税前价格

	private double tax = 0.00; // 商品税
	private double pWithTax = 0.00; // 税后价格

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getPWithTax() {
		return pWithTax;
	}

	public void setPWithTax(double pWithTax) {
		this.pWithTax = pWithTax;
	}
}
//被装饰者类
class Goods extends Consume {
	//实现cost方法，直接返回原始价格
	public double cost() {
		return price;
	}

	public double tax() {
		return 0;
	}

}
//装饰者类的抽象父类
abstract class Decorator extends Consume{

}
//进口税装饰者类
class ImportTax extends Decorator{
	Consume consume;
	//把被装饰者当作构造器的参数，再由构造器记录到实例变量中
	public ImportTax(Consume consume) {
		this.consume = consume;
	}
	//实现tax方法，计算加上进口税的税额，首先调用被装饰对象的tax方法，在加上进口税
	public double tax() {
		return BigDecimalOp.add(consume.tax(), BigDecimalOp.mul(price, 0.05));
	}
	//实现cost方法，计算带进口税的价格，首先调用被装饰对象的cost方法，再加上对应的进口税
	public double cost() {
		return BigDecimalOp.add(BigDecimalOp.mul(price, 0.05), consume.cost());
	}
}
//非食品药品税装饰者类
class NotFoodTax extends Decorator {
	Consume consume;

	public NotFoodTax(Consume consume) {
		this.consume = consume;
	}

	public double tax() {
		return BigDecimalOp.add(BigDecimalOp.mul(price, 0.10), consume.tax());
	}

	public double cost() {
		return BigDecimalOp.add(BigDecimalOp.mul(price, 0.10), consume.cost());
	}

}
class ConsumeFactory{
	Consume consume=null;
	//客户用此方法来实例化对象
	public Consume createConsume(String type)
	{

		if(type.equals("goods"))
		{
			consume=new Goods();
		}
		return consume;
	}
	//重载方法，用来实例化装饰者对象
	public Consume createConsume(String type,Consume consume)
	{

		if(type.equals("imported"))
		{
			this.consume= new ImportTax(consume);
		}
		if(type.equals("notfood"))
		{
			this.consume= new NotFoodTax(consume);
		}
		return this.consume;
	}
}