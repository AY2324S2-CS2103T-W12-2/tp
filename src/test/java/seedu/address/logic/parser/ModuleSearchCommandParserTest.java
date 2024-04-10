package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_CODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.module.ModuleSearchCommand;
import seedu.address.model.module.ModuleCode;

public class ModuleSearchCommandParserTest {
    private ModuleSearchCommandParser parser = new ModuleSearchCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        String userInput = MODULE_CODE_DESC_CS2103T;

        ModuleSearchCommand expectedCommand = new ModuleSearchCommand(new ModuleCode(VALID_MODULE_CODE_CS2103T));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ModuleSearchCommand.MESSAGE_USAGE);

        // missing modulecode prefix
        assertParseFailure(parser, "",
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        String expectedMessage = String.format(ModuleCode.MESSAGE_CONSTRAINTS, INVALID_MODULE_CODE);

        // invalid modulecode
        assertParseFailure(parser, INVALID_MODULE_CODE_DESC, expectedMessage);
    }
}
