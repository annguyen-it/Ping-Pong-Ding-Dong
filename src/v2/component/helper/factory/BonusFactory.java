package v2.component.helper.factory;

import v2.component.gameObject.immovable.bonus.Bonus;
import v2.component.gameObject.immovable.star.StarType;
import v2.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class BonusFactory {

    private final List<Bonus> listBonus = new ArrayList<>();

    private int exist;

    public BonusFactory() {
        //removeBonus();
    }

    public void createBonus(StarType starType) {
        Bonus bonus = new Bonus(starType);

        if (!isDuplicate(bonus)) {
            listBonus.add(bonus);
            GameView.listTimeLeft.add(bonus.getTimeLeft());
        }
        else {
            listBonus.get(exist).setTimeLeft(4400);
        }
    }

    public List<Bonus> getBonusList() {
        return listBonus;
    }

    public Bonus getBonus(int i) {
        return listBonus.get(i);
    }

    private boolean isDuplicate(Bonus bonus) {
        if (listBonus.size() == 0) {
            return false;
        }

        for (int i = 0; i < listBonus.size(); i++) {
            if (listBonus.get(i).getColor() == bonus.getColor()) {
                exist = i;
                return true;
            }
        }

        return false;
    }

    public void update(){
        for (int i = 0; i < listBonus.size(); ) {
            listBonus.get(i).decreaseTimeLeft();

            if (listBonus.get(i).getTimeLeft() <= 0) {
                listBonus.remove(i);
            }
            else {
                i++;
            }
        }
    }
}
