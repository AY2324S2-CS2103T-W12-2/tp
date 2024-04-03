package seedu.address.model.student;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.Timing;

/**
 * Tests that a {@code Student} is free between a given timing
 */
public class IsFreePredicate implements Predicate<Student> {
    private final Timing startTime;
    private final Timing endTime;

    /**
     * Constructor to create a predicate
     * @param startTime starting time of range
     * @param endTime ending time of range
     */
    public IsFreePredicate(Timing startTime, Timing endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean test(Student student) {
        return student.getModuleTimings()
                .stream()
                .noneMatch(
                        moduleTiming ->
                                startTime.compareTo(moduleTiming.getEndTime()) == -1
                                && endTime.compareTo(moduleTiming.getStartTime()) == 1
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
        return startTime.equals(otherIsFreePredicate.startTime) && endTime.equals(otherIsFreePredicate.endTime);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("startTime", startTime)
                .add("endTime", endTime)
                .toString();
    }
}
