package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class SoundOptionsButton implements GuiOptionsButton {
	GameSettings settings;
	
	public SoundOptionsButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Sound settings";
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
//  	settings.mc.displayGuiScreen(
//  			new GuiOptions(
//  					parent,
//  					settings.getSoundOptionsMenuContext()
//  			)
//  	);
	}

}
