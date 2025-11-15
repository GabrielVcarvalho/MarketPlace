package repository.exceptions;

public class NullEntityForRepository extends RuntimeException {
    public NullEntityForRepository(String message) {
        super(message);
    }
}
