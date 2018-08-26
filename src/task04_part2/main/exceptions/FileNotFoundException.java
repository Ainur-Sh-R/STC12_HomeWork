package task04_part2.main.exceptions;

public class FileNotFoundException extends RuntimeException{

    public FileNotFoundException(String message) {
        super(message);
    }
}
