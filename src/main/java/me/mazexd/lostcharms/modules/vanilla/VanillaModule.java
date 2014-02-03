package me.mazexd.lostcharms.modules.vanilla;

import me.mazexd.lostcharms.modularity.Module;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;

public class VanillaModule extends Module {
    public static final String ID = "vanilla";

    public static EnchantmentAirWorker enchantAerial;

    @Override
    protected String getId()
    {
        return VanillaModule.ID;
    }

    @Override
    protected boolean canLoad()
    {
        return true;
    }

    @Override
    protected void onPreInit()
    {
        Configuration config = getConfig();

        int id = config.get(getId() + ".enchantments", "aerial-effectivity", 200).getInt();
        enchantAerial = new EnchantmentAirWorker(id, 2);

        config.addCustomCategoryComment(getId() + ".enchantments", "Change the Ids only if you know what you are doing!!!");

        MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
    }
}
