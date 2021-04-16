package v2.view;

import v2.board.Score;
import v2.component.Ball;
import v2.controller.GameController;
import v2.model.GameModel;
import v2.component.paddle.Paddle;
import v2.model.EnterNameDialogModel;

import java.awt.*;

public class GameView extends View {
    private GameController controller;

    private String leftPlayerName;
    private String rightPlayerName;

    public GameView(EnterNameDialogModel enterNameDialogModel) {
        super();

        leftPlayerName = enterNameDialogModel.getPlayerName1();
        rightPlayerName = enterNameDialogModel.getPlayerName2();
    }

    public void setController(GameController controller){
        this.controller = controller;
    }

    @Override
    public void initUI() {
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    //region paint components

    public void stopGame(Graphics g) {
        if (GameModel.pause) {
            paintStopGame(g);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintChildComponents(g);
    }

    private void paintChildComponents(Graphics g) {
        GameModel model = controller.getModel();

        paintBackground(g);

        paintPaddle(g, model.getLeftPaddle());
        paintPaddle(g, model.getRightPaddle());

        paintBall(g);

        paintScore(g);

        paintName(g);
        stopGame(g);
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, v2.Game.WIDTH, v2.Game.HEIGHT);
    }

    private void paintPaddle(Graphics g, Paddle paddle) {
        g.setColor(Color.cyan);
        g.fillRect(paddle.getX(), paddle.getY(), Paddle.PADDLE_WIDTH, Paddle.PADDLE_HEIGHT);
    }

    private void paintBall(Graphics g) {
        GameModel model = controller.getModel();

        g.setColor(Color.white);
        g.fillOval(model.getBall().getX(), model.getBall().getY(), Ball.BALL_SIZE, Ball.BALL_SIZE);
    }

    private void paintStopGame(Graphics g) {
        g.setColor(Color.gray);
        g.setFont(new Font("Serif", Font.PLAIN, 20));
        String stop = "Enter Space To Continue This Game";
        g.drawString(stop, (v2.Game.WIDTH - g.getFontMetrics().stringWidth(stop))/2, 200);
    }

    private void paintName(Graphics g) {
        int widthName1 = g.getFontMetrics().stringWidth(leftPlayerName);
        int widthName2 = g.getFontMetrics().stringWidth(rightPlayerName);

        g.setColor(Color.gray);
        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.drawString(leftPlayerName, (v2.Game.WIDTH/2 - widthName1)/2, 60);
        g.drawString(rightPlayerName, 600 + (v2.Game.WIDTH/2 - widthName2)/2, 60);
    }

    private void paintScore(Graphics g) {
        GameModel model = controller.getModel();

        Score scoreObj1 = model.getLeftPaddle().getScoreObject();
        Score scoreObj2 = model.getRightPaddle().getScoreObject();
        String displayScore1 = Integer.toString(scoreObj1.getScore());
        String displayScore2 = Integer.toString(scoreObj2.getScore());

        g.setFont(new Font("Serif", Font.PLAIN, 50));
        g.setColor(Color.gray);
        g.drawString(displayScore1, (v2.Game.WIDTH/2 - 75 - g.getFontMetrics().stringWidth(displayScore1)), 60);
        g.drawString(displayScore2, v2.Game.WIDTH/2 + 75, 60);
    }

    //endregion
}
