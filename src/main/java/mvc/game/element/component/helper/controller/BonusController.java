package main.java.mvc.game.element.component.helper.controller;

import main.java.mvc.game.element.function.intangible.GameSide.Side;
import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.element.function.intangible.bonus.Bonus;
import main.java.mvc.game.element.function.intangible.bonus.BonusType;
import main.java.mvc.game.GameModel;

import java.util.ArrayList;
import java.util.List;

public class BonusController {

    private final GameModel gameModel;
    private final List<Bonus> bonusList = new ArrayList<>();

    public BonusController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public List<Bonus> getBonusList() {
        return bonusList;
    }

    public void receive(BonusType bonusType, Side side, Ball ball) {
        Bonus bonus = Bonus.type(bonusType).with(gameModel).by(side, ball);
        int duplicateIndex = indexOf(bonus);

        if (duplicateIndex == -1) {
            activate(bonus);
        }
        else {
            reset(bonusList.get(duplicateIndex));
        }
    }

    private int indexOf(Bonus bonus) {
        if (bonusList.size() == 0) {
            return -1;
        }

        for (int i = 0; i < bonusList.size(); i++) {
            if (bonusList.get(i).sameTypeWith(bonus)) {
                return i;
            }
        }

        return -1;
    }

    private void activate(Bonus bonus) {
        bonusList.add(bonus);
        bonus.activate();
    }

    private void reset(Bonus bonus) {
        bonus.reset();
    }

    public void update() {
        for (int i = 0; i < bonusList.size(); ) {
            bonusList.get(i).decreaseTimeLeft();

            if (bonusList.get(i).timeout()) {
                removeBonusAt(i);
            }
            else {
                i++;
            }
        }
    }

    private void removeBonusAt(int index) {
        bonusList.get(index).deactivate();
        bonusList.remove(index);
    }

    public void clear() {
        for (Bonus bonus : bonusList) {
            bonus.deactivate();
        }

        bonusList.clear();
    }

    public List<BonusType> getExcludeBonusTypeList() {
        List<BonusType> result = new ArrayList<>();

        for (Bonus bonus : bonusList) {
            if (!bonus.canAppearWhenActivated()) {
                result.add(bonus.getType());
            }
        }

        return result;
    }
}
