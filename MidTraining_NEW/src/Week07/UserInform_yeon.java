package Week07;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Character.isLowerCase;

public class UserInform_yeon {

    static class User {
        String num;
        String name;
        String tel;
        String email;

        @Override
        public String toString() {
            return num + "," + name + "," + tel + "," + email;
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = null;
        int num2;
        Scanner s = new Scanner(System.in);
        PrintWriter in = new PrintWriter(new FileWriter("user2.txt"));

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
            num2 = s.nextInt();

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


//        입력이 끝났으니, 해당 파일을 통해 정보 검색_ 사용자로부터 키보드로 번호 입력 받고-> 거기 매핑되는 전번 출력
//        ???해당 파일 통해서가 아닌데? 그냥 여기 저장된 값 토대로 하는데?

        System.out.println("검색할(전번조건길이) 번호를 입력하세요: ");
        String search = s.next();
        int num222= Integer.parseInt(search);

//        파일 입력 받아서 파일 기반으로 정보 찾아 매핑 후 출력하기로 변경해보자.

        scan = new Scanner(new BufferedReader(new FileReader("user2.txt")));
        scan.useDelimiter("\n"); // 각 사용자 정보를 개행으로 구분
        boolean found = false;    // 초기 found를 false로 뒀기에, 아래 if 검색문에서 true화되지 않으면 그대로 false라 검색 실패로 연결됨.
        // 사용자에게 검색 결과를 저장할 파일명을 입력받습니다.
//        System.out.println("검색 결과를 저장할 파일명을 입력하세요: ");
//        String findUserFileName = s.next();
//        PrintWriter findUserWriter = new PrintWriter(new FileWriter(findUserFileName));
        PrintWriter findUserWriter = new PrintWriter(new FileWriter("find_user2.txt"));

        List<String> nameList = new ArrayList<>();  //         sort를 해야 한다면?

        while (scan.hasNext()) {  // hasNext()는 무슨 의미? _다음 읽은 것이 있냐.__ 이미 위에서 개행구분으로써 데이터는 한줄구분된 상태.

            String userInfo = scan.next();
            String[] userInfoParts = userInfo.split(",");
            String num = userInfoParts[0];
            String name = userInfoParts[1];
            String tel = userInfoParts[2];
            String email = userInfoParts[3];

//           filter 조건에 따라 여러 개가 출력될 수 있을 때
            if (tel.length() >= num222) {
                //이때 출력될 이름을 sorted하고 싶다면?
                nameList.add(name);

//              System.out.println("사용자 번호 " + search + "의 전화번호는 " + tel + "입니다.");
                found = true;
                findUserWriter.print(num + "," + name + "," + tel + "," + email + "\n");
//                break;
            }

//            if (num.equals(search)&& tel.length() >= 5)  => 만약 filter 같은 조건문 추가하고 싶다면

//            if (num.equals(search)) {
////                name = name.toUpperCase();  만약 name을 대문자화해서 출력하고 싶다면
//                System.out.println("사용자 번호 " + search + "의 전화번호는 " + tel + "입니다.");
//                found = true;
//                // 검색 결과를 find_user.txt 파일에 저장
//                findUserWriter.print(num + "," + name + "," + tel + "," + email + "\n");
//                break; // 해당 사용자를 찾았으면 검색 종료
//            }
        }

//        sort
        Collections.sort(nameList);
        System.out.println(nameList);

//        아래 주석도 sort 때문에 쓰는 건데, 출력값이 name 하나라면 위처럼 나타내는 게 나음.
//        for (String name : nameList) {
//            System.out.print(name+ ",");
//        }


        if (!found) {
            System.out.println("사용자 번호 " + search + "을 찾을 수 없습니다.");
        }
        findUserWriter.close(); // find_user.txt 파일을 닫습니다.

        if (scan != null)
            scan.close();


//        입력받아서 저장된 users에서 검색하기 (파일 아님)
//        for (User user : users) {
//            if (user.num.equals(search)) {
//                System.out.println(user.tel);
//                break;
//            }
//        }


    }


}
