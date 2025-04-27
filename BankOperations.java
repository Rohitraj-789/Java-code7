import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;




public class BankOperations {

    public boolean validateCredentials(String username, String password) {
        String sql = "SELECT COUNT(*) FROM customers WHERE username = ? AND password = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // true if at least one matching row
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createAccount(String name, String username, String password, String phone, String email) {
        String accountNumber = generateAccountNumber();
        LocalDateTime createdAt = LocalDateTime.now();
        // FIX: Updated INSERT query columns to match the corrected database schema.
        String sql = "INSERT INTO customers (name, username, password, phone, email, balance, account_number, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, username);  // Using "username" as per DB schema.
            pstmt.setString(3, password);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.setDouble(6, 0.00);      // Default balance
            pstmt.setString(7, accountNumber);
            pstmt.setTimestamp(8, Timestamp.valueOf(createdAt));
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deposit(String accountNumber, double amount) {
        // FIX: Updated WHERE clause column to "account_number" (matches corrected DB schema)
        String sql = "UPDATE customers SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean withdraw(String accountNumber, double amount) {
        // SQL statement subtracts the withdrawal amount from the current balance
        String sql = "UPDATE customers SET balance = balance - ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, amount);
            pstmt.setString(2, accountNumber);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean transfer(String fromAccount, String toAccount, double amount) {
        // FIX: Updated column name in queries to "account_number" per DB schema.
        String withdrawSql = "UPDATE customers SET balance = balance - ? WHERE account_number = ?";
        String depositSql = "UPDATE customers SET balance = balance + ? WHERE account_number = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement withdrawStmt = conn.prepareStatement(withdrawSql);
                 PreparedStatement depositStmt = conn.prepareStatement(depositSql)) {

                withdrawStmt.setDouble(1, amount);
                withdrawStmt.setString(2, fromAccount);
                if (withdrawStmt.executeUpdate() <= 0) {
                    conn.rollback();
                    return false;
                }

                depositStmt.setDouble(1, amount);
                depositStmt.setString(2, toAccount);
                if (depositStmt.executeUpdate() <= 0) {
                    conn.rollback();
                    return false;
                }

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("account_number"),
                        rs.getDouble("balance"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("username")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    private String generateAccountNumber() {
        // FIX (Optional): Consider adding random digits to reduce duplicates.
        return "ACC" + System.currentTimeMillis();
    }
}
