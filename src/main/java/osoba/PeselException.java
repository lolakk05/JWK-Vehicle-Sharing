package osoba;

public class PeselException extends Exception {
    public PeselException(String message) {
        super(message);
    }

    public static void ValidatePesel(String pesel) throws PeselException {
        if(pesel.length() != 11 || pesel == null) {
            throw new PeselException("Niepoprawna długość numeru pesel");
        }
        int[] wages = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for(int i = 0; i < 10; i++) {
            int number = Character.getNumericValue(pesel.charAt(i));
            sum += number * wages[i];
        }
        double diff = 10 - (sum % 10);
        if(diff == 10) {
            diff = 0;
        }
        int lastChar = Character.getNumericValue(pesel.charAt(10));
        if(lastChar != diff) {
            throw new PeselException("Pesel jest niepoprawny");
        }
    }
}
