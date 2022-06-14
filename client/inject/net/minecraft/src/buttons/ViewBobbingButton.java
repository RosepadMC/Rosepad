package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class ViewBobbingButton implements GuiOptionsButton {
	GameSettings settings;
	
	public ViewBobbingButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "View bobbing: " + (settings.viewBobbing ? "ON" : "OFF");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.viewBobbing = !settings.viewBobbing;
	}

}
