package net.minecraft.src;

import net.minecraft.src.GuiScreenInputPass;

import org.lwjgl.input.Keyboard;

public class GuiName extends GuiScreen {
    private final GuiScreen parentScreen;
    private int updateCounter;
    private String name;

    public GuiName(final GuiScreen screen) {
        this.updateCounter = 0;
        this.name = GuiScreenInputPass.name;
        this.parentScreen = screen;
    }
    
    @Override
    public void updateScreen() {
        ++this.updateCounter;
    }
    
    @Override
    public void actionPerformed() {
        this.controlList.clear();
        this.controlList.add(new GuiButton(0,
                this.width / 2 - 100, this.height / 4 + 96 + 12, "Change"));
        this.controlList.add(new GuiButton(1,
                this.width / 2 - 100, this.height / 4 + 120 + 12, "Cancel"));
        this.controlList.get(0).enabled = false;
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) {
        if (!button.enabled) {
            return;
        }
        if (button.id == 1) {
            this.mc.displayGuiScreen(this.parentScreen);
        }
        if (button.id == 0) {
            GuiScreenInputPass.name = this.name;
            this.mc.displayGuiScreen(this.parentScreen);
        }
    }
    @Override
    protected void keyTyped(final char character, final int key) {
        if (character == '\u0016') {
            String clipboardString = GuiScreen.getClipboardString();
            if (clipboardString == null) {
                clipboardString = "";
            }
            else clipboardString = Util.filterWith(clipboardString, "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»");
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
        if ("!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»".indexOf(character) >= 0 && this.name.length() < 32) {
            this.name = this.name + character;
        }
        this.controlList.get(0).enabled = (this.name.length() > 0);
    }
    
    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float renderPartialTick) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Change name:", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRenderer, "Please enter your new username:", this.width / 2 - 140, this.height / 4 + 36, 10526880);
        final int xMin = this.width / 2 - 100;
        final int yMin = this.height / 4 - 10 + 50 + 18;
        this.drawRect(xMin - 1, yMin - 1, xMin + 200 + 1, yMin + 20 + 1, -6250336);
        this.drawRect(xMin, yMin, xMin + 200, yMin + 20, -16777216);
        this.drawString(this.fontRenderer, this.name + ((this.updateCounter / 6 % 2 == 0) ? "_" : ""), xMin + 4, yMin + 6, 14737632);
        super.drawScreen(mouseX, mouseY, renderPartialTick);
    }
}
