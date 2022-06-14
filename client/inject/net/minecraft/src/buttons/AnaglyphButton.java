package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class AnaglyphButton implements GuiOptionsButton {
	GameSettings settings;
	
	public AnaglyphButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "3d anaglyph: " + (settings.anaglyph ? "ON" : "OFF");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.anaglyph = !settings.anaglyph;
    	settings.mc.renderEngine.refreshTextures();
	}

}
