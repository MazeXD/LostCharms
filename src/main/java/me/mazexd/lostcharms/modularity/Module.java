package me.mazexd.lostcharms.modularity;

import me.mazexd.lostcharms.LostCharms;
import net.minecraftforge.common.Configuration;

public abstract class Module {
    public Configuration getConfig()
    {
        return LostCharms.CONFIG;
    }

    public boolean load()
    {
        boolean isLoading = getConfig().get(getId(), "enabled", true).getBoolean(true) && canLoad();

        if (isLoading)
        {
            onPreInit();
        }

        getConfig().addCustomCategoryComment(getId(), "Settings for " + getId() + " module");

        return isLoading;
    }

    protected void onPreInit()
    {
    }

    protected void onInit()
    {
    }

    protected void onPostInit()
    {
    }

    protected abstract String getId();

    protected abstract boolean canLoad();
}
