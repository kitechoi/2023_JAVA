import java.io.*;
import java.util.Scanner;
//working directory를 어떻게 설정하느냐에 따라 적합한 이미지 경로가 바뀐다.
//상단 상하점세개에서 Edit을 눌러 working directory를 설정할 수 있다.
//현재 working directory는 week07_ex로 설정했으며, 이에 따라 src와 동등한 위치인 week07_ex 바로 안에 이미지를 둬야 함.

public class ByteStreamsLab {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("원본 파일 명을 입력하세요: ");

        String inputFileName= scan.next();
        System.out.print("복사 파일 명을 입력하세요: ");
        String outputFileName = scan.next();

        try (InputStream inputStream = new FileInputStream(inputFileName);
             OutputStream outputStream = new FileOutputStream(outputFileName)) {

            int c;

            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
            System.out.println("* '/f' + 이미지 복제 완료*" + outputFileName);
        }
    }
}
