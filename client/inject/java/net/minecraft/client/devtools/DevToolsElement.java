package net.minecraft.client.devtools;

public interface DevToolsElement {
    void render(DevTools screen, int x, int y, boolean selected);
    void select(DevTools screen);
}
