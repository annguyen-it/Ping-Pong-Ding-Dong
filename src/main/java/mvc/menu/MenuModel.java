package main.java.mvc.menu;

import main.java.mvc.common.Model;
import main.java.mvc.menu.sound.MenuSoundPlayer;

public class MenuModel extends Model {

    MenuSoundPlayer soundPlayer = new MenuSoundPlayer();

    public MenuModel() {
        soundPlayer.playBackgroundAudio();
    }
}
