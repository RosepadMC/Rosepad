package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

import java.io.File;

public class GuiCreateWorld extends GuiScreen {
    private final GuiScreen parent;
    private String name = "";
    private int updateCounter = 0;

    public GuiCreateWorld(GuiScreen parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed() {
        controlList.clear();

        this.controlList.add(new GuiSmallButton(0, this.width / 2 - 170, this.height - 30, "Back"));
        this.controlList.add(new GuiSmallButton(1, this.width / 2 + 20, this.height - 30, "Create world"));
        this.controlList.get(1).enabled = (name.length() > 0);
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0:
                mc.displayGuiScreen(parent);
                break;
            case 1:
            if (!name.isEmpty()) {
                File file = new File(Minecraft.getMinecraftDir(), "saves/" + name);
                if (file.exists()) break;
                this.mc.displayGuiScreen(null);
                this.mc.playerController = new PlayerControllerSP(this.mc);
                for (int i = 0; i < 6; i++) {
                    try {
                        this.mc.startWorld(name);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.mc.displayGuiScreen(null);
            }
                break;
        }
    }

    @Override
    protected void keyTyped(final char character, final int key) {
        if (character == '\u0016') {
            String clipboardString = GuiScreen.getClipboardString();
            if (clipboardString == null) {
                clipboardString = "";
            }
            else clipboardString = Util.filterWith(clipboardString, " -0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz");
            int length = 32 - this.name.length();
            if (length > clipboardString.length()) {
                length = clipboardString.length();
            }
            if (length > 0) {
                this.name = this.name + clipboardString.substring(0, length);
            }
        }
        if (key == Keyboard.KEY_RETURN) {
            this.actionPerformed(this.controlList.get(0));
        }
        if (key == 14 && this.name.length() > 0) {
            this.name = this.name.substring(0, this.name.length() - 1);
        }
        if (" -0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz".indexOf(character) >= 0 && this.name.length() < 32) {
            this.name = this.name + character;
        }
        this.controlList.get(1).enabled = (this.name.length() > 0);
    }

    @Override
    public void updateScreen() {
        updateCounter++;
        updateCounter %= 12;
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float renderPartialTick) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Create world", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRenderer, "World name", this.width / 2 - 140, this.height / 4 + 9, 10526880);
        final int xMin = this.width / 2 - 100;
        final int yMin = this.height / 4 * 3 - 10;
        this.drawRect(xMin - 1, yMin - 1, xMin + 200 + 1, yMin + 20 + 1, -6250336);
        this.drawRect(xMin, yMin, xMin + 200, yMin + 20, -16777216);
        this.drawString(this.fontRenderer, name + ((this.updateCounter / 6 % 2 == 0) ? "_" : ""), xMin + 4, yMin + 6, 14737632);
        super.drawScreen(mouseX, mouseY, renderPartialTick);
    }
}
