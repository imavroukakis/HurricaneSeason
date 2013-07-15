package hurricaneParser;


public class IllegalDataException extends Exception {

    public IllegalDataException() {
    }

    public IllegalDataException(String s) {
        super(s);
    }

    public IllegalDataException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public IllegalDataException(Throwable throwable) {
        super(throwable);
    }
}
