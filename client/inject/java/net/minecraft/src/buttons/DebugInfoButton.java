package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class DebugInfoButton implements GuiOptionsButton {
	GameSettings settings;
	
	public DebugInfoButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
		return "";
    	//return "Debug info: " + (settings.showFPS ? "ON" : "OFF");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	//settings.showFPS = !settings.showFPS;
	}

}
