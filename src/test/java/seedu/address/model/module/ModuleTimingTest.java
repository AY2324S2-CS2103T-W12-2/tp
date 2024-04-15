package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleTimingTest {
    @Test
    public void equals_returnsTrue() {
        ModuleTiming moduleTiming = new ModuleTiming(
            new ModuleCode("CS2103T"),
            new Day("Mon"),
            new Timing("0800"),
            new Timing("1600")
        );
        ModuleTiming moduleTimingCopy = new ModuleTiming(
            new ModuleCode("CS2103T"),
            new Day("Mon"),
            new Timing("0800"),
            new Timing("1600")
        );
        assertTrue(moduleTiming.equals(moduleTimingCopy));
    }

    @Test
    public void equals_returnsFalse() {
        ModuleTiming moduleTiming = new ModuleTiming(
            new ModuleCode("CS2103T"),
            new Day("Mon"),
            new Timing("0800"),
            new Timing("1600")
        );
        ModuleTiming moduleTimingCopy = new ModuleTiming(
            new ModuleCode("CS2103T"),
            new Day("Mon"),
            new Timing("0800"),
            new Timing("1700")
        );
        assertTrue(!moduleTiming.equals(moduleTimingCopy));
    }
}
