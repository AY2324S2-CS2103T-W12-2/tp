package seedu.address.model.module;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Module in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class ModuleTiming {
    public static final String MESSAGE_CONSTRAINTS = "End time should be larger than Start time";
    private final ModuleCode moduleCode;
    private final Day day;
    private final Timing startTime;
    private final Timing endTime;

    /**
     * The constructor for a Module. This requires that all fields cannot be null.
     */
    public ModuleTiming(
            ModuleCode moduleCode,
            Day day,
            Timing startTime,
            Timing endTime) {
        requireAllNonNull(moduleCode, day, startTime, endTime);
        this.moduleCode = moduleCode;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public ModuleCode getModuleCode() {
        return moduleCode;
    }

    public Day getDay() {
        return day;
    }

    public Timing getStartTime() {
        return startTime;
    }

    public Timing getEndTime() {
        return endTime;
    }

    /**
     * Returns true if the start time < end time.
     */
    public static boolean isValidModuleTiming(Timing startTime, Timing endTime) {
        return startTime.compareTo(endTime) < 0;
    }

    /**
     * Returns true if timing clashes.
     */
    public boolean doesModuleTimingClash(ModuleTiming otherTiming) {
        if (!day.equals(otherTiming.day)) {
            return false;
        }

        // check if there is overlap in timings
        return endTime.compareTo(otherTiming.startTime) > 0 && startTime.compareTo(otherTiming.endTime) < 0;
    }

    @Override
    public String toString() {
        return String.format("%s %s-%s", day.toString(), startTime.toString(), endTime.toString());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleTiming // instanceof handles nulls
                && moduleCode.equals(((ModuleTiming) other).moduleCode)
                && day.equals(((ModuleTiming) other).day)
                && startTime.equals(((ModuleTiming) other).startTime)
                && endTime.equals(((ModuleTiming) other).endTime)); // state check
    }
}
