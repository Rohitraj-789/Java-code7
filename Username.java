public class Username {
    private static String currentUsername;

    public static String getUsername() {
        return currentUsername;
    }

    public static void setUsername(String username) {
        currentUsername = username;
    }
}
