package v2.controller;

import v2.model.EnterNameDialogModel;
import v2.model.GameModel;
import v2.model.MenuModel;
import v2.thread.RankingBtnThread;
import v2.utils.database.Database;
import v2.view.GameView;
import v2.view.MenuView;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class MenuController extends Controller<MenuView, MenuModel> {

    public static String playerName1;
    public static String playerName2;

    public MenuController(FlowController flowController) {
        super(flowController, new MenuView(), new MenuModel());
    }

    @Override
    public void initEvent() {
        addPlayButtonEvent();
        addHelpButtonEvent();
        addRankingButtonEvent();
        addExitButtonEvent();
    }

    private void addPlayButtonEvent() {
        view.getPlayButton().addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    null,
                    view.getPlayDialog(),
                    "Fill in your names",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {

                playerName1 = view.getPlayerNameTextField1().getText();
                playerName2 = view.getPlayerNameTextField2().getText();

                EnterNameDialogModel model = new EnterNameDialogModel(playerName1, playerName2);
                GameView gameView = new GameView(model);
                GameModel gameModel = new GameModel();

                GameController gameController = new GameController(flowController, gameView, gameModel);

                gameView.setController(gameController);
                gameModel.setController(gameController);

                switchController(gameController);
            }
        });
    }

    private void addHelpButtonEvent() {
        String[] play = { "OK" };

        view.getHelpButton().addActionListener(e -> JOptionPane.showOptionDialog(
                null,
                view.getHelpDialog(),
                "Help",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                play,
                null)
        );
    }

    private void addRankingButtonEvent() {
        String[] topScoreColumns = { "Rank", "Name", "Total points", "Join date" };
        String[] topWinColumns = { "Rank", "Name", "Wins", "Played", "Percentage", "Join date" };

        DefaultTableCellRenderer align = new DefaultTableCellRenderer();
        align.setHorizontalAlignment(JLabel.CENTER);

        String[] str = { "OK" };

        view.getRankingButton().addActionListener(e -> {
            RankingBtnThread thread = new RankingBtnThread();
            thread.start();
        });

//        view.getRankingButton().addActionListener(e -> {
//
//                    if (!Database.connected()) {
//                        try {
//                            Database.connect();
//                        }
//                        catch (SQLException exception) {
//                            JOptionPane.showOptionDialog(
//                                    null,
//                                    new JLabel("Cannot connect to database"),
//                                    "Help",
//                                    JOptionPane.OK_CANCEL_OPTION,
//                                    JOptionPane.PLAIN_MESSAGE,
//                                    null,
//                                    str,
//                                    null);
//
//                            exception.printStackTrace();
//                            return;
//                        }
//                    }
//
//                    //  Top Score
//                    DefaultTableModel topScoreTableModel = new DefaultTableModel(topScoreColumns, 0);
//                    List<String[]> topScoreData = Database.getTopScore();
//
//                    topScoreData.forEach(topScoreTableModel::addRow);
//
//                    JTable topScoreTable = new JTable(topScoreTableModel);
//                    topScoreTable.setRowHeight(20);
//                    topScoreTable.getColumnModel().getColumn(0).setCellRenderer(align);
//                    topScoreTable.getColumnModel().getColumn(2).setCellRenderer(align);
//                    topScoreTable.getColumnModel().getColumn(3).setCellRenderer(align);
//
//                    topScoreTable.getColumnModel().getColumn(0).setMaxWidth(50);
//                    topScoreTable.getColumnModel().getColumn(2).setMaxWidth(110);
//                    topScoreTable.getColumnModel().getColumn(3).setMaxWidth(110);
//
//                    JScrollPane topScoreScrollPane = new JScrollPane(topScoreTable);
//
//
//                    //  Top Win
//                    DefaultTableModel topWinTableModel = new DefaultTableModel(topWinColumns, 0);
//                    List<String[]> topWinData = Database.getTopWin();
//
//                    topWinData.forEach(topWinTableModel::addRow);
//
//                    JTable topWinTable = new JTable(topWinTableModel);
//                    topWinTable.setRowHeight(20);
//                    topWinTable.getColumnModel().getColumn(0).setCellRenderer(align);
//                    topWinTable.getColumnModel().getColumn(2).setCellRenderer(align);
//                    topWinTable.getColumnModel().getColumn(3).setCellRenderer(align);
//                    topWinTable.getColumnModel().getColumn(4).setCellRenderer(align);
//                    topWinTable.getColumnModel().getColumn(5).setCellRenderer(align);
//
//                    topWinTable.getColumnModel().getColumn(0).setMaxWidth(50);
//
//                    JScrollPane topWinScrollPane = new JScrollPane(topWinTable);
//
//
//                    //  Add to tabbed pane
//                    JTabbedPane tabbedPane = new JTabbedPane();
//                    tabbedPane.add("Top Score", topScoreScrollPane);
//                    tabbedPane.add("Top Wins", topWinScrollPane);
//
//                    JOptionPane.showOptionDialog(
//                            null,
//                            tabbedPane,
//                            "Help",
//                            JOptionPane.OK_CANCEL_OPTION,
//                            JOptionPane.PLAIN_MESSAGE,
//                            null,
//                            str,
//                            null);
//                }
//        );
    }

    private void addExitButtonEvent() {
        view.getExitButton().addActionListener(e -> {
            int output = JOptionPane.showConfirmDialog(
                    view,
                    "Do you want to exit",
                    " ",
                    JOptionPane.YES_NO_OPTION
            );

            if (output == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
