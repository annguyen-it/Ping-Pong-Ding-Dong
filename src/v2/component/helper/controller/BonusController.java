package v2.component.helper.controller;

import v2.board.GameSide;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.intangible.bonus.Bonus;
import v2.component.intangible.bonus.BonusType;
import v2.model.GameModel;

import java.util.ArrayList;
import java.util.List;

public class BonusController {

    private final GameModel gameModel;
    private final List<Bonus> listBonus = new ArrayList<>();

    public BonusController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public List<Bonus> getBonusList() {
        return listBonus;
    }

    public void receive(BonusType bonusType, GameSide.Side side, Ball ball) {
        Bonus bonus = Bonus.type(bonusType).with(gameModel).by(side, ball);
        int duplicateIndex = indexOf(bonus);

        if (duplicateIndex == -1) {
            activate(bonus);
        }
        else {
            reset(listBonus.get(duplicateIndex));
        }
    }

    private int indexOf(Bonus bonus) {
        if (listBonus.size() == 0) {
            return -1;
        }

        for (int i = 0; i < listBonus.size(); i++) {
            if (listBonus.get(i).sameTypeWith(bonus)) {
                return i;
            }
        }

        return -1;
    }

    private void activate(Bonus bonus) {
        listBonus.add(bonus);
        bonus.activate();
    }

    private void reset(Bonus bonus) {
        bonus.reset();
    }

    public void update() {
        for (int i = 0; i < listBonus.size(); ) {
            listBonus.get(i).decreaseTimeLeft();

            if (listBonus.get(i).timeout()) {
                removeBonusAt(i);
            }
            else {
                i++;
            }
        }
    }

    private void removeBonusAt(int index){
        listBonus.get(index).deactivate();
        listBonus.remove(index);
    }

    public void clear() {
        for (Bonus bonus : listBonus){
            bonus.deactivate();
        }

        listBonus.clear();
    }
}
