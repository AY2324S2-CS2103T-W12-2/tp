package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void getModuleCodeMethod() {
        Module module = new Module(
            new ModuleCode("CS1101S"),
            new Title("Programming Methodology"),
            new Description("Learn how to CS")
        );
        assertTrue(module.getModuleCode().equals(new ModuleCode("CS1101S")));
    }

    @Test
    public void getTitleMethod() {
        Module module = new Module(
            new ModuleCode("CS1101S"),
            new Title("Programming Methodology"),
            new Description("Learn how to CS")
        );
        assertTrue(module.getTitle().getTitle().equals(new Title("Programming Methodology").getTitle()));
    }

    @Test
    public void getDescriptionMethod() {
        Module module = new Module(
            new ModuleCode("CS1101S"),
            new Title("Programming Methodology"),
            new Description("Learn how to CS")
        );
        assertTrue(module.getDescription().getValue().equals(new Description("Learn how to CS").getValue()));
    }
}
