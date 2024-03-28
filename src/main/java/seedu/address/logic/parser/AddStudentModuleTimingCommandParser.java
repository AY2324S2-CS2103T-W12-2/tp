package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddStudentModuleTimingCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Day;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.module.Timing;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddStudentModuleTimingCommandParser implements Parser<AddStudentModuleTimingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentModuleTimingCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_STUDENT_ID, PREFIX_MODULE_CODE, PREFIX_DAY, PREFIX_START_TIME, PREFIX_END_TIME);
        if (!arePrefixesPresent(
                argMultimap, PREFIX_STUDENT_ID, PREFIX_MODULE_CODE, PREFIX_DAY, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentModuleTimingCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_STUDENT_ID, PREFIX_MODULE_CODE);
        Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_STUDENT_ID).get());
        ModuleCode moduleCode = ParserUtil.parseModule(argMultimap.getValue(PREFIX_MODULE_CODE).get());
        Day day = ParserUtil.parseDay(argMultimap.getValue(PREFIX_DAY).get());
        Timing startTime = ParserUtil.parseTiming(argMultimap.getValue(PREFIX_START_TIME).get());
        Timing endTime = ParserUtil.parseTiming(argMultimap.getValue(PREFIX_END_TIME).get());

        ModuleTiming moduleTiming = new ModuleTiming(moduleCode, day, startTime, endTime);

        return new AddStudentModuleTimingCommand(index, moduleCode, moduleTiming);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
