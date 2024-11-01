public class Exceptions extends Exception {

    public class NotIntegerException extends Exception {
        public NotIntegerException(String message) {
            super(message);
        }
    }

    public class NotStringException extends Exception {
        public NotStringException(String message) {
            super(message);
        }
    }

    public class NotDoubleException extends Exception {
        public NotDoubleException(String message) {
            super(message);
        }
    }
    public class NotInstanceOfMenuException extends Exception {
        public NotInstanceOfMenuException(String message) {
            super(message);
        }
    }
    public class EmptyInputException extends Exception {
        public EmptyInputException(String message) {
            super(message);
        }
    }


}
