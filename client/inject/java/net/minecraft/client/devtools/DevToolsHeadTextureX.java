package net.minecraft.client.devtools;

public class DevToolsHeadTextureX extends DevToolsRangeElement {
    public static int VALUE = 0;

    public DevToolsHeadTextureX() {
        super("Head X offset", 0, 48, VALUE);
    }

    @Override
    public void onChanged() {
        VALUE = value;
    }
}
