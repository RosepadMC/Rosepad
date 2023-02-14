package net.minecraft.src;

import org.lwjgl.opengl.GL11;

import net.buj.rml.annotations.Nullable;
import net.minecraft.client.Minecraft;

public class GuiSlider extends GuiButton {
    public static interface ChangeHandler {
        void run(float percent);
    }

    private float percent;
    private boolean listening = false;

    private @Nullable ChangeHandler onChange = null;

    public GuiSlider(int id, int posX, int posY, int width, int height, String text, float percent) {
        super(id, posX, posY, width, height, text);
        this.percent = percent;
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
        if (!this.visible) return;
        final FontRenderer fontRenderer = minecraft.fontRenderer;

        GL11.glBindTexture(3553, minecraft.renderEngine.getTexture("/gui/gui.png"));
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

        final int n = enabled ? isHovered(mouseX, mouseY) ? 2 : 1 : 0;

        this.drawTexturedModalRect(this.xPosition, this.yPosition,
                                   0, 46,
                                   this.width / 2, this.height);
        this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition,
                                   200 - this.width / 2, 46,
                                   this.width / 2, this.height);
        this.drawTexturedModalRect(this.xPosition + (int) (percent * (width - 8)) + 1, this.yPosition,
                                   201, 46 + n * 20,
                                   6, this.height);
        this.drawCenteredString(fontRenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, -6250336);
    }

    private void moved(int x) {
        x -= 3;
        if (x < 0) x = 0;
        if (x > width - 8) x = width - 8;

        float val = (float) x / (width - 8);
        if (percent != val && onChange != null) onChange.run(val);
        percent = val;
    }

    @Override
    public boolean listensForMouseEvents() {
        return listening;
    }

    @Override
    public void mousePressed(int mouseX, int mouseY) {
        listening = true;
        moved(mouseX);
    }

    @Override
    public void mouseMoved(int mouseX, int mouseY) {
        if (!listening) return;
        moved(mouseX);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        if (!listening) return;
        listening = false;
        moved(mouseX);
    }

    public void setOnChangeHandler(ChangeHandler listener) {
        onChange = listener;
    }
}
