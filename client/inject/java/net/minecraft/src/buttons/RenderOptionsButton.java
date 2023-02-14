package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class RenderOptionsButton implements GuiOptionsButton {
	GameSettings settings;
	
	public RenderOptionsButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
		return "Render settings";
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
//  	settings.mc.displayGuiScreen(
//  			new GuiOptions(
//  					parent,
//  					settings.getRenderOptionsMenuContext()
//  			)
//  	);
	}

}
