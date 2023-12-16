package Week07;

import java.io.*;
import java.util.*;

public class UserInform_professor {
    public static void main(String[] args) throws IOException {
        int num2;
        String search;
        String num, name, tel, email;
        Scanner scan = null;
        PrintWriter in = new PrintWriter(new FileWriter("user.txt"));  // 사용자입력 파일화. 그냥 PrintWriter로 해도 똑같이 돌아감
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("사용자 번호를 입력하세요 ");
            num = s.next();
            System.out.println("사용자 이름을 입력하세요 ");
            name = s.next();
            System.out.println("사용자 전화번호를 입력하세요 ");
            tel = s.next();
            System.out.println("사용자 이메일을 입력하세요 ");
            email = s.next();
            System.out.println("입력이 끝났으면 1, 계속 입력하시려면 0");
            num2 = s.nextInt();
            in.print(num + "," + name + "," + tel + "," + email + "\n"); // 각 사용자 정보를 개행으로 구분
            in.flush();   //   교수님 코드에선 flush 없어도 파일 저장까지 잘 작동됨
            if (num2 == 1)
                break;
        }
        in.close(); // 파일 쓰기 완료 후 PrintWriter를 닫습니다.

        System.out.println("검색하실 사용자 번호를 입력하세요");
        num2 = s.nextInt();
        search = num2 + "";   //num2는 int형, search는 문자형이어야 해서 이렇게 어거지로 문자형화함

        scan = new Scanner(new BufferedReader(new FileReader("user.txt")));
        scan.useDelimiter("\n"); // 각 사용자 정보를 개행으로 구분
        boolean found = false;
        PrintWriter findUserWriter = new PrintWriter(new FileWriter("find_user.txt"));
        while (scan.hasNext()) {  // hasNext()는 무슨 의미? _다음 읽은 것이 있냐.__ 이미 위에서 개행구분으로써 데이터는 한줄구분된 상태.
            String userInfo = scan.next();  // 이때 userInfo는 1,dd,111,dd@ 같은 형태
            String[] userInfoParts = userInfo.split(",");
            num = userInfoParts[0];
            name = userInfoParts[1];
            tel = userInfoParts[2];
            email = userInfoParts[3];
//            if (num.equals(search)&& tel.length() >= 5)  => 만약 filter 같은 조건문 추가하고 싶다면
            if (num.equals(search)) {
                //                name = name.toUpperCase();  만약 name를 대문자로 출력하고 싶다면
                  System.out.println("사용자 번호 " + num2 + "의 전화번호는 " + tel + "입니다.");
                found = true;
                // 검색 결과를 find_user.txt 파일에 저장
                findUserWriter.print(num + "," + name + "," + tel + "," + email + "\n");
                break; // 해당 사용자를 찾았으면 검색 종료
            }
        }
        if (!found) {
            System.out.println("사용자 번호 " + num2 + "을 찾을 수 없습니다.");
        }
        findUserWriter.close(); // find_user.txt 파일을 닫습니다.

        if (scan != null)
            scan.close();
    }
}

//한글 입력시 깨지는 문제가 발생하여, 이름 입력할때, 반드시 영문으로 입력해야 함