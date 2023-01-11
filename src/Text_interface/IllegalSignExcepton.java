package Text_interface;

public class IllegalSignExcepton extends Exception {
    private String message;

    public IllegalSignExcepton(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
