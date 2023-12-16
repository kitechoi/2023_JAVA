package Week06_01;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//예제는 스트림 이용해서 풀었음
public class MethodReferenceLowerCase {


    public static void main(String[] args) {
        List<String> listOfName = Arrays.asList(new String[]
                {"Apple", "Banana", "Cherry"});
        System.out.println("처음 리스트 "+listOfName);

        for (String name : listOfName) {
            String newName = name.toLowerCase();
            listOfName.set(listOfName.indexOf(name), newName);
        }

        System.out.println("처리 후 리스트 "+listOfName);


    }
}
