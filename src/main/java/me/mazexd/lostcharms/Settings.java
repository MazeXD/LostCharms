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

import java.io.File;

import me.mazexd.lostcharms.enchantment.EnchantmentAirWorker;
import net.minecraftforge.common.Configuration;

public class Settings {
    private Configuration config;

    // Enchantments
    public EnchantmentAirWorker enchantAerial;

    public Settings(File file)
    {
        config = new Configuration(file);
    }

    public void load()
    {
        config.load();

        int id = config.get("enchantments", "aerial-effectivity", 200).getInt();
        enchantAerial = new EnchantmentAirWorker(id, 2);

        config.addCustomCategoryComment("enchantments", "Change the Ids only if you know what you are doing!!!");

        config.save();
    }
}
