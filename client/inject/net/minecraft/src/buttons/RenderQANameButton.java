package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class RenderQANameButton implements GuiOptionsButton {
	GameSettings settings;
	
	public RenderQANameButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Show QA build name: " + (settings.renderQAName ? "Yes" : "No");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
        settings.renderQAName = !settings.renderQAName;
	}
}
