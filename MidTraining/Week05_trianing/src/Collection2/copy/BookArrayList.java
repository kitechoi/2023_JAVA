package Collection2.copy;

import java.util.ArrayList;

public class BookArrayList {
    private ArrayList<Book> arrayList; //arrayList선언

    public BookArrayList() {
        arrayList = new ArrayList<Book>(); //Book형으로 선언한 ArrayList생성
    }

    public void addBook(Book book) { //Book데이터 추가
        arrayList.add(book);
    }

    public boolean removeBook(int BookId) {
        for(int i = 0; i < arrayList.size();i++) {
            Book book = arrayList.get(i);
            int tempId = book.getBookId();
            if(tempId == BookId) {
                arrayList.remove(i);
                return true;
            }
        }
        System.out.println(BookId + "가 존재하지 않습니다.");
        return false;
    }



    public void showAllBook() {
        for(Book book : arrayList) {
            System.out.println(book);
        }
        System.out.println();
    }

    public void insertBook(int insertIndex, Book newBook) {
        arrayList.add(insertIndex, newBook);
    }
}