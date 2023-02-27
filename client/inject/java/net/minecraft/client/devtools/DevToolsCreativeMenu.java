package net.minecraft.client.devtools;

public class DevToolsCreativeMenu extends DevToolsToggleElement {
    public static boolean ENABLED = false;

    public DevToolsCreativeMenu() {
        super("Enable creative menu", ENABLED);
    }

    @Override
    public void onChanged() {
        ENABLED = value;
    }
}
