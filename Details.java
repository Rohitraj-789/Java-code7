import java.sql.*;


public class Details{
    public String getName(String username) {
        String sql = "SELECT name FROM customers WHERE username = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (SQLException e) {
            // Handle exception appropriately, e.g., log it, or rethrow.
        }
        return null; // Return null if no record is found.
    }

    public String getrPhone(String username) {
        String sql = "SELECT phone FROM customers WHERE username = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("phone");
            }
        } catch (SQLException e) {
            // Handle exception (log it, rethrow it, etc.)
        }
        return null;  // Return null if no record is found.
    }

    public String getEmail(String username) {
        String sql = "SELECT email FROM customers WHERE username = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
        } catch (SQLException e) {
            // Handle exception appropriately.
        }
        return null;  // Return null if no email is found.
    }

    public String getAccountNumber(String username) {
        String sql = "SELECT account_number FROM customers WHERE username = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("account_number");
            }
        } catch (SQLException e) {
            // Handle exception appropriately.
        }
        return null;  // Return null if no account number is found.
    }

    public double getBalance(String username) {
        String sql = "SELECT balance FROM customers WHERE username = ? LIMIT 1";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            // Handle exception appropriately.
        }
        return 0.0;  // Return a default value (or consider throwing an exception) if no balance is found.
    }




//    public static void main(String[] args) {
//        Details obj = new Details();
//        System.out.println(obj.getUsername("Abhay@1931"));
//        System.out.println(obj.getrPhone("Abhay@1931"));
//        System.out.println(obj.getEmail("Abhay@1931"));
//        System.out.println(obj.getAccountNumber("Abhay@1931"));
//        System.out.println(obj.getBalance("Abhay@1931"));
//    }

}