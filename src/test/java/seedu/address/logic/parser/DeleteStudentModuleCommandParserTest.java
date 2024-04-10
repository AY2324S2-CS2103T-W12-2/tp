package seedu.address.logic.parser;

import static seedu.address.logic.commands.CommandTestUtil.MODULE_CODE_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_CODE_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStudentModuleCommand;
import seedu.address.model.module.ModuleCode;

public class DeleteStudentModuleCommandParserTest {
    private DeleteStudentModuleCommandParser parser = new DeleteStudentModuleCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = " " + PREFIX_STUDENT_ID + targetIndex.getOneBased() + MODULE_CODE_DESC_CS2103T;

        DeleteStudentModuleCommand expectedCommand = new DeleteStudentModuleCommand(
                targetIndex, new ModuleCode(VALID_MODULE_CODE_CS2103T));

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
