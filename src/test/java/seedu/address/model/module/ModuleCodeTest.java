package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {
    @Test
    public void isValidModuleCode_validModuleCode_returnsTrue() {
        assertTrue(ModuleCode.isValidCode("CS1101S"));
    }

    @Test
    public void isValidModuleCode_lowerCaseModuleCode_returnsTrue() {
        assertTrue(ModuleCode.isValidCode("cs1101s"));
    }

    @Test
    public void equals_sameModules_returnsTrue() {
        assertTrue(new ModuleCode("CS1101S").equals(new ModuleCode("CS1101S")));
    }

    @Test
    public void equals_differentModules_returnsFalse() {
        assertTrue(!(new ModuleCode("CS1101S").equals(new ModuleCode("CS2040S"))));
    }
}
