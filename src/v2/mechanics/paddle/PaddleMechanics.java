package v2.mechanics.paddle;

import v2.component.gameObject.GameObject;
import v2.mechanics.common.move.ChangeSpeed;
import v2.mechanics.common.collide.WallCollide;
import v2.mechanics.common.move.Stoppable;

public interface PaddleMechanics extends
        Stoppable,
        WallCollide,
        ChangeSpeed<GameObject> { }
