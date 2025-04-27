

import java.time.LocalDateTime;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String user_name;
    private String account_number;
    private double balance;
    private String phone;
    private LocalDateTime created_at;

    public Customer(int id, String name, String account_number, double balance, String phone, String email, String username) {
        this.id = id;
        this.name = name;
        this.account_number = account_number;
        this.balance = balance;
        this.phone = phone;
        this.email = email;
        this.user_name = username;
        this.created_at = LocalDateTime.now(); // Set to current time upon object creation
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getAccountNumber() { return account_number; }
    public double getBalance() { return balance; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getUsername() { return user_name; }
    public LocalDateTime getCreatedAt() { return created_at; }

    // Setter for balance if you need to update it
    public void setBalance(double balance) {
        this.balance = balance;
    }
}

