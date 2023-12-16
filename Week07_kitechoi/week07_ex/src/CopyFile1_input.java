import java.io.*;
import java.util.Scanner;
//CopyFile1을 ByteStreamsLab 참고해서 파일명부터 입력받으려고 했는데, 파일은 이진이 아니라서 안 되는 모습.
public class CopyFile1_input {
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
            System.out.println("**'/f' + 파일 복제 완료** " + outputFileName);

        }
    }
}