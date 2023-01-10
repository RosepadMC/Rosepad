package net.minecraft.src;

public class GuiSlider extends GuiButton {
    private float percent;
    public GuiSlider(int id, int posX, int posY, int width, int height, String text, float percent) {
        super(id, posX, posY, width, height, text);
        this.percent = percent;
    }


}
