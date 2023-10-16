package Collection2.copy;

import java.util.ArrayList;

import Collection.Book;

public class BookArrayList {
	private ArrayList<Book> arrayList;
	
	public BookArrayList() {
		arrayList = new ArrayList<Book>();
	}
	
	public void addBook(Book book) {
		arrayList.add(book);
	}
	
	public boolean removeBook(int BookId) {
		for(int i = 0; i < arrayList.size(); i++) {
			Book book = arrayList.get(i);
			int tempId = book.getBookId();
//			tempId가 현재 번호. BookId가 지우고자 하는 번호
			if(tempId == BookId) {
				arrayList.remove(i);
				return true;
			}
		}
		System.out.println(BookId + "가 존재하지 않습니다.");
		return false;
//		return false의 의미는 if문이 실행되지 않을 경우의 else와 유사한 역할을 함.
//		함수가 불린형이니 최종리턴은 T/F 중 하나가 되어야 함. 
	}
	
	public void insertBook(int index, Book book) {
    	arrayList.add(index, book);
    }
	
	
	public void showAllBook() {
		for(Book book : arrayList) {
			System.out.println(book);
		}
		System.out.println();
	}
	
	
	
}
