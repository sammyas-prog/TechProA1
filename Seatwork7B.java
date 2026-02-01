class PasswordException extends Exception {
    public PasswordException(String message) {
        super(message);
    }
}

public class file {
    static void checkPassword(String password) throws PasswordException {
        if (password.length() < 8) {
            throw new PasswordException("Password must be at least 8 characters long.");
        } else {
            System.out.println("Password accepted!");
        }
    }

    public static void main(String[] args) {
        try {
            checkPassword("abc123");
        } catch (PasswordException e) {
            System.out.println("User-Defined Exception: " + e.getMessage());
        }
    }
}
