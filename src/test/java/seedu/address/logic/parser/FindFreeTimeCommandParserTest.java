package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteStudentModuleTimingCommand;
import seedu.address.logic.commands.FindFreeTimeCommand;
import seedu.address.model.module.Day;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTiming;
import seedu.address.model.module.Timing;
import seedu.address.model.student.IsFreePredicate;

import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

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

//    @Test
//    public void parse_repeatedNonTagValue_failure() {
//        String validExpectedStudentString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND;
//
//        // multiple names
//        assertParseFailure(parser, NAME_DESC_AMY + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // multiple phones
//        assertParseFailure(parser, PHONE_DESC_AMY + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // multiple emails
//        assertParseFailure(parser, EMAIL_DESC_AMY + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // multiple addresses
//        assertParseFailure(parser, ADDRESS_DESC_AMY + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//
//        // multiple fields repeated
//        assertParseFailure(parser,
//                validExpectedStudentString + PHONE_DESC_AMY + EMAIL_DESC_AMY + NAME_DESC_AMY + ADDRESS_DESC_AMY
//                        + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_ADDRESS, PREFIX_EMAIL, PREFIX_PHONE));
//
//        // invalid value followed by valid value
//
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, INVALID_EMAIL_DESC + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, INVALID_PHONE_DESC + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // invalid address
//        assertParseFailure(parser, INVALID_ADDRESS_DESC + validExpectedStudentString,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//
//        // valid value followed by invalid value
//
//        // invalid name
//        assertParseFailure(parser, validExpectedStudentString + INVALID_NAME_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));
//
//        // invalid email
//        assertParseFailure(parser, validExpectedStudentString + INVALID_EMAIL_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_EMAIL));
//
//        // invalid phone
//        assertParseFailure(parser, validExpectedStudentString + INVALID_PHONE_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));
//
//        // invalid address
//        assertParseFailure(parser, validExpectedStudentString + INVALID_ADDRESS_DESC,
//                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ADDRESS));
//    }
//
//    @Test
//    public void parse_optionalFieldsMissing_success() {
//        // zero tags
//        Student expectedStudent = new StudentBuilder(AMY).withTags().build();
//        assertParseSuccess(parser, NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY + ADDRESS_DESC_AMY,
//                new AddCommand(expectedStudent));
//    }
//
//    @Test
//    public void parse_compulsoryFieldMissing_failure() {
//        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);
//
//        // missing name prefix
//        assertParseFailure(parser, VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
//                expectedMessage);
//
//        // missing phone prefix
//        assertParseFailure(parser, NAME_DESC_BOB + VALID_PHONE_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB,
//                expectedMessage);
//
//        // Commented out these 2 tests as email and address is now optional
//        // missing email prefix
////        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + VALID_EMAIL_BOB + ADDRESS_DESC_BOB,
////                expectedMessage);
//
//        // missing address prefix
////        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + VALID_ADDRESS_BOB,
////                expectedMessage);
//
//        // all prefixes missing
//        assertParseFailure(parser, VALID_NAME_BOB + VALID_PHONE_BOB + VALID_EMAIL_BOB + VALID_ADDRESS_BOB,
//                expectedMessage);
//    }
//
//    @Test
//    public void parse_invalidValue_failure() {
//        // invalid name
//        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);
//
//        // invalid phone
//        assertParseFailure(parser, NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Phone.MESSAGE_CONSTRAINTS);
//
//        // invalid email
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Email.MESSAGE_CONSTRAINTS);
//
//        // invalid address
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC
//                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Address.MESSAGE_CONSTRAINTS);
//
//        // invalid tag
//        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB
//                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);
//
//        // two invalid values, only first invalid value reported
//        assertParseFailure(parser, INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC,
//                Name.MESSAGE_CONSTRAINTS);
//
//        // non-empty preamble
//        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
//                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
//                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
//    }
}
