package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class DifficultyButton implements GuiOptionsButton {
	GameSettings settings;
	
	public DifficultyButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Difficulty: " + GameSettings.DIFFICULTY_LEVELS[settings.difficulty];
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.difficulty++;
    	settings.difficulty %= 5;
	}

}
