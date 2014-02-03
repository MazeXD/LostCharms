package me.mazexd.lostcharms.modules.openpowersuits.asm;

import java.util.Iterator;

import me.mazexd.lostcharms.modules.openpowersuits.TerminalAccessModule;
import net.machinemuse.api.ModuleManager;
import net.machinemuse.powersuits.item.ItemPowerArmorHelmet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.VarInsnNode;

import cpw.mods.fml.common.FMLLog;

public class ItemGlassesPatcher implements IClassTransformer {

    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes)
    {
        if ("openperipheral.addons.glasses.ItemGlasses".equals(name))
        {
            ClassNode node = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept(node, 0);

            patchItemGlasses(node, false);

            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
            node.accept(writer);
            return writer.toByteArray();
        }

        return bytes;
    }

    private void patchItemGlasses(ClassNode node, boolean obfuscated)
    {
        Iterator<MethodNode> methods = node.methods.iterator();
        MethodNode method = null;

        while (methods.hasNext())
        {
            MethodNode m = methods.next();

            if (m.name.equals("getGlassesItem") && m.desc.equals("(Lnet/minecraft/entity/player/EntityPlayer;)Lnet/minecraft/item/ItemStack;"))
            {
                method = m;
                break;
            }
        }

        if (method == null) return;

        MethodNode proxyMethod = new MethodNode(method.access, method.name, method.desc, method.signature, method.exceptions.toArray(new String[0]));
        InsnList instructions = new InsnList();

        instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        instructions.add(new VarInsnNode(Opcodes.ALOAD, 0));
        instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, node.name, method.name + "Proxied", method.desc));
        instructions.add(new MethodInsnNode(Opcodes.INVOKESTATIC, "me/mazexd/lostcharms/modules/openpowersuits/asm/ItemGlassesPatcher", "handle",
                "(Lnet/minecraft/entity/player/EntityPlayer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;"));
        instructions.add(new InsnNode(Opcodes.ARETURN));

        proxyMethod.instructions.add(instructions);
        node.methods.add(proxyMethod);

        method.name = method.name + "Proxied";
    }

    public static ItemStack handle(EntityPlayer player, ItemStack result)
    {
        if (player == null || result != null) return result;

        ItemStack helmetSlot = player.inventory.armorInventory[3];

        if (helmetSlot == null || !(helmetSlot.getItem() instanceof ItemPowerArmorHelmet)) return null;

        if (!ModuleManager.itemHasActiveModule(helmetSlot, TerminalAccessModule.MODULE_TERMINAL_GLASSES)) return null;

        FMLLog.info("[LostCharm] Calling proxy of getGlassesItem");
        return helmetSlot;
    }
}
