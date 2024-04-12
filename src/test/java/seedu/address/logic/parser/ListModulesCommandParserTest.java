package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.ListModulesCommand;

public class ListModulesCommandParserTest {
    private ListModulesCommandParser parser = new ListModulesCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = MODULE_CODE_DESC_CS2103T;

        ListModulesCommand expectedCommand = new ListModulesCommand(VALID_MODULE_CODE_CS2103T);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListModulesCommand.MESSAGE_USAGE);

        // missing modulecode prefix
        assertParseFailure(parser, "",
                expectedMessage);
    }
}
