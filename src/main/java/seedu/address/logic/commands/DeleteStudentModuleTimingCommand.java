package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.student.Student;

/**
 * Represents a command to delete a module timing from the module of a student in the address book.
 */
public class DeleteStudentModuleTimingCommand extends Command {

    public static final String COMMAND_WORD = "delete_timing";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a module timing from the module of a student "
            + "in the address book. "
            + "Parameters: "
            + PREFIX_STUDENT_ID + "STUDENT ID"
            + PREFIX_MODULE_CODE + "MODULE CODE"
            + PREFIX_DAY + "DAY"
            + PREFIX_START_TIME + "START TIME"
            + PREFIX_END_TIME + "END TIME";

    public static final String MESSAGE_SUCCESS = "Deleted module timing %1$s from student: %2$s";
    public static final String MESSAGE_MODULE_NOT_FOUND =
            "This module does not exist in the student's contact in address book";
    public static final String MESSAGE_MODULE_TIMING_DOES_NOT_EXIST =
            "This module timing does not exist in the student's contact in address book";

    private final Index index;
    private final ModuleCode moduleCode;
    private final ModuleTiming moduleTiming;

    /**
     * Represents a command to delete a student's module timing from the address book.
     * This command removes the specified module timing for a student identified by the given index and module code.
     */
    public DeleteStudentModuleTimingCommand(Index index, ModuleCode moduleCode, ModuleTiming moduleTiming) {
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
        if (!model.doesStudentModuleTimingExist(studentToModify, moduleTiming)) {
            throw new CommandException(MESSAGE_MODULE_TIMING_DOES_NOT_EXIST);
        }

        model.deleteModuleTimingFromStudent(moduleTiming, studentToModify);

        return new CommandResult(
                String.format(
                        MESSAGE_SUCCESS,
                        Messages.format(moduleTiming),
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
        if (!(other instanceof DeleteStudentModuleTimingCommand)) {
            return false;
        }

        DeleteStudentModuleTimingCommand otherDeleteCommand = (DeleteStudentModuleTimingCommand) other;
        return index.equals(otherDeleteCommand.index) && moduleCode.equals(otherDeleteCommand.moduleCode)
                && moduleTiming.equals(otherDeleteCommand.moduleTiming);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("student index to delete module from", index)
                // TODO: check if this is needed
                // .add("module to delete to student", moduleCode)
                .add("module timing to delete from module", moduleTiming)
                .toString();
    }
}

