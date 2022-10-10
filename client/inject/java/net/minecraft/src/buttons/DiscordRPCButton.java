package net.minecraft.src.buttons;

import net.minecraft.client.DiscordRPCManager;
import net.minecraft.src.*;

public class DiscordRPCButton implements GuiOptionsButton {
    GameSettings settings;

    public DiscordRPCButton(GameSettings settings) {
        this.settings = settings;
    }

    @Override
    public String getText() {
        return "Discord RPC: " + (settings.enableDiscordRPC ? "Enabled" : "Disabled");
    }

    @Override
    public void run(GuiButton button, GuiScreen parent) {
        settings.enableDiscordRPC = !settings.enableDiscordRPC;
        if (settings.enableDiscordRPC) {
            DiscordRPCManager.start();
        }
        else {
            DiscordRPCManager.stop();
        }
    }
}
