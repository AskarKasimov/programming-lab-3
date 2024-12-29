package customexception.creature;

public class CreatureCantChangeOwnerException extends CreatureException {
    public CreatureCantChangeOwnerException(String message) {
        super(message);
    }
}
