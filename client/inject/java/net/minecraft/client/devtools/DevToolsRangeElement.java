package net.minecraft.client.devtools;

public abstract class DevToolsRangeElement implements DevToolsElement {

    private final String text;
    protected final int lowerBound;
    protected final int upperBound;
    protected int value;

    public DevToolsRangeElement(String text, int lowerBound, int upperBound, int value) {
        this.text = text;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.value = value;
    }

    @Override
    public void render(DevTools screen, int x, int y, boolean selected) {
        int len = screen.strlen(text) + 10;
        screen.drawString(text, x, y, selected ? 0xFF00FFFF : 0xFFFFFFFF);
        screen.drawRect(x + len - 2, y, x + len - 2 + 100, y + 10, 0xFF111111);
        screen.drawRect(
                x + len - 2, y,
                x + len - 2 + Math.round((value - lowerBound) / (float) upperBound * 100), y + 10,
                selected ? 0xFF00FFFF : 0xFFFFFFFF
        );
        screen.drawString("" + value, x + len + 105, y, selected ? 0xFF00FFFF : 0xFFFFFFFF);
    }

    @Override
    public void select(DevTools screen) {
        screen.append(this);
    }

    public void add(int value) {
        this.value += value;
        if (this.value < lowerBound) this.value = lowerBound;
        else if (this.value > upperBound) this.value = upperBound;
    }

    public abstract void onChanged();
}
