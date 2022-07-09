package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class GraphicsModeButton implements GuiOptionsButton {
	GameSettings settings;
	
	public GraphicsModeButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Graphics: " + (settings.fancyGraphics ? "FANCY" : "FAST");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.fancyGraphics = !settings.fancyGraphics;
    	settings.mc.renderGlobal.loadRenderers();
	}

}
