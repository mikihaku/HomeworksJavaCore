public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(String message, Throwable e) {
        super("Элемент массива невозможно представить как число по адресу " + message);
    }
}
