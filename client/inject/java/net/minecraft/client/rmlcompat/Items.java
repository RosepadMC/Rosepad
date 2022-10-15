package net.minecraft.client.rmlcompat;

import net.buj.rml.annotations.Nullable;

public class Items extends net.buj.rml.registry.Items {
    @Override
    protected @Nullable Item createItem(int id) {
        return new net.minecraft.src.Item(id);
    }
}
