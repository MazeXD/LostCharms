package me.mazexd.lostcharms.modularity;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.FMLLog;

public class ModuleManager {
    private List<Module> modules = Lists.newArrayList();
    private Map<String, Module> loadedModules = Maps.newHashMap();

    public boolean isActive(String id)
    {
        return loadedModules.containsKey(id.toLowerCase());
    }

    public void registerModule(Module module)
    {
        modules.add(module);
    }

    public void preInit()
    {
        for (Module module : modules)
        {
            if (module.load())
            {
                FMLLog.info("[LostCharms] Loaded %s module", module.getId());
                loadedModules.put(module.getId().toLowerCase(), module);
            }
        }
    }

    public void init()
    {
        for (Module module : loadedModules.values())
        {
            module.onInit();
        }
    }

    public void postInit()
    {
        for (Module module : loadedModules.values())
        {
            module.onPostInit();
        }
    }
}
