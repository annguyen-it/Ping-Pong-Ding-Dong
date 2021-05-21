package main.java.mvc.menu;

import main.java.mvc.common.Model;
import main.java.mvc.menu.sound.MenuSoundPlayer;

public class MenuModel extends Model {

    public MenuModel() {
        MenuSoundPlayer soundPlayer = new MenuSoundPlayer();
        soundPlayer.playBackgroundAudio();
    }
}
