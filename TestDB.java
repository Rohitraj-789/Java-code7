public class TestDB {
    public static void main(String[] args) {
        if (DatabaseConnection.getConnection() != null) {
            System.out.println("✅ Database Connection Successful!");
        } else {
            System.out.println("❌ Failed to Connect.");
        }
    }
}