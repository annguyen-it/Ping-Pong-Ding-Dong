package main.java.mvc.game.elements.function.intangible.bonus;

import main.java.mvc.game.elements.function.intangible.GameSide;

/**
 * Class {@code DecreaseOpponentPaddleSize} is a implementation of Bonus.
 * It makes size of opponent's {@code Paddle} becomes smaller
 * <p>
 * {@code DecreaseOpponentPaddleSize} has time limit.
 * </p>
 */
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
