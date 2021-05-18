package main.java.mvc.game;

import main.java.App;
import main.java.mvc.game.board.Score;

import main.java.mvc.game.component.gameObject.movable.paddle.LeftPaddle;
import main.java.mvc.game.component.intangible.bonus.Bonus;
import main.java.mvc.game.component.gameObject.immovable.star.Pickup;
import main.java.mvc.game.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.component.gameObject.movable.paddle.Paddle;

import main.java.mvc.common.model.EnterNameDialogModel;
import main.java.mvc.common.View;

import java.awt.*;

import java.util.List;

public class GameView extends View {

    private final String leftPlayerName;
    private final String rightPlayerName;

    private final Font infoFont = new Font("Serif", Font.PLAIN, 20);
    private final Font nameFont = new Font("Serif", Font.PLAIN, 50);
    private final Font scoreFont = new Font("Serif", Font.PLAIN, 50);

    private GameController controller;

    public GameView(EnterNameDialogModel enterNameDialogModel) {
        super();

        leftPlayerName = enterNameDialogModel.getPlayerName1();
        rightPlayerName = enterNameDialogModel.getPlayerName2();
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    public String getLeftPlayerName() {
        return leftPlayerName;
    }

    public String getRightPlayerName() {
        return rightPlayerName;
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

        if (!controller.isStarted()) {
            paintStartGame(g);
        }

        paintStar(g);

        paintProcessBars(g);
    }

    private void paintBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, App.WIDTH, App.HEIGHT);
    }

    private void paintPaddle(Graphics g, Paddle paddle) {
        g.setColor(Color.cyan);
        g.fillRect(paddle.getX(), paddle.getY(), paddle.getWidth(), paddle.getHeight());
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
        g.setColor(Color.gray);
        g.setFont(infoFont);
        String info = "Press Space To Start Game";
        g.drawString(info, (App.WIDTH - g.getFontMetrics().stringWidth(info))/2, 200);
    }

    private void paintName(Graphics g) {
        int widthName1 = g.getFontMetrics().stringWidth(leftPlayerName);
        int widthName2 = g.getFontMetrics().stringWidth(rightPlayerName);

        final int marginTop = 60;

        g.setColor(Color.gray);
        g.setFont(nameFont);
        g.drawString(leftPlayerName, (App.WIDTH/2 - widthName1)/2, marginTop);
        g.drawString(rightPlayerName, 600 + (App.WIDTH/2 - widthName2)/2, marginTop);
    }

    private void paintScore(Graphics g) {
        GameModel model = controller.getModel();

        Score scoreObj1 = model.getLeftPaddle().getScoreObject();
        Score scoreObj2 = model.getRightPaddle().getScoreObject();
        String displayScore1 = Integer.toString(scoreObj1.getScore());
        String displayScore2 = Integer.toString(scoreObj2.getScore());

        final int marginTop = 60;
        final int distanceBetweenScores = 150;
        final int leftPlayerScoreWidth = g.getFontMetrics().stringWidth(displayScore1);

        g.setFont(scoreFont);
        g.setColor(Color.gray);
        g.drawString(displayScore1, App.WIDTH/2 - distanceBetweenScores/2 - leftPlayerScoreWidth, marginTop);
        g.drawString(displayScore2, App.WIDTH/2 + distanceBetweenScores/2, marginTop);
    }

    private void paintStar(Graphics g) {
        Pickup pickup = controller.getModel().getStar();

        if (pickup != null) {

            Image image = getToolkit().getImage(pickup.getImagePath());

            g.drawImage(image, pickup.getX(), pickup.getY(), null);
        }
    }

    private void paintProcessBars(Graphics g) {
        List<Bonus> bonusList = controller.getModel().getBonus();
        int displayItemIndex = 0;

        for (Bonus bonus : bonusList) {
            if (bonus.hasTimeLimit()) {
                paintProcessBar(g, displayItemIndex++, bonus);
            }
        }
    }

    private void paintProcessBar(Graphics g, int itemIndex, Bonus bonus) {
        if (bonus.hasTimeLimit()) {
            // 7*5+4 = 39 part
            // 5 items and 4 spaces between them, item.width : space.width = 7 : 1

            final int space = (App.WIDTH - 2*(LeftPaddle.INITIAL_X + Paddle.INITIAL_WIDTH))/39;
            final int width = space*7;
            final int height = 12;
            final int marginLeftOfFirstItem = LeftPaddle.INITIAL_X;
            final int border = 1;

            final int x = marginLeftOfFirstItem + space*itemIndex + width*itemIndex;
            final int y = 700;

            //Paint icon
            Image image = getToolkit().getImage(Bonus.getImagePath(bonus.getType()));
            g.drawImage(image, x - 25, y - 5, 20, 20, null);

            //  Paint container
            g.setColor(Color.white);
            g.fillRect(x, y, width, height);

            //  Paint black box inside container, visible part of container becomes border
            g.setColor(Color.black);
            g.fillRect(x + border, y + border, width - 2*border, height - 2*border);

            //  Paint duration part
            g.setColor(bonus.getProcessBar().getColor());
            g.fillRect(x + border, y + border, bonus.getProcessBar().getWidth(), height - 2*border);
        }
    }
}
