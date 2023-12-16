package Mid_02;

import java.io.*;
import java.util.Scanner;




public class Test
{

    public static void main(String[] args) throws IOException {
        File file1 = new File("obama.txt");
        File file2 = new File("output.txt");
        char CharCounter = 0;
        BufferedReader in = (new BufferedReader(new FileReader(file1)));
        PrintWriter out = (new PrintWriter(new FileWriter(file2)));
        int ch;
        while ((ch = in.read()) != -1)
        {
            if(Character.isLowerCase(ch)) {   // Character는 클래스인 거라서, import하면 안 써도 되긴 함
                ch = Character.toUpperCase(ch);
            }
            out.write(ch);
        }
        in.close();
        out.close();
    }
}


