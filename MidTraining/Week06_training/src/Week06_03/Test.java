package Week06_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum Type {
    MEAT, FISH, OTHER
} //열거형 상수집합을 정의하는 데이터 유형

public class Test {
    public static void main(String[] args) {

//        푸드 객체 중 if문 통과 객체 리스트화
//        =>근데 객체 조건들을 검색하려면 애초에 리스트여야 하는 거아님?

        List<Food> list = new ArrayList<Food>();
        list.add(new Food("스테이크",false,100,Type.MEAT));
        list.add(new Food("조기구이",false,15,Type.FISH));
        list.add(new Food("샐러드",true,10,Type.OTHER));
        list.add(new Food("토마토볶음",true,50,Type.OTHER));
        list.add(new Food("철판데리야끼",false,500,Type.MEAT));
        list.add(new Food("탕후루",true,301,Type.OTHER));

        List<Food> result = list.stream()
                .filter(p -> p.calories<=300)
                .filter(p -> p.type == Type.OTHER)
                .filter(p -> p.Vege = true)
//                .map(p -> p.name)
//                name을 map하지 않아도 name만 출력될 수 있음. List<Food>에다가 Food클래스 toString에서 return값을 name을 정의할 경우.
//      **********Food 클래스에서 toString을 어떻게 정의하는가가 중요함. 얠 정의 안 하면  Object가 지 알아서 해시값으로 반환해버리니 꼭 정의해야!
//                리턴되길 원하는 값을 우리가 지정해줘야 함.
                .collect(Collectors.toList());

//        만약 name만 리스트로써 출력하고 싶다? => 1. List<String>에 map을 이용 2. List<Food> 그대로에 toString 리턴값을 name으로 설정.

        //        List<String> result = list.stream()
//                .filter(p -> p.calories<=300)
//                .filter(p -> p.type == Type.OTHER)
//                .filter(p -> p.Vege = true)
//                .map(p -> p.name)
//                .collect(Collectors.toList());
        System.out.println(result);

    }
}



//
//
//import java.util.Scanner;
//
//public class Test {
//    public static void main(String[] args) {
//
//        // 사용자로부터 입력받을 변수 선언
//        String name;
//        boolean veg;
//        int calories;
//        Type type;
//
//        // 사용자로부터 입력 받기
//        Scanner scanner = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("음식 이름을 입력하세요(종료: q): ");
//            name = scanner.nextLine();
//
//            if (name.equals("q")) {
//                break;
//            }
//
//            // 음식 이름이 입력되었으면
//            if (name.length() > 0) {
//                // 채식 여부를 입력받습니다.
//                System.out.println("채식 여부를 입력하세요(true/false): ");
//                veg = scanner.nextBoolean();
//
//                // 칼로리를 입력받습니다.
//                System.out.println("칼로리를 입력하세요: ");
//                calories = scanner.nextInt();
//
//                // 음식 종류를 입력받습니다.
//                System.out.println("음식 종류를 입력하세요(MEAT/FISH/OTHER): ");
//                type = Type.valueOf(scanner.nextLine());
//
//                // 입력받은 값으로 Food 객체 생성
//                Food food = new Food(name, veg, calories, type);
//
//                // 리스트에 추가
//                list.add(food);
//            }
//        }
//
//        // 리스트에 있는 모든 음식 출력
//        for (Food food : list) {
//            System.out.println(food);
//        }
//    }
//}
