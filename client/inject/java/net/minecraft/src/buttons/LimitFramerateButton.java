package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class LimitFramerateButton implements GuiOptionsButton {
	GameSettings settings;
	
	public LimitFramerateButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Limit framerate: " + (settings.limitFramerate ? "ON" : "OFF");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.limitFramerate = !settings.limitFramerate;
	}

}
