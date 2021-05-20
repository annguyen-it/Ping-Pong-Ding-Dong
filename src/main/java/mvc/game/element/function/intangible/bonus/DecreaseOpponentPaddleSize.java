package main.java.mvc.game.element.function.intangible.bonus;

import main.java.mvc.game.element.function.intangible.GameSide;

public class DecreaseOpponentPaddleSize extends Bonus implements HasTimeLimit {

    public DecreaseOpponentPaddleSize() {
        super(BonusType.decreaseOpponentPaddleSize);
    }

    @Override
    public void activate() {
        gameModel.getPaddle(GameSide.getOpposite(receiveSide)).sizeDown();
    }

    @Override
    public void deactivate() {
        gameModel.getPaddle(GameSide.getOpposite(receiveSide)).returnInitialSize();
    }
}
