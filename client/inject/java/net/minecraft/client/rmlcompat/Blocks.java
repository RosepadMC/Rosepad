package net.minecraft.client.rmlcompat;

import net.buj.rml.annotations.Nullable;

public class Blocks extends net.buj.rml.registry.Blocks {
    @Override
    protected @Nullable Block createBlock(int id) {
        return new net.minecraft.src.Block(id);
    }
}
