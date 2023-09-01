package bg.softuni.springinitdemo.exceptions;

public class EntityMissingException extends RuntimeException {
    public EntityMissingException(String msg) {
        super(msg);
    }
}
