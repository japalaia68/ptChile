package pt.exception;

public class NonexistentFolderException extends Exception {
    public NonexistentFolderException(String s) {
        super(s);
    }
}
