package Week07_01;

import java.io.*;
import java.util.Scanner;

import static java.lang.Character.*;

// 이걸거면 char CharCounter = 0; 왜 씀?

public class Test
{

    public static void main(String[] args) throws IOException {
        File file1 = new File("input.txt");
        File file2 = new File("output.txt");
        char CharCounter = 0;
        BufferedReader in = (new BufferedReader(new FileReader(file1)));
        PrintWriter out = (new PrintWriter(new FileWriter(file2)));

        int ch;

        while ((ch = in.read()) != -1) {
            if(isLowerCase(ch)){
                ch = toUpperCase(ch);
            }
            out.write(ch);
        }

        in.close();
        out.close();
    }
}
