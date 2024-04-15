package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DayTest {
    @Test
    public void isValidDay_validDay_returnsTrue() {
        assertTrue(Day.isValidDay("Mon"));
    }

    @Test
    public void isValidDay_longFormDay_returnsFalse() {
        assertTrue(!Day.isValidDay("Monday"));
    }

    @Test
    public void toStringMethod() {
        Day day = new Day("Mon");
        assertTrue(day.toString().equals("Mon"));
    }

    @Test
    public void equals_sameDays_returnsTrue() {
        Day day = new Day("Mon");
        Day dayCopy = new Day("Mon");
        assertTrue(day.equals(dayCopy));
    }

    @Test
    public void equals_differentDays_returnsFalse() {
        Day day = new Day("Mon");
        Day dayCopy = new Day("Tue");
        assertTrue(!day.equals(dayCopy));
    }
}
