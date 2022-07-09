package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class RenderDistanceButton implements GuiOptionsButton {
	GameSettings settings;
	
	public RenderDistanceButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Render distance: " + GameSettings.RENDER_DISTANCES[settings.renderDistance].toString();
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.renderDistance++;
    	settings.renderDistance %= 4;
	}

}
