package main.java.mvc.game;

import main.java.App;

import main.java.mvc.game.element.function.intangible.bonus.Bonus;
import main.java.mvc.game.element.component.gameObject.immovable.pickup.Pickup;
import main.java.mvc.game.element.component.gameObject.movable.ball.Ball;
import main.java.mvc.game.element.component.gameObject.movable.paddle.Paddle;

import main.java.mvc.common.model.PlayerNamesModel;
import main.java.mvc.common.View;

import java.awt.*;

import java.util.List;

public class GameView extends View {

    private static final int NAME_MARGIN_TOP = 60;
    private static final int SCORE_MARGIN_TOP = 60;
    private static final int SCORES_DISTANCE = 150;

    private static final int PROCESS_BAR_PADDING_TWO_SIDES = 45;
    private static final int PROCESS_BARS_DISTANCE = (App.WIDTH - 2*PROCESS_BAR_PADDING_TWO_SIDES)/39;
    private static final int PROCESS_BAR_WIDTH = PROCESS_BARS_DISTANCE*7;
    private static final int PROCESS_BAR_HEIGHT = 12;
    private static final int PROCESS_BAR_BORDER_WIDTH = 1;

    private static final int PROCESS_BAR_Y = 700;

    private final Font infoFont = new Font("Serif", Font.PLAIN, 20);
    private final Font nameFont = new Font("Serif", Font.PLAIN, 50);
    private final Font scoreFont = new Font("Serif", Font.PLAIN, 50);

    private final String leftPlayerName;
    private final String rightPlayerName;

    private GameController controller;

    public GameView(PlayerNamesModel playerNamesModel) {
        super();

        leftPlayerName = playerNamesModel.getPlayerName1();
        rightPlayerName = playerNamesModel.getPlayerName2();
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

        paintScore(g);
        paintName(g);

        if (!controller.isStarted()) {
            paintStartingText(g);
        }

        paintBall(g);
        paintPickup(g);

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

        List<Ball> ballList = model.getBallList();
        for (Ball ball : ballList) {
            g.fillOval(ball.getX(), ball.getY(), ball.getSize(), ball.getSize());
        }
    }

    private void paintStartingText(Graphics g) {
        String info = "Press Space To Start Game";

        g.setColor(Color.gray);
        g.setFont(infoFont);
        g.drawString(info, (App.WIDTH - g.getFontMetrics().stringWidth(info))/2, 200);
    }

    private void paintName(Graphics g) {
        int widthName1 = g.getFontMetrics().stringWidth(leftPlayerName);
        int widthName2 = g.getFontMetrics().stringWidth(rightPlayerName);

        g.setColor(Color.gray);
        g.setFont(nameFont);
        g.drawString(leftPlayerName, (App.WIDTH/2 - widthName1)/2, NAME_MARGIN_TOP);
        g.drawString(rightPlayerName, 600 + (App.WIDTH/2 - widthName2)/2, NAME_MARGIN_TOP);
    }

    private void paintScore(Graphics g) {
        GameModel model = controller.getModel();

        String leftScore = Integer.toString(model.getLeftPaddle().getScore());
        String rightScore = Integer.toString(model.getRightPaddle().getScore());

        final int leftPlayerScoreWidth = g.getFontMetrics().stringWidth(leftScore);

        g.setFont(scoreFont);
        g.setColor(Color.gray);
        g.drawString(leftScore, App.WIDTH/2 - SCORES_DISTANCE/2 - leftPlayerScoreWidth, SCORE_MARGIN_TOP);
        g.drawString(rightScore, App.WIDTH/2 + SCORES_DISTANCE/2, SCORE_MARGIN_TOP);
    }

    private void paintPickup(Graphics g) {
        Pickup pickup = controller.getModel().getPickup();

        if (pickup != null) {
            Image image = getToolkit().getImage(pickup.getImagePath());
            g.drawImage(image, pickup.getX(), pickup.getY(), null);
        }
    }

    private void paintProcessBars(Graphics g) {
        List<Bonus> bonusList = controller.getModel().getBonusList();

        for (int i = 0; i < bonusList.size(); i++) {
            paintProcessBar(g, i, bonusList.get(i++));
        }
    }

    private void paintProcessBar(Graphics g, int itemIndex, Bonus bonus) {
        if (bonus.hasTimeLimit()) {
            // 7*5+4 = 39 part
            // 5 items and 4 spaces between them, item.width : space.width = 7 : 1

            final int x = PROCESS_BAR_PADDING_TWO_SIDES + PROCESS_BARS_DISTANCE*itemIndex + PROCESS_BAR_WIDTH*itemIndex;

            //Paint icon
            Image image = getToolkit().getImage(Bonus.getImagePath(bonus.getType()));
            g.drawImage(image, x - 25, PROCESS_BAR_Y - 5, 20, 20, null);

            //  Paint container
            g.setColor(Color.white);
            g.fillRect(x, PROCESS_BAR_Y, PROCESS_BAR_WIDTH, PROCESS_BAR_HEIGHT);

            //  Paint black box inside container, visible part of container becomes border
            g.setColor(Color.black);
            g.fillRect(
                    x + PROCESS_BAR_BORDER_WIDTH,
                    PROCESS_BAR_Y + PROCESS_BAR_BORDER_WIDTH,
                    PROCESS_BAR_WIDTH - 2*PROCESS_BAR_BORDER_WIDTH,
                    PROCESS_BAR_HEIGHT - 2*PROCESS_BAR_BORDER_WIDTH
            );

            //  Paint duration part
            g.setColor(bonus.getProcessBar().getColor());
            g.fillRect(
                    x + PROCESS_BAR_BORDER_WIDTH,
                    PROCESS_BAR_Y + PROCESS_BAR_BORDER_WIDTH,
                    bonus.getProcessBar().getWidth(),
                    PROCESS_BAR_HEIGHT - 2*PROCESS_BAR_BORDER_WIDTH
            );
        }
    }
}
