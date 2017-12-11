package tax;

import java.util.*;

import javafx.scene.Parent;
import tax.BigDecimalOp;
//ʹ��װ���ߺ͹������ģʽ��tax����
public abstract class Consume {
	public abstract double cost();//������Ʒ���Ѷ�

	public abstract double tax();//������Ʒ˰��

	public static String[] foodMed = { "1 chocolate bar", "1 imported box of chocolates",
			"1 packet of headache pills" }; // ʳ����ҩƷ����
	private String name = ""; // ��Ʒ����
	public static double price = 0.00; // ��Ʒ˰ǰ�۸�

	private double tax = 0.00; // ��Ʒ˰
	private double pWithTax = 0.00; // ˰��۸�

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
//��װ������
class Goods extends Consume {
	//ʵ��cost������ֱ�ӷ���ԭʼ�۸�
	public double cost() {
		return price;
	}

	public double tax() {
		return 0;
	}

}
//װ������ĳ�����
abstract class Decorator extends Consume{

}
//����˰װ������
class ImportTax extends Decorator{
	Consume consume;
	//�ѱ�װ���ߵ����������Ĳ��������ɹ�������¼��ʵ��������
	public ImportTax(Consume consume) {
		this.consume = consume;
	}
	//ʵ��tax������������Ͻ���˰��˰����ȵ��ñ�װ�ζ����tax�������ڼ��Ͻ���˰
	public double tax() {
		return BigDecimalOp.add(consume.tax(), BigDecimalOp.mul(price, 0.05));
	}
	//ʵ��cost���������������˰�ļ۸����ȵ��ñ�װ�ζ����cost�������ټ��϶�Ӧ�Ľ���˰
	public double cost() {
		return BigDecimalOp.add(BigDecimalOp.mul(price, 0.05), consume.cost());
	}
}
//��ʳƷҩƷ˰װ������
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
	//�ͻ��ô˷�����ʵ��������
	public Consume createConsume(String type)
	{

		if(type.equals("goods"))
		{
			consume=new Goods();
		}
		return consume;
	}
	//���ط���������ʵ����װ���߶���
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