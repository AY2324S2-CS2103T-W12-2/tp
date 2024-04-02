package seedu.address.logic.commands.module;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.List;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
/** Lists all modules in the address book. */
public class ListModulesCommand extends Command {
    public static final String COMMAND_WORD = "list_modules";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": List all modules in the address book."
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE";

    private final String modulePrefix;

    /** Creates a ListModulesCommand to list modules with the specified module code prefix {@code modulePrefix}. */
    public ListModulesCommand(String modulePrefix) {
        requireNonNull(modulePrefix);
        this.modulePrefix = modulePrefix;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> modules = model.getModuleMap().getModulesByPrefix(this.modulePrefix);
        if (modules.size() == 0) {
            return new CommandResult("No modules found with prefix " + this.modulePrefix);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("List of modules with prefix: ").append(this.modulePrefix).append("\n");
        for (Module m : modules) {
            sb.append(m.getModuleCode().getCode()).append(" : ");
            sb.append(StringUtil.truncate(m.getDescription().getValue(), 80)).append("\n");
        }
        return new CommandResult(sb.toString());
    }
}
