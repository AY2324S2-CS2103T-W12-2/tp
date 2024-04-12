package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_MON;
import static seedu.address.logic.commands.CommandTestUtil.END_TIMING_DESC_1600;
import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.START_TIMING_DESC_0800;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_MON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMING_0800;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIMING_1600;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStudentModuleTimingCommand;
import seedu.address.model.module.Day;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.module.Timing;

public class DeleteStudentModuleTimingCommandParserTest {
    private DeleteStudentModuleTimingCommandParser parser = new DeleteStudentModuleTimingCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = " " + PREFIX_STUDENT_ID + targetIndex.getOneBased()
                + MODULE_CODE_DESC_CS2103T
                + DAY_DESC_MON
                + START_TIMING_DESC_0800
                + END_TIMING_DESC_1600;


        ModuleTiming expectedModuleTiming = new ModuleTiming(
            new ModuleCode(VALID_MODULE_CODE_CS2103T),
            new Day(VALID_DAY_MON),
            new Timing(VALID_TIMING_0800),
            new Timing(VALID_TIMING_1600)
        );
        DeleteStudentModuleTimingCommand expectedCommand = new DeleteStudentModuleTimingCommand(
                targetIndex,
                new ModuleCode(VALID_MODULE_CODE_CS2103T),
                expectedModuleTiming);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteStudentModuleTimingCommand.MESSAGE_USAGE);

        // missing index prefix
        assertParseFailure(parser,
                " " + INDEX_FIRST_STUDENT.getOneBased() + MODULE_CODE_DESC_CS2103T
                        + START_TIMING_DESC_0800 + END_TIMING_DESC_1600,
                expectedMessage);
    }
}
