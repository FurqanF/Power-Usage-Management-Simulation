public class SmartApplianceException extends Exception{
    public SmartApplianceException(String message) {
        super("Invalid Reduce Percentage(" + message + ")");
    }
}
