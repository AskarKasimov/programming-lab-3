package customexception.creature;

public class RejectingToWorkException extends CreatureException {
    public RejectingToWorkException(String message) {
        super(message);
    }
}
