public class Book {
    private String title;
    private String author;
    private String isbn;
    private String type;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn, String type) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.type = type;
        this.isBorrowed = false;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        this.isBorrowed = true;
    }

    public void displayInfo() {
        System.out.println("عنوان الكتاب: " + title);
        System.out.println("المؤلف: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("النوع: " + type);
        System.out.println("الحالة: " + (isBorrowed ? "معار" : "متوفر"));
        System.out.println("--------------------");
    }
}
