package osoba;

public class EmailException extends Exception {
    public EmailException(String message) {
        super(message);
    }

    public static void ValidateEmail(String email) throws EmailException {
        if(!email.contains("@")) {
            throw new EmailException("Mail musi zawierać znak @");
        }
    }
}
