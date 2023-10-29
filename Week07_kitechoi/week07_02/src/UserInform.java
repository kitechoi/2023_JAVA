import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserInform {

    static class User {
        String num;
        String name;
        String tel;
        String email;
    }

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        PrintWriter in = new PrintWriter("user.txt");

        List<User> users = new ArrayList<>();

        while (true) {
            User user = new User();

            System.out.println("번호: ");
            user.num = s.next();

            System.out.println("이름: ");
            user.name = s.next();

            System.out.println("전화번호: ");
            user.tel = s.next();

            System.out.println("이메일: ");
            user.email = s.next();

            users.add(user);

            System.out.println("입력이 끝났으면 1, 계속하려면 0:");
            int num2 = s.nextInt();

            if (num2 == 1) {
                break;
            }
        }

        // 각 변수 4개에 저장된 정보를 리스트화하여 파일 형태로 저장
        for (User user : users) {
            in.print(user.num + "," + user.name + "," + user.tel + "," + user.email + "\n");
            in.flush();
//            flush() 안쓰면 오류는 안나지만, 입력한 정보가 파일에 저장되지 않음.
//            flush만 쓰고 print를 안 쓸 경우에도 파일 출력 형식이 안 정해져서 그런지 파일에 저장이 안됨.
        }
//        in.close(); close()는 FileWriter일 때 씀.


//            입력이 끝났으니, 해당 파일을 통해 정보 검색_ 사용자로부터 키보드로 번호 입력 받고-> 거기 매핑되는 전번 출력
        System.out.println("검색할 번호를 입력하세요: ");
        String search = s.next();

        for (User user : users) {
            if (user.num.equals(search)) {
                System.out.println("전화번호: " + user.tel);
                break;
            }
        }
    }


}
