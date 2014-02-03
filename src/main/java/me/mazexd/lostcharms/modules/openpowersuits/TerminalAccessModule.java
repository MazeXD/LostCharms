package me.mazexd.lostcharms.modules.openpowersuits;

import java.util.List;

import net.machinemuse.api.IModularItem;
import net.machinemuse.api.moduletrigger.IPlayerTickModule;
import net.machinemuse.api.moduletrigger.IToggleableModule;
import net.machinemuse.powersuits.item.ItemComponent;
import net.machinemuse.powersuits.powermodule.PowerModuleBase;
import net.machinemuse.utils.MuseCommonStrings;
import net.machinemuse.utils.MuseItemUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import openperipheral.addons.OpenPeripheralAddons;
import openperipheral.addons.glasses.ItemGlasses;
import openperipheral.addons.glasses.TerminalManagerServer;
import cpw.mods.fml.common.FMLLog;

public class TerminalAccessModule extends PowerModuleBase implements IPlayerTickModule, IToggleableModule {
    public static final String MODULE_TERMINAL_GLASSES = "Terminal Access";

    public TerminalAccessModule(List<IModularItem> validItems)
    {
        super(validItems);

        addInstallCost(MuseItemUtils.copyAndResize(ItemComponent.laserHologram, 1));
        addInstallCost(new ItemStack(OpenPeripheralAddons.Items.glasses, 1));
    }

    @Override
    public String getCategory()
    {
        return MuseCommonStrings.CATEGORY_SPECIAL;
    }

    @Override
    public String getDataName()
    {
        return MODULE_TERMINAL_GLASSES;
    }

    @Override
    public String getDescription()
    {
        return "Integrate the terminal glasses into your power suit";
    }

    @Override
    public String getLocalizedName()
    {
        return StatCollector.translateToLocal("module.terminalglasses.name");
    }

    @Override
    public String getTextureFile()
    {
        return "greenstar";
    }

    @Override
    public void onPlayerTickActive(EntityPlayer player, ItemStack item)
    {
        if (!player.getEntityWorld().isRemote)
        {
            ItemStack helmetSlot = player.inventory.armorInventory[3];

            if (helmetSlot == null || !item.isItemEqual(helmetSlot)) return;

            Long helmetGuid = ItemGlasses.extractGuid(helmetSlot);
            Long guid = ItemGlasses.extractGuid(item);

            if (helmetGuid == null || !helmetGuid.equals(guid)) return;

            FMLLog.info("Tick active: %s", guid);
            TerminalManagerServer.instance.onGlassesTick(player, guid);
        }
    }

    @Override
    public void onPlayerTickInactive(EntityPlayer arg0, ItemStack arg1)
    {
    }
}
