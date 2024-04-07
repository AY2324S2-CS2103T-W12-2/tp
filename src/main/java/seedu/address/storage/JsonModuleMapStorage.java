package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleMap;

/**
 * A class to access ModuleMap data stored as a json file in the embedded jar
 */
public class JsonModuleMapStorage implements ModuleMapStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonModuleMapStorage.class);

    private final String filePath = "/data/module_data.json";
    public JsonModuleMapStorage() {}

    @Override
    public ModuleMap readModuleMap() {
        String data;
        try {
            data = new String(this.getClass().getResourceAsStream(filePath).readAllBytes());
        } catch (IOException e) {
            logger.severe("Error reading from module file: " + filePath);
            throw new Error("Error reading from module file: " + filePath, e);

        }
        requireNonNull(data);

        ModuleMap moduleMap = new ModuleMap();

        JsonAdaptedModule[] jsonModuleMap;

        try {
            jsonModuleMap = JsonUtil.readJsonString(data, JsonAdaptedModule[].class);
        } catch (DataLoadingException e) {
            logger.severe("Error reading from module file: " + filePath);
            throw new Error("Error reading from module file: " + filePath, e);
        }

        for (JsonAdaptedModule jsonModule : jsonModuleMap) {
            try {
                Module module = jsonModule.toModelType();
                moduleMap.addModule(module);
            } catch (IllegalValueException ive) {
                throw new Error("Error reading from module file: " + filePath, ive);
            }
        }
        return moduleMap;
    }
}
