package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.stream.Stream;

import seedu.address.logic.commands.FindFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Day;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.module.Timing;
import seedu.address.model.student.IsFreePredicate;

/**
 * Parses input arguments and creates a new FindFreeTimeCommand object
 */
public class FindFreeTimeCommandParser implements Parser<FindFreeTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindFreeTimeCommand
     * and returns an FindFreeTimeCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindFreeTimeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(
                        args, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DAY);
        if (!arePrefixesPresent(
                argMultimap, PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DAY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_TIME, PREFIX_END_TIME, PREFIX_DAY);
        Timing startTime = ParserUtil.parseTiming(argMultimap.getValue(PREFIX_START_TIME).get());
        Timing endTime = ParserUtil.parseTiming(argMultimap.getValue(PREFIX_END_TIME).get());
        Day day = ParserUtil.parseDay((argMultimap.getValue(PREFIX_DAY)).get());

        IsFreePredicate isFreePredicate = ParserUtil.parseIsFreePredicate(day, startTime, endTime);

        return new FindFreeTimeCommand(isFreePredicate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
