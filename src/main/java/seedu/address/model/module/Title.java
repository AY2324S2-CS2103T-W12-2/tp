package seedu.address.model.module;

/**
 * Represents a Module's title in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTitle(String)}
 */
public class Title {
    public static final String MESSAGE_CONSTRAINTS = "";
    private final String title;

    public Title(String title) {
        this.title = title;
    }

    //    TODO(yadunut): Validate title
    public static boolean isValidTitle(String test) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public String getTitle() {
        return title;
    }
}
