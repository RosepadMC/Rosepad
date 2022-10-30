package net.minecraft.client.rmlcompat;

import net.buj.rml.annotations.Nullable;
import net.buj.rml.registry.Items;

public class Blocks extends net.buj.rml.registry.Blocks {
    public Blocks(Items source) {
        super(source);
    }

    @Override
    protected @Nullable Block createBlock(int id) {
        return new net.minecraft.src.Block(id);
    }
}
