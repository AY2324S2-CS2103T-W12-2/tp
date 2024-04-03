package seedu.address.logic.parser;

import seedu.address.logic.commands.FindFreeTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.module.Timing;
import seedu.address.model.student.IsFreePredicate;

import java.util.stream.Stream;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_TIME;

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
                        args, PREFIX_START_TIME, PREFIX_END_TIME);
        if (!arePrefixesPresent(
                argMultimap, PREFIX_START_TIME, PREFIX_END_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_TIME, PREFIX_END_TIME);
        Timing startTime = ParserUtil.parseTiming(argMultimap.getValue(PREFIX_START_TIME).get());
        Timing endTime = ParserUtil.parseTiming(argMultimap.getValue(PREFIX_END_TIME).get());

        return new FindFreeTimeCommand(new IsFreePredicate(startTime, endTime));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
