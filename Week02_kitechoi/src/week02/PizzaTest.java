package week02;

class Circle {
	protected int radius;
	public Circle(int r) { radius = r; }
	}


class Pizza extends Circle {
	String menu;
	
	public Pizza(String m, int r) {
		super(r);
		menu = m;
	}
	
	public void print() {
		System.out.println(menu +" "+ radius);
	}
}

public class PizzaTest {
	public static void main(String args[]) {
		Pizza obj = new Pizza("Pepperoni", 20);
		obj.print();
		}
}
