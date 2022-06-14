package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class CheatMenuModeButton implements GuiOptionsButton {
	GameSettings settings;
	
	public CheatMenuModeButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Cheat menu mode: " + GameSettings.CHEAT_MENU_MODES[settings.cheatMenuMode];
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.cheatMenuMode++;
    	settings.cheatMenuMode %= 5;
	}

}
