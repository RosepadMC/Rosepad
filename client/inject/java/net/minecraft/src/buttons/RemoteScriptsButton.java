package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class RemoteScriptsButton implements GuiOptionsButton {
	GameSettings settings;
	
	public RemoteScriptsButton(GameSettings settings) {
		this.settings = settings;
	}

	@Override
	public String getText() {
    	return "Remote scripts: " + (settings.acceptRemoteScripts ? "ASK" : "DENY");
	}

	@Override
	public void run(GuiButton button, GuiScreen parent) {
    	settings.acceptRemoteScripts = !settings.acceptRemoteScripts;
	}

}
