package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class ControlsOptionsButton implements GuiOptionsButton {
	GameSettings settings;
	
	public ControlsOptionsButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Controls";
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.mc.displayGuiScreen(
    			new GuiControls(parent)
    	);
	}

}
