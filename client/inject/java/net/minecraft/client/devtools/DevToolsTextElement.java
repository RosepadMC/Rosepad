package net.minecraft.client.devtools;

public class DevToolsTextElement implements DevToolsElement {

    private final String text;

    public DevToolsTextElement(String text) {
        this.text = text;
    }

    @Override
    public void render(DevTools screen, int x, int y, boolean selected) {
        screen.drawString(text, x, y, selected ? 0xFFFFFF00 : 0xFFFFFFFF);
    }

    @Override
    public void select(DevTools screen) {

    }
}
