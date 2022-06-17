package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class GuiScalingButton implements GuiOptionsButton {
	GameSettings settings;
	
	public GuiScalingButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
		return "GUI scaling: " + ScaledResolution.guiScaling;
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
		ScaledResolution.guiScaling++;
		if (ScaledResolution.guiScaling > 4) {
			ScaledResolution.guiScaling = 1;
		}
		settings.mc.displayGuiScreen(parent);
	}

}
