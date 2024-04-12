package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.student.IsFreePredicate;

/**
 * Finds and lists all students in address book who is free in the specified timings.
 */
public class FindFreeTimeCommand extends Command {

    public static final String COMMAND_WORD = "find_free_time";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all students who are free in the time–range specified"
            + "and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_DAY + "Wed" + " " +  PREFIX_START_TIME + "0230"
            + " " + PREFIX_END_TIME + "0330";

    private final IsFreePredicate predicate;

    public FindFreeTimeCommand(IsFreePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FindFreeTimeCommand)) {
            return false;
        }

        FindFreeTimeCommand otherFindCommand = (FindFreeTimeCommand) other;
        return predicate.equals(otherFindCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}
