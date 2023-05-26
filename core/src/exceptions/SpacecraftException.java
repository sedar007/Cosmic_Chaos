package exceptions;


public class SpacecraftException extends Exception {
    public SpacecraftException() {
        super("No spacecraft is empty");
    }
}
