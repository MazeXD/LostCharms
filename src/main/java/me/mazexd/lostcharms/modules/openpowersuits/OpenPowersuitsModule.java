package me.mazexd.lostcharms.modules.openpowersuits;

import java.util.Arrays;

import me.mazexd.lostcharms.modularity.Module;
import net.machinemuse.api.IModularItem;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.powersuits.common.ModularPowersuits;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Loader;

public class OpenPowersuitsModule extends Module {

    @Override
    public String getId()
    {
        return "openpowersuits";
    }

    @Override
    public boolean canLoad()
    {
        return Loader.isModLoaded("powersuits") && Loader.isModLoaded("OpenPeripheral");
    }

    @Override
    protected void onPreInit()
    {
        MinecraftForge.EVENT_BUS.register(new ClickHandler());
    }

    @Override
    protected void onPostInit()
    {
        ModuleManager.addModule(new TerminalAccessModule(Arrays.asList((IModularItem) ModularPowersuits.powerArmorHead)));
    }
}
