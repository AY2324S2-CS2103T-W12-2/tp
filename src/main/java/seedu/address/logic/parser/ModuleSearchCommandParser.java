package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;

import java.util.stream.Stream;

import seedu.address.logic.commands.module.ModuleSearchCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.ModuleCode;

/**
 * Parses input arguments and creates a new ModuleSearchCommand object
 */
public class ModuleSearchCommandParser implements Parser<ModuleSearchCommand> {
    @Override
    public ModuleSearchCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_MODULE_CODE);
        if (!arePrefixesPresent(argumentMultimap, PREFIX_MODULE_CODE) || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleSearchCommand.MESSAGE_USAGE));
        }
        argumentMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MODULE_CODE);
        ModuleCode moduleCode = ParserUtil.parseModule(argumentMultimap.getValue(PREFIX_MODULE_CODE).get());
        return new ModuleSearchCommand(moduleCode);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
