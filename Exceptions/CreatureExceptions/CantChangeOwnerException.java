package Exceptions.CreatureExceptions;

public class CantChangeOwnerException extends CreatureException {
    public CantChangeOwnerException(String message) {
        super(message);
    }
}
