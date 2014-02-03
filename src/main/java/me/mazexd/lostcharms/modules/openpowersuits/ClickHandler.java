package me.mazexd.lostcharms.modules.openpowersuits;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.machinemuse.api.ModuleManager;
import net.machinemuse.powersuits.item.ItemPowerArmorHelmet;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import openperipheral.addons.glasses.ItemGlasses;
import openperipheral.addons.glasses.TileEntityGlassesBridge;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClickHandler {
    @ForgeSubscribe
    public void handleUse(PlayerInteractEvent e)
    {
        if (FMLCommonHandler.instance().getEffectiveSide().isServer() && e.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)
        {
            ItemStack heldItem = e.entityPlayer.getHeldItem();

            if (heldItem == null || !(heldItem.getItem() instanceof ItemPowerArmorHelmet)) return;

            if (!ModuleManager.itemHasActiveModule(heldItem, "Terminal Access")) return;

            TileEntity tileEntity = e.entityPlayer.getEntityWorld().getBlockTileEntity(e.x, e.y, e.z);

            if (tileEntity == null || !(tileEntity instanceof TileEntityGlassesBridge)) return;

            try
            {
                Method method = TileEntityGlassesBridge.class.getDeclaredMethod("linkGlasses", new Class[] { ItemStack.class });
                method.setAccessible(true);
                method.invoke(tileEntity, new Object[] { heldItem });
                e.entityPlayer.addChatMessage(String.format("Linked Helmet to Glasses Bridge (GUID: %s)", new Object[] { ItemGlasses.extractGuid(heldItem) }));

                e.setCanceled(true);
            }
            catch (NoSuchMethodException e1)
            {
                e1.printStackTrace();
            }
            catch (SecurityException e1)
            {
                e1.printStackTrace();
            }
            catch (IllegalAccessException e1)
            {
                e1.printStackTrace();
            }
            catch (IllegalArgumentException e1)
            {
                e1.printStackTrace();
            }
            catch (InvocationTargetException e1)
            {
                e1.printStackTrace();
            }
        }
    }
}
