package v2.component.helper.factory;

import v2.component.gameObject.immovable.bonus.Bonus;
import v2.view.GameView;

import java.util.ArrayList;
import java.util.List;

public class BonusFactory {

    private final List<Bonus> listBonus = new ArrayList<>();

    private int exist;
    private Bonus bonus;

    public BonusFactory() {
        //removeBonus();
    }

    public void tryRemoveBonus() {
        for (int i = 0; i < listBonus.size(); ) {
            if (listBonus.get(i).getTimeLeft() <= 0) {
                listBonus.remove(i);
            }
            else {
                i++;
            }
        }
    }

    public void update() {
        bonus = new Bonus(4400);

        if (!isDuplicate(bonus)) {
            listBonus.add(bonus);
            GameView.listTimeLeft.add(bonus.getTimeLeft());
        }
        else {
            listBonus.get(exist).setTimeLeft(4400);
        }
        tryRemoveBonus();
    }

    public List<Bonus> getListBonus() {
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
}
