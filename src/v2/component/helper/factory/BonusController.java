package v2.component.helper.factory;

import v2.component.intangible.Bonus;
import v2.component.gameObject.immovable.star.BonusType;

import java.util.ArrayList;
import java.util.List;

public class BonusController {

    private final List<Bonus> listBonus = new ArrayList<>();

    public void receive(BonusType starType) {
        Bonus bonus = new Bonus(starType);
        int duplicateIndex = indexOf(bonus);

        if (duplicateIndex == -1) {
            add(bonus);
        }
        else {
            reset(duplicateIndex);
        }
    }

    public List<Bonus> getBonusList() {
        return listBonus;
    }

    public void update() {
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

    public void clear(){
        listBonus.clear();
    }

    private int indexOf(Bonus bonus) {
        if (listBonus.size() == 0) {
            return -1;
        }

        for (int i = 0; i < listBonus.size(); i++) {
            if (listBonus.get(i).getStarType() == bonus.getStarType()) {
                return i;
            }
        }

        return -1;
    }

    private void add(Bonus bonus){
        listBonus.add(bonus);
    }

    private void reset(int bonusIndex){
        listBonus.get(bonusIndex).reset();
    }
}
