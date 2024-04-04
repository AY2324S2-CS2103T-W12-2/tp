package seedu.address.model.student;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.Day;
import seedu.address.model.module.Timing;

/**
 * Tests that a {@code Student} is free between a given timing
 */
public class IsFreePredicate implements Predicate<Student> {
    private final Timing startTime;
    private final Timing endTime;

    private final Day day;

    /**
     * Constructor to create a predicate
     * @param startTime starting time of range
     * @param endTime ending time of range
     * @param day day to check
     */
    public IsFreePredicate(Timing startTime, Timing endTime, Day day) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }

    @Override
    public boolean test(Student student) {
        return student.getModuleTimings()
                .stream()
                .filter(
                        moduleTiming -> moduleTiming.getDay().equals(day)
                )
                .noneMatch(
                        moduleTiming ->
                                startTime.compareTo(moduleTiming.getEndTime()) < 0
                                && endTime.compareTo(moduleTiming.getStartTime()) > 0
                );
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof IsFreePredicate)) {
            return false;
        }

        IsFreePredicate otherIsFreePredicate = (IsFreePredicate) other;
        return startTime.equals(otherIsFreePredicate.startTime)
                && endTime.equals(otherIsFreePredicate.endTime)
                && day.equals(otherIsFreePredicate.day);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .add("day", day)
                .toString();
    }
}
