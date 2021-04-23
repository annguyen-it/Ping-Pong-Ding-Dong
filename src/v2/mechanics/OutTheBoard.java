package v2.mechanics;

import v2.component.paddle.Paddle;

public interface OutTheBoard extends Mechanic {

    Paddle isOutTheBoard(Paddle paddleLeft, Paddle paddleRight);
}