package net.minecraft.client.devtools;

public class DevToolsEnableColoredLighting extends DevToolsToggleElement {
    public static boolean ENABLED = false;

    public DevToolsEnableColoredLighting() {
        super("Enable colored lighting", ENABLED);
    }

    @Override
    public void onChanged() {
        ENABLED = value;
    }
}
