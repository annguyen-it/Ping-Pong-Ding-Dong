package main.java.mvc.game.element.function.intangible.bonus;

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
