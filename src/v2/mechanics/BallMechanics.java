package v2.mechanics;

import v2.component.paddle.Paddle;

public interface BallMechanics extends
        ChangeSpeed<Paddle>,
        ChangeMoveDirection,
        OutTheBoard,
        GameObjectCollide,
        WallCollide { }
