package main.java.mvc.game.mechanics.paddle;

import main.java.mvc.game.mechanics.common.move.Stoppable;
import main.java.mvc.game.mechanics.common.transform.Resizeable;

public interface PaddleMechanics extends
        PaddleWallCollide,
        PaddleChangeSpeed,
        Stoppable,
        Resizeable { }
