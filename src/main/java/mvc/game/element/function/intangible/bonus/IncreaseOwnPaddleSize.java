package main.java.mvc.game.element.function.intangible.bonus;

/**
 * Class {@code IncreaseOwnPaddleSize} is a implementation of Bonus. It makes size of own {@code Paddle} becomes larger
 * <p>
 * {@code IncreaseOwnPaddleSize} has time limit.
 * </p>
 */
public class IncreaseOwnPaddleSize extends Bonus implements HasTimeLimit {

    public IncreaseOwnPaddleSize() {
        super(BonusType.increaseOwnPaddleSize);
    }

    @Override
    public void activate() {
        gameModel.getPaddle(receiveSide).sizeUp();
    }

    @Override
    public void deactivate() {
        gameModel.getPaddle(receiveSide).returnInitialSize();
    }
}
