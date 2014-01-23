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

package me.mazexd.lostcharms.enchantment;

import me.mazexd.lostcharms.LostCharms;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EnchantmentHandler {
    @ForgeSubscribe
    public void adjustBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        if (!ForgeHooks.canHarvestBlock(event.block, event.entityPlayer, event.metadata)) return;

        if (!event.entityPlayer.onGround && hasEnchantment(event.entityPlayer, LostCharms.SETTINGS.enchantAerial))
        {
            event.newSpeed *= 5.0f;
        }
    }

    private boolean hasEnchantment(EntityPlayer player, Enchantment enchantment)
    {
        return EnchantmentHelper.getMaxEnchantmentLevel(enchantment.effectId, player.getLastActiveItems()) > 0;
    }
}
