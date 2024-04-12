package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_MON;
import static seedu.address.logic.commands.CommandTestUtil.END_TIMING_DESC_1600;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_TIMING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_TIMING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.START_TIMING_DESC_0800;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMING_0800;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMING_1600;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindFreeTimeCommand;
import seedu.address.model.module.Day;
import seedu.address.model.module.Timing;
import seedu.address.model.student.IsFreePredicate;

public class FindFreeTimeCommandParserTest {
    private FindFreeTimeCommandParser parser = new FindFreeTimeCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = DAY_DESC_MON
                + START_TIMING_DESC_0800
                + END_TIMING_DESC_1600;


        IsFreePredicate expectedIsFreePredicate = new IsFreePredicate(
            new Timing(VALID_TIMING_0800),
            new Timing(VALID_TIMING_1600),
            new Day(VALID_DAY_MON)
        );
        FindFreeTimeCommand expectedCommand = new FindFreeTimeCommand(expectedIsFreePredicate);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindFreeTimeCommand.MESSAGE_USAGE);

        // missing day prefix
        assertParseFailure(parser, VALID_DAY_MON + START_TIMING_DESC_0800 + END_TIMING_DESC_1600,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid day
        assertParseFailure(parser, INVALID_DAY_DESC + START_TIMING_DESC_0800 + END_TIMING_DESC_1600,
                Day.MESSAGE_CONSTRAINTS);

        // invalid start time
        assertParseFailure(parser, DAY_DESC_MON + INVALID_START_TIMING_DESC + END_TIMING_DESC_1600,
                Timing.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, DAY_DESC_MON + START_TIMING_DESC_0800 + INVALID_END_TIMING_DESC,
                Timing.MESSAGE_CONSTRAINTS);
    }
}
