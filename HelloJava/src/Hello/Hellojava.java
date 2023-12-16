package Hello;
import java.util.Scanner;


public class Hellojava {
	public static void main(String[] args) {
		System.out.println("Hello java");
		
		Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();
		
		if(num == 1) {
			System.out.println("Hi");
		} else if(num == 2) {
			System.out.println("Bye");
		} else {
			System.out.println("다시 입력하세요");
		}
		
	}

}
