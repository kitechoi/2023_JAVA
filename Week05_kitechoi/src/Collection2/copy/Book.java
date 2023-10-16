package Collection2.copy;

public class Book {
	private int BookId;
	private String BookName;
	
//	이런 클래스함수? 를 뭐라고 하더라. 입력 받기만 하는 것 같은데 왜 void, return 둘 다 없지?
	public Book(int BookId, String BookName) {
		this.BookId = BookId;
		this.BookName = BookName;
	}
	
	public int getBookId() {
		return BookId;
	}
	public void setBookId(int BookId) {
		this.BookId = BookId;
	}
	public String getBookName() {
		return BookName;
	}
	
	public void setBookName(String setBookName) {
		this.BookName = BookName;
	}
	
	public String toString() {	
		return BookName +"도서의 아이디는 "+ BookId + "입니다";
	}
}
