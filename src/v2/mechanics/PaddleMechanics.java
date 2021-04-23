package v2.mechanics;

import v2.component.GameObject;

public interface PaddleMechanics extends
        VerticalMoveOnly,
        Stoppable,
        WallCollide,
        ChangeSpeed<GameObject> { }
