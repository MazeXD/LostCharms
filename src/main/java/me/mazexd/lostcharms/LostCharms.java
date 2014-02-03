/* The MIT License (MIT)
 * 
 * Copyright (c) 2014 MazeXD
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.mazexd.lostcharms;

import me.mazexd.lostcharms.modularity.ModuleManager;
import me.mazexd.lostcharms.modules.openpowersuits.OpenPowersuitsModule;
import me.mazexd.lostcharms.modules.vanilla.VanillaModule;
import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = LostCharms.MODID, version = LostCharms.VERSION, dependencies = "after:*")
@NetworkMod(clientSideRequired = true)
public class LostCharms {
    public static final String MODID = "lostcharms";
    public static final String VERSION = "{$VERSION}";

    public static Configuration CONFIG;
    private static ModuleManager MODULES;

    @EventHandler
    public void onPreinit(FMLPreInitializationEvent event)
    {
        CONFIG = new Configuration(event.getSuggestedConfigurationFile());
        CONFIG.load();

        MODULES = new ModuleManager();
        MODULES.registerModule(new VanillaModule());

        MODULES.preInit();

        if (CONFIG.hasChanged())
        {
            CONFIG.save();
        }
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event)
    {
        MODULES.init();
    }

    @EventHandler
    public void onPostInit(FMLPostInitializationEvent event)
    {
        MODULES.postInit();
    }

    public static boolean isModuleActive(String id)
    {
        return MODULES.isActive(id);
    }
}
