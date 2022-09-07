package net.minecraft.src.buttons;

import net.minecraft.src.GameSettings;
import net.minecraft.src.GuiButton;
import net.minecraft.src.GuiOptionsButton;
import net.minecraft.src.GuiScreen;

public class RenderChunkNameButton implements GuiOptionsButton {
	GameSettings settings;

	public RenderChunkNameButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Show chunk name: " + (settings.renderChunkName ? "Yes" : "No");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
        settings.renderChunkName = !settings.renderChunkName;
	}
}
