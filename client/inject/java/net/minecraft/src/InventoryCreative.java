package net.minecraft.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import net.buj.rml.annotations.Nullable;
import net.minecraft.client.Minecraft;

public class InventoryCreative implements IInventory {
    private final Item[] baseInventory;
    private final Item[] filteredInventory;
    private String filter = "";

    public InventoryCreative(int len) {
        filteredInventory = new Item[len];
        List<Item> list = new ArrayList<>();
        for (int i = 0; i < Item.ITEMS_LIST.length; i++) {
            @Nullable Item item = Item.ITEMS_LIST[i];
            if (item != null && item instanceof Item) {
                list.add(item);
            }
        }
        baseInventory = new Item[list.size()];
        for (int i = 0; i < list.size(); i++)
            baseInventory[i] = list.get(i);
    }

    @Override
    public int getSizeInventory() {
        return baseInventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        if (slot >= filteredInventory.length) return null;
        if (filteredInventory[slot] == null) return null;
        return new ItemStack(filteredInventory[slot], 64);
    }

    @Override
    public ItemStack decrStackSize(int slot, int _1) {
        if (slot >= filteredInventory.length) return null;
        if (filteredInventory[slot] == null) return null;
        return new ItemStack(filteredInventory[slot], 64);
    }

    @Override
    public void setInventorySlotContents(int _1, ItemStack _2) {}

    @Override
    public String getInvName() {
        return "Inventory";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void onInventoryChanged() {}

    public void setFilter(String text) {
        filter = text;

        for (int i = 0; i < filteredInventory.length; i++)
            filteredInventory[i] = null;

        int o = 0;
        for (int i = 0; i < baseInventory.length; i++) {
            if (vanillaCheck(baseInventory[i]) && isSearchedFor(baseInventory[i].getName())) {
                filteredInventory[o++] = baseInventory[i];
                if (o == filteredInventory.length) break;
            }
        }
    }

    public String getFilter() {
        return new String(filter);
    }

    private boolean vanillaCheck(Item item) {
        return Minecraft.INSTANCE.theWorld.rosepadContentEnabled() || item.getVanilla();
    }

    private boolean isSearchedFor(String name) {
        List<Character> chars = new ArrayList<>();
        for (Character c : name.toLowerCase(Locale.ROOT).toCharArray()) {
            chars.add(c);
        }

        for (Character c : filter.toLowerCase(Locale.ROOT).toCharArray()) {
            int i = 0;
            for (Character c2 : chars) {
                if (c2.equals(c)) {
                    break;
                }
                i++;
            }
            if (i == chars.size()) {
                return false;
            }
            chars.remove(i);
        }

        return true;
    }
}
