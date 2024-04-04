package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.ModuleCode;

public class ModuleSearchCommand extends Command {
    public static final String COMMAND_WORD = "module_search";
    public static final String MESSAGE_USAGE = "Searches for students with the specified module code. "
            + "Parameters: MODULE_CODE\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_MODULE_CODE + "CS2103T";
    private final ModuleCode moduleCode;

    public ModuleSearchCommand(ModuleCode moduleCode) {
        requireNonNull(moduleCode);
        this.moduleCode = moduleCode;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(student -> student.hasModule(moduleCode));
        StringBuilder sb = new StringBuilder();
        sb.append("List of students with module: ").append(moduleCode).append("\n");
        sb.append(String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
        return new CommandResult(sb.toString());
    }
}
