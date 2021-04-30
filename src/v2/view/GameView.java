package v2.view;

import v2.board.Score;

import v2.component.gameObject.immovable.bonus.Bonus;
import v2.component.gameObject.immovable.star.Star;
import v2.component.gameObject.movable.ball.Ball;
import v2.component.gameObject.movable.paddle.Paddle;

import v2.controller.GameController;
import v2.model.EnterNameDialogModel;
import v2.model.GameModel;

import java.awt.*;

import java.util.List;

public class GameView extends View {

    private final String leftPlayerName;
    private final String rightPlayerName;
    private final Font infoFont = new Font("Serif", Font.PLAIN, 20);
    private final Font nameFont = new Font("Serif", Font.PLAIN, 50);
    private final Font scoreFont = new Font("Serif", Font.PLAIN, 50);

    private GameController controller;

    public static int timeLong = 4400;

    public GameView(EnterNameDialogModel enterNameDialogModel) {
        super();

        leftPlayerName = enterNameDialogModel.getPlayerName1();
        rightPlayerName = enterNameDialogModel.getPlayerName2();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void initUI() {
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    //region paint components

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
        paintStartGame(g);

        drawStar(g);
        drawBonus(g);
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, v2.Game.WIDTH, v2.Game.HEIGHT);
    }

    private void paintPaddle(Graphics g, Paddle paddle) {
        g.setColor(Color.cyan);
        g.fillRect(paddle.getX(), paddle.getY(), Paddle.INITIAL_PADDLE_WIDTH, Paddle.INITIAL_PADDLE_HEIGHT);
    }

    private void paintBall(Graphics g) {
        GameModel model = controller.getModel();

        g.setColor(Color.white);

        List<Ball> ballList = model.getBalls();
        for (Ball ball : ballList) {
            g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
        }
    }

    private void paintStartGame(Graphics g) {
        if (!controller.isStarted()){
            g.setColor(Color.gray);
            g.setFont(infoFont);
            String info = "Press Space To Start Game";
            g.drawString(info, (v2.Game.WIDTH - g.getFontMetrics().stringWidth(info))/2, 200);
        }
    }

    private void paintName(Graphics g) {
        int widthName1 = g.getFontMetrics().stringWidth(leftPlayerName);
        int widthName2 = g.getFontMetrics().stringWidth(rightPlayerName);

        g.setColor(Color.gray);
        g.setFont(nameFont);
        g.drawString(leftPlayerName, (v2.Game.WIDTH/2 - widthName1)/2, 60);
        g.drawString(rightPlayerName, 600 + (v2.Game.WIDTH/2 - widthName2)/2, 60);
    }

    private void paintScore(Graphics g) {
        GameModel model = controller.getModel();

        Score scoreObj1 = model.getLeftPaddle().getScoreObject();
        Score scoreObj2 = model.getRightPaddle().getScoreObject();
        String displayScore1 = Integer.toString(scoreObj1.getScore());
        String displayScore2 = Integer.toString(scoreObj2.getScore());

        g.setFont(scoreFont);
        g.setColor(Color.gray);
        g.drawString(displayScore1, (v2.Game.WIDTH/2 - 75 - g.getFontMetrics().stringWidth(displayScore1)), 60);
        g.drawString(displayScore2, v2.Game.WIDTH/2 + 75, 60);
    }

    private void drawStar(Graphics g) {
        Star star = controller.getModel().getStar();

        if (star != null) {
            Image image = getToolkit().getImage(star.getImagePath());
            g.drawImage(image, star.getX(), star.getY(), null);
        }
    }

    private void drawBonus(Graphics g) {
        List<Bonus> bonusList = controller.getModel().getBonus();

        for (int i = 0; i < bonusList.size(); i++) {
            paintTimerStar(g, i, bonusList.get(i));
        }
    }


    private void paintTimerStar(Graphics g, int bonusIndex, Bonus bonus) {
        int x = 50 + 50*bonusIndex + 220*bonusIndex;

        g.setColor(Color.white);
        g.fillRect(x - 1, 700 - 1, 222, 12);

        g.setColor(Color.black);
        g.fillRect(x, 700, 220, 10);

        g.setColor(bonus.getColor());
        g.fillRect(x, 700, bonus.getTimeLeft()/40, 10);
    }
}
