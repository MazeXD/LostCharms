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

import me.mazexd.lostcharms.enchantment.EnchantmentHandler;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = LostCharms.MODID, version = LostCharms.VERSION)
@NetworkMod(clientSideRequired = true)
public class LostCharms {
    public static final String MODID = "lostcharms";
    public static final String VERSION = "{$VERSION}";

    public static Settings SETTINGS;

    @EventHandler
    public void onPreinit(FMLPreInitializationEvent event)
    {
        SETTINGS = new Settings(event.getSuggestedConfigurationFile());
        SETTINGS.load();

        MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
    }
}
