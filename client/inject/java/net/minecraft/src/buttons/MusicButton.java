package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class MusicButton implements GuiOptionsButton {
    GameSettings settings;

    public MusicButton(GameSettings settings) {
        this.settings = settings;
    }

    @Override
    public String getText() {
        //return "Music: " + (settings.musicVolume ? "ON" : "OFF");
        return "";
    }

    @Override
    public void run(GuiButton button, GuiScreen parent) {
        //settings.musicVolume = !settings.musicVolume;
        //settings.mc.sndManager.onSoundOptionsChanged();
    }

}
