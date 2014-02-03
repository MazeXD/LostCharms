package me.mazexd.lostcharms.asm;

import java.util.Map;

import me.mazexd.lostcharms.modules.openpowersuits.asm.ItemGlassesPatcher;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({ "me.mazexd.lostcharms.asm", "me.mazexd.lostcharms.modules.openpowersuits.asm" })
public class LostCharmsLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass()
    {
        return new String[] { ItemGlassesPatcher.class.getName() };
    }

    @Override
    public String getModContainerClass()
    {
        return null;
    }

    @Override
    public String getSetupClass()
    {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data)
    {
    }
}
