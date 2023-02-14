package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class GameOptionsButton implements GuiOptionsButton {
	GameSettings settings;
	
	public GameOptionsButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Game settings";
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
//  	settings.mc.displayGuiScreen(
//  			new GuiOptions(
//  					parent,
//  					settings.getGameOptionsMenuContext()
//  			)
//  	);
	}

}
