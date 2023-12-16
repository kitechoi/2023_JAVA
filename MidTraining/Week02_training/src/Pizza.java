class Circle {
    protected int radius;
    public Circle(int r) {radius = r;}

    protected void print() {
        System.out.println("헤헤" + radius);
    }
}

public class Pizza extends Circle {
    String menu;

    public Pizza(String menu, int r) {
        super(r);
        this.menu = menu;
    }
    @Override
    protected void print() {
        System.out.println(menu +" "+ radius + "개");
    }


    public static void main(String args[]) {
        Pizza obj = new Pizza("Pepperoni", 20);
        obj.print();

        Circle thing = new Circle(2);
        thing.print();
    }
}
