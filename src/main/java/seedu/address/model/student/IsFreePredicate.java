package seedu.address.model.student;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.module.Timing;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student} is free between a given timing
 */
public class IsFreePredicate implements Predicate<Student> {
    private final Timing startTime, endTime;

    public IsFreePredicate(Timing startTime, Timing endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean test(Student student) {
        return !student.getModuleTimings()
                .stream()
                .noneMatch(
                        moduleTiming -> startTime.compareTo(moduleTiming.getEndTime()) == -1 && endTime.compareTo(moduleTiming.getStartTime()) == 1
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
