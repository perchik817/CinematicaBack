package megalab.cinematica.exceptions;

public class UnsavedDataException extends RuntimeException {
    public UnsavedDataException(String mess) {
        super(mess);
    }
}
