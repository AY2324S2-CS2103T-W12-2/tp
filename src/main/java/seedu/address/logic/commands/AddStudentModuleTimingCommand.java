package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.student.Student;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;

/**
 * Adds a student to the address book.
 */
public class AddStudentModuleTimingCommand extends Command {

    public static final String COMMAND_WORD = "add_timing";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a module timing to the module of a student "
            + "in the address book. "
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID"
            + PREFIX_MODULE_CODE + "MODULE CODE"
            + PREFIX_DAY + "DAY"
            + PREFIX_START_TIME + "START TIME"
            + PREFIX_END_TIME + "END TIME";

    public static final String MESSAGE_SUCCESS = "New module timing %1$s added to student: %2$s";
    public static final String MESSAGE_MODULE_NOT_FOUND =
            "This module does not exist in the student's contact in address book";

    private final Index index;
    private final ModuleCode moduleCode;
    private final ModuleTiming moduleTiming;

    /**
     * Creates an AddStudentModuleCommand to add the specified {@code ModuleTiming} to {@code Student}
     */
    public AddStudentModuleTimingCommand(Index index, ModuleCode moduleCode, ModuleTiming moduleTiming) {
        requireNonNull(index);
        requireNonNull(moduleCode);
        requireNonNull(moduleTiming);
        this.index = index;
        this.moduleCode = moduleCode;
        this.moduleTiming = moduleTiming;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToModify = lastShownList.get(index.getZeroBased());

        if (!model.doesStudentHaveModule(studentToModify, moduleCode)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        // Some timing validation needs to go here (no duplicate timings for this module or any module ?)

//        model.addModuleToStudent(moduleCode, studentToModify);

        return new CommandResult(
                String.format(
                        MESSAGE_SUCCESS,
                        moduleCode.getCode(),
                        Messages.format(studentToModify)
                )
        );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStudentModuleTimingCommand)) {
            return false;
        }

        AddStudentModuleTimingCommand otherAddCommand = (AddStudentModuleTimingCommand) other;
        return index.equals(otherAddCommand.index) && moduleCode.equals(otherAddCommand.moduleCode)
                && moduleTiming.equals(otherAddCommand.moduleTiming);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student index to add module to", index)
                .add("module to add to student", moduleCode)
                .toString();
    }
}

