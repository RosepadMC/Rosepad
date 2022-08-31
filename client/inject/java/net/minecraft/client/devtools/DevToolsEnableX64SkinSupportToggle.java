package net.minecraft.client.devtools;

public class DevToolsEnableX64SkinSupportToggle extends DevToolsToggleElement {
    public static boolean ENABLED = false;

    public DevToolsEnableX64SkinSupportToggle() {
        super("Enable X64 skin support", ENABLED);
    }

    @Override
    public void onChanged() {
        ENABLED = value;
    }
}
