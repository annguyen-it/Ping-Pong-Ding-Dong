package v2.view;

import v2.board.Score;
import v2.board.TAdapter;
import v2.component.Ball;
import v2.model.GameModel;
import v2.component.paddle.Paddle;
import v2.controller.Controller;
import v2.model.EnterNameDialogModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView extends View implements ActionListener {

    private static final int DELAY = 2;

    private final String leftPlayerName;
    private final String rightPlayerName;

    private final GameModel model;

    public GameView(Controller controller, EnterNameDialogModel enterNameDialogModel) {
        super(controller);

        model = (GameModel) controller.getModel();
        leftPlayerName = enterNameDialogModel.getPlayerName1();
        rightPlayerName = enterNameDialogModel.getPlayerName2();
    }

    @Override
    public void initEvent() {
        addKeyListener(new TAdapter(model.getLeftPaddle(), model.getRightPaddle()));
    }

    @Override
    public void initUI() {
        setBackground(Color.BLACK);
        setFocusable(true);
        new Timer(DELAY, this).start();
    }

    //region paint components

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChildComponents(g);
    }

    private void paintChildComponents(Graphics g) {
        paintPaddle(g, model.getLeftPaddle());
        paintPaddle(g, model.getRightPaddle());

        paintBall(g);

        paintScore(g, model.getLeftPaddle());
        paintScore(g, model.getRightPaddle());
    }

    private void paintPaddle(Graphics g, Paddle paddle){
        g.setColor(Color.cyan);
        g.fillRect(paddle.getX(), paddle.getY(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
    }

    private void paintBall(Graphics g){
        g.setColor(Color.white);
        g.fillOval(model.getBall().getX(), model.getBall().getY(), Ball.BALL_SIZE, Ball.BALL_SIZE);
    }

    private void paintScore(Graphics g, Paddle paddle){
        Score scoreObj = paddle.getScoreObject();

        g.setFont(new Font("Serif", Font.PLAIN, 80));
        g.setColor(Color.gray);
        g.drawString(Integer.toString(scoreObj.getScore()), scoreObj.getX(), scoreObj.getY());
    }

    //endregion

    @Override
    public void actionPerformed(ActionEvent e) {
        model.updatePaddles();
        model.updateBall();

        repaint();
    }
}
