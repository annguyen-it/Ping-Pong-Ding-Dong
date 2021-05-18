package main.java.mvc.game.component.intangible.bonus;

import main.java.mvc.game.board.GameSide;

public class DecreaseEnemyPaddleSize extends Bonus implements HasTimeLimit {

    public DecreaseEnemyPaddleSize() {
        super(BonusType.decreaseEnemyPaddleSize);
    }

    @Override
    public void activate() {
        gameModel.getPaddle(GameSide.opposite(receiveSide)).sizeDown();
    }

    @Override
    public void deactivate() {
        gameModel.getPaddle(GameSide.opposite(receiveSide)).returnInitialSize();
    }
}
