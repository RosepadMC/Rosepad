package net.minecraft.client.devtools;

public abstract class DevToolsToggleElement implements DevToolsElement {
    protected boolean value;
    private final String text;

    public DevToolsToggleElement(String text, boolean value) {
        this.text = text;
        this.value = value;
    }

    @Override
    public void render(DevTools screen, int x, int y, boolean selected) {
        screen.drawString(text + ": " + (value ? "Yes" : "No"), x, y, selected ? 0xFFFFFF00 : 0xFFFFFFFF);
    }

    @Override
    public void select(DevTools screen) {
        value = !value;
        onChanged();
    }

    public abstract void onChanged();
}
