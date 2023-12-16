package Week07_02;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class UserInform {
    public static void main(String[] args)throws IOException {
        int num2;
        String search;
        String num, name, tel, email;
        Scanner scan = null;
        PrintWriter in = new PrintWriter(new FileWriter("user.txt"));
        Scanner s = new Scanner(System.in);

        while (true) {
            out.println("번호: ");
            num = s.next();
            out.println("이름: ");
            name = s.next();
            out.println("전화번호: ");
            tel = s.next();
            out.println("이메일: ");
            email = s.next();
            out.println("입력이 끝났으면 1, 계속하려면 0:");
            num2 = s.nextInt();

//            각 변수 4개에 저장된 정보를 리스트화하여 파일 형태로 저장
//            입력이 끝났으니, 해당 파일을 통해 정보 검색_ 사용자로부터 키보드로 번호 입력 받고-> 거기 매핑되는 전번 출력
            public void UserInform(String num, String name, String tel, String email)  {
                    this.num = num;
                    this.name = name;
                    this.tel = tel;
                    this.email = email;
                }

            List<UserInform> list = new ArrayList<UserInform>();


            List<UserInform> result = list.stream()
                    .filter(p -> p.calories<=300)
                    .filter(p -> p.type == Type.OTHER)
                    .filter(p -> p.Vege = true)
            }


            in.print(num + "," + name + "," + tel + "," + email + "");
            in.flush();

            if(num2 == 1)
                break;
        }