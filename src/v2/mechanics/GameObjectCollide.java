package v2.mechanics;

import v2.component.paddle.Paddle;

interface GameObjectCollide extends Collide {

    void collide(Paddle paddle);
    boolean willCollideLeftPaddle(Paddle paddle);
    boolean willCollideRightPaddle(Paddle paddle);
}
