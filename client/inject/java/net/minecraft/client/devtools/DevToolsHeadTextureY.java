package net.minecraft.client.devtools;

public class DevToolsHeadTextureY extends DevToolsRangeElement {
    public static int VALUE = 0;

    public DevToolsHeadTextureY() {
        super("Head Y offset", 0, 48, VALUE);
    }

    @Override
    public void onChanged() {
        VALUE = value;
    }
}
