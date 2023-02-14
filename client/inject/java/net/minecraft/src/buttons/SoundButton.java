package net.minecraft.src.buttons;

import net.minecraft.src.*;

public class SoundButton implements GuiOptionsButton {
    GameSettings settings;

    public SoundButton(GameSettings settings) {
        this.settings = settings;
    }

    @Override
    public String getText() {
        //return "Sound: " + (settings.soundVolume ? "ON" : "OFF");
        return "";
    }

    @Override
    public void run(GuiButton button, GuiScreen parent) {
        //settings.soundVolume = !settings.soundVolume;
        //settings.mc.sndManager.onSoundOptionsChanged();
    }

}
