package net.minecraft.client.devtools;

import java.util.List;

public class DevToolsSubmenuElement extends DevToolsTextElement {
    private final List<DevToolsElement> elements;

    public DevToolsSubmenuElement(String text, List<DevToolsElement> elements) {
        super(text);
        this.elements = elements;
    }

    @Override
    public void select(DevTools screen) {
        screen.append(elements);
    }
}
