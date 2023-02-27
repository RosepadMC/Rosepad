package net.minecraft.client.devtools;

import net.minecraft.client.Minecraft;
import net.minecraft.src.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class DevTools extends GuiScreen {

    private final List<List<DevToolsElement>> elements = new ArrayList<>();
    private int element = 0;
    private final GuiScreen parent;

    private DevToolsRangeElement rangeElement = null;

    public DevTools(List<DevToolsElement> elements, GuiScreen parent, Minecraft mc) {
        this.elements.add(elements);
        this.parent = parent;
        this.mc = mc;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float renderPartialTick) {
        if (elements.size() == 0) {
            this.mc.displayGuiScreen(parent);
            return;
        }

        //if (parent != null) parent.drawScreen(0, 0, renderPartialTick);

        GL11.glDisable(GL11.GL_DEPTH_TEST);
        this.drawRect(0, 0, mc.displayWidth, mc.displayHeight, 0xAA000000);
        int yoffset = 0;
        for (DevToolsElement element : elements.get(elements.size() - 1)) {
            element.render(this, 10, 10 + yoffset * 10, yoffset++ == this.element);
        }
        GL11.glEnable(GL11.GL_DEPTH_TEST);
    }

    @Override
    public void handleKeyboardInput() {
        if (Keyboard.getEventKeyState()) {
            switch (Keyboard.getEventKey()) {
                case Keyboard.KEY_UP:
                    if (rangeElement != null) break;
                    element--;
                    if (element == -1) {
                        element = elements.get(elements.size() - 1).size() - 1;
                    }
                    break;
                case Keyboard.KEY_DOWN:
                    if (rangeElement != null) break;
                    element++;
                    if (element == elements.get(elements.size() - 1).size()) {
                        element = 0;
                    }
                    break;
                case Keyboard.KEY_LEFT:
                    if (rangeElement == null) break;
                    rangeElement.add(-1);
                    break;
                case Keyboard.KEY_RIGHT:
                    if (rangeElement == null) break;
                    rangeElement.add(1);
                    break;
                case Keyboard.KEY_RETURN:
                    if (rangeElement != null) {
                        rangeElement.onChanged();
                        rangeElement = null;
                        break;
                    }
                    elements.get(elements.size() - 1).get(element).select(this);
                    break;
                case Keyboard.KEY_BACK:
                    if (rangeElement != null) {
                        rangeElement = null;
                        break;
                    }
                    element = 0;
                    elements.remove(elements.size() - 1);
                    break;
                case Keyboard.KEY_ESCAPE:
                    mc.displayGuiScreen(parent);
                    break;
            }
        }
    }

    public static List<DevToolsElement> skinRenderPage() {
        List<DevToolsElement> elements = new ArrayList<>();

        elements.add(new DevToolsEnableX64SkinSupportToggle());
        elements.add(new DevToolsHeadTextureX());
        elements.add(new DevToolsHeadTextureY());

        return elements;
    }

    public static List<DevToolsElement> mainPage() {
        List<DevToolsElement> elements = new ArrayList<>();

        elements.add(new DevToolsTextElement("# Render"));
        elements.add(new DevToolsSubmenuElement("> Skin render", skinRenderPage()));
        elements.add(new DevToolsEnableColoredLighting());
        elements.add(new DevToolsCreativeMenu());

        return elements;
    }

    public void drawRect(int x, int y, int endX, int endY, int color) {
        super.drawRect(x, y, endX, endY, color);
    }

    public void drawString(String text, int beginX, int beginY, int color) {
        this.drawString(mc.fontRenderer, text, beginX, beginY, color);
    }

    public int strlen(String text) {
        return mc.fontRenderer.getStringWidth(text);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return parent != null && parent.doesGuiPauseGame();
    }

    public void append(List<DevToolsElement> elements) {
        element = 0;
        this.elements.add(elements);
    }

    public void append(DevToolsRangeElement element) {
        rangeElement = element;
    }
}
