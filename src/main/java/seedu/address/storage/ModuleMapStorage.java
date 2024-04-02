package seedu.address.storage;

import seedu.address.model.module.ModuleMap;

/**
 * Represents a storage for {@link seedu.address.model.module.ModuleMap}.
 */
public interface ModuleMapStorage {
    ModuleMap readModuleMap();
}
