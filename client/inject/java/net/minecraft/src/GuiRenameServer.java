package net.minecraft.src;

import org.lwjgl.input.Keyboard;

public class GuiRenameServer extends GuiScreen { // Yes, it's exactly the same code as GuiAddServer and GuiMultiplayer
    private final GuiMultiplayerList parentScreen;
    private int updateCounter;
    private String ipText;
    private final int serverID;

    public GuiRenameServer(final GuiMultiplayerList screen, final int serverID) {
        this.serverID = serverID;
        this.updateCounter = 0;
        this.ipText = "";
        this.parentScreen = screen;
    }

    @Override
    public void updateScreen() {
        ++this.updateCounter;
    }

    @Override
    public void actionPerformed() {
        this.controlList.clear();
        this.controlList.add(new GuiSmallButton(0,
                this.width / 3 - 75, this.height / 4 * 3 + 20, "Rename"));
        this.controlList.add(new GuiSmallButton(1,
                this.width / 3 * 2 - 75, this.height / 4 * 3 + 20, "Cancel"));
        this.controlList.get(0).enabled = this.ipText.isEmpty();
    }

    @Override
    protected void actionPerformed(final GuiButton button) {
        if (!button.enabled) {
            return;
        }
        if (button.id == 0) {
            parentScreen.setServerName(this.serverID, this.ipText);
        }
        this.mc.displayGuiScreen(this.parentScreen);
    }

    @Override
    protected void keyTyped(final char character, final int key) {
        if (character == '\u0016') {
            String clipboardString = GuiScreen.getClipboardString();
            if (clipboardString == null) {
                clipboardString = "";
            }
            else clipboardString = Util.filterWith(clipboardString, " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»");
            int length = 32 - this.ipText.length();
            if (length > clipboardString.length()) {
                length = clipboardString.length();
            }
            if (length > 0) {
                this.ipText = this.ipText + clipboardString.substring(0, length);
            }
        }
        if (key == Keyboard.KEY_RETURN) {
            this.actionPerformed(this.controlList.get(0));
        }
        if (key == 14 && this.ipText.length() > 0) {
            this.ipText = this.ipText.substring(0, this.ipText.length() - 1);
        }
        if (" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8£\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1ªº¿®¬½¼¡«»".indexOf(character) >= 0 && this.ipText.length() < 32) {
            this.ipText = this.ipText + character;
        }
        this.controlList.get(0).enabled = (this.ipText.length() > 0);
    }

    @Override
    public void drawScreen(final int mouseX, final int mouseY, final float renderPartialTick) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, "Rename server", this.width / 2, this.height / 4 - 60 + 20, 16777215);
        this.drawString(this.fontRenderer, "Minecraft Multiplayer is currently not finished, but there", this.width / 2 - 140, this.height / 4, 10526880);
        this.drawString(this.fontRenderer, "is some buggy early testing going on.", this.width / 2 - 140, this.height / 4 + 9, 10526880);
        this.drawString(this.fontRenderer, "Enter the new server name:", this.width / 2 - 140, this.height / 4 + 36, 10526880);
        final int xMin = this.width / 2 - 100;
        final int yMin = this.height / 4 * 3 - 10;
        this.drawRect(xMin - 1, yMin - 1, xMin + 200 + 1, yMin + 20 + 1, -6250336);
        this.drawRect(xMin, yMin, xMin + 200, yMin + 20, -16777216);
        this.drawString(this.fontRenderer, this.ipText + ((this.updateCounter / 6 % 2 == 0) ? "_" : ""), xMin + 4, yMin + 6, 14737632);
        super.drawScreen(mouseX, mouseY, renderPartialTick);
    }
}
