import java.util.*;
import java.time.LocalDate;

public class LibraryApp {
    private static List<Book> books = new ArrayList<>();
    private static List<Borrower> borrowers = new ArrayList<>();
    private static List<BorrowingProcess> borrowings = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n--- نظام إدارة مكتبة ---");
            System.out.println("1. إضافة كتاب");
            System.out.println("2. عرض الكتب");
            System.out.println("3. إضافة مستعير");
            System.out.println("4. استعارة كتاب");
            System.out.println("5. عرض عمليات الاستعارة");
            System.out.println("6. خروج");
            System.out.print("اختر خياراً: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // للتخلص من السطر المتبقي

            switch (choice) {
                case 1 -> addBook();
                case 2 -> showBooks();
                case 3 -> addBorrower();
                case 4 -> borrowBook();
                case 5 -> showBorrowings();
                case 6 -> running = false;
                default -> System.out.println("خيار غير صالح!");
            }
        }
    }

    private static void addBook() {
        System.out.print("العنوان: ");
        String title = scanner.nextLine();
        System.out.print("المؤلف: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("النوع (ورقي/إلكتروني): ");
        String type = scanner.nextLine();

        books.add(new Book(title, author, isbn, type));
        System.out.println("تمت إضافة الكتاب بنجاح.");
    }

    private static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("لا توجد كتب.");
        } else {
            for (Book b : books) {
                b.displayInfo();
            }
        }
    }

    private static void addBorrower() {
        System.out.print("اسم المستعير: ");
        String name = scanner.nextLine();
        System.out.print("رقم الهوية: ");
        String id = scanner.nextLine();

        borrowers.add(new Borrower(name, id));
        System.out.println("تمت إضافة المستعير.");
    }

    private static void borrowBook() {
        System.out.print("أدخل ISBN الكتاب: ");
        String isbn = scanner.nextLine();

        Book selectedBook = null;
        for (Book b : books) {
            if (b.getIsbn().equals(isbn) && !b.isBorrowed()) {
                selectedBook = b;
                break;
            }
        }

        if (selectedBook == null) {
            System.out.println("الكتاب غير متوفر أو غير موجود.");
            return;
        }

        System.out.print("رقم هوية المستعير: ");
        String id = scanner.nextLine();

        Borrower borrower = null;
        for (Borrower br : borrowers) {
            if (br.getId().equals(id)) {
                borrower = br;
                break;
            }
        }

        if (borrower == null) {
            System.out.println("المستعير غير موجود.");
            return;
        }

        LocalDate now = LocalDate.now();
        LocalDate returnDate = now.plusDays(14); // إرجاع بعد أسبوعين

        selectedBook.borrowBook();
        borrowings.add(new BorrowingProcess(selectedBook, borrower, now, returnDate));
        System.out.println("تمت عملية الاستعارة.");
    }

    private static void showBorrowings() {
        if (borrowings.isEmpty()) {
            System.out.println("لا توجد عمليات استعارة.");
        } else {
            for (BorrowingProcess bp : borrowings) {
                bp.displayInfo();
            }
        }
    }
}
