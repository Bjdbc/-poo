public class Borrower {
    private String name;
    private String id;

    public Borrower(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void displayInfo() {
        System.out.println("اسم المستعير: " + name);
        System.out.println("رقم الهوية: " + id);
    }
}
