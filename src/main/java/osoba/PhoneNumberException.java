package osoba;

public class PhoneNumberException extends RuntimeException {
    public PhoneNumberException(String message) {
        super(message);
    }

    public static void ValidateNumber(String phoneNumber) throws PhoneNumberException {
        if(phoneNumber.length() != 9){
            throw new PhoneNumberException("Niepoprawny numer telefonu");
        }
    }
}
