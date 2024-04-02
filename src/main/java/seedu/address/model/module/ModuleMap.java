package seedu.address.model.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Wrapper for a hashmap that stores all Module objects
 */
public class ModuleMap {
    private final HashMap<ModuleCode, Module> moduleMap;

    public ModuleMap() {
        this.moduleMap = new HashMap<>();
    }

    public void addModule(Module module) {
        moduleMap.put(module.getModuleCode(), module);
    }

    public boolean hasModule(ModuleCode code) {
        return moduleMap.containsKey(code);
    }
    public Module getModule(ModuleCode code) {
        return moduleMap.get(code);
    }

    public List<Module> getModulesByPrefix(String prefix) {
        List<Module> modules = new ArrayList<>();
        for (Module module : moduleMap.values()) {
            if (module.getModuleCode().getCode().startsWith(prefix)) {
                modules.add(module);
            }
        }
        return modules;
    }
}
